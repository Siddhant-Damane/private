package com.webonise.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webonise.custom.exceptions.ForumException;
import com.webonise.dao.AnswerDao;
import com.webonise.models.Answers;

@Repository("answerDao")
public class AnswerDaoImpl implements AnswerDao {

	final static Logger LOGGER = Logger.getLogger(AnswerDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction transaction = null;

	@SuppressWarnings("unchecked")
	@Override
	public List<Answers> findByQuestionId(long questionId) throws ForumException {

		
		ArrayList<Answers> answerlist = null;
		try {
			session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(Answers.class);
			criteria.add(Restrictions.like("questionid", questionId));
			answerlist = (ArrayList<Answers>) criteria.list();
		} catch (HibernateException e) {
			
			throw new ForumException(e);
		}
		finally {
			session.close();
		}

		return answerlist;

	}

	@Override
	public Answers findByAnswerId(long answerId) throws ForumException{

		Answers answers = null;
		try {
			session = sessionFactory.openSession();
			answers = (Answers) session.get(Answers.class, answerId);
		} catch (HibernateException e) {
			throw new ForumException(e);
		}
		finally {
			session.close();
		}
		return answers;
	}

	@Override
	public void saveAnser(Answers answer) throws ForumException{
		try {
			session = sessionFactory.openSession();
			transaction = session.getTransaction();
			session.beginTransaction();
			session.save(answer);
			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			
			throw new ForumException(e);
		} finally {
			session.close();
		}

	}

	@Override
	public Answers findOne(long answerId) throws ForumException{

		Answers answers = null;
		try {
			session = sessionFactory.openSession();
			answers = (Answers) session.get(Answers.class, answerId);
		} catch (HibernateException e) {
			throw new ForumException(e);
		}
		finally {
			session.close();
		}
		return answers;
	}

	@Override
	public void delete(long answerId) throws ForumException{
		try {
			session = sessionFactory.openSession();
			transaction = session.getTransaction();
			session.beginTransaction();
			session.delete(session.get(Answers.class, answerId));
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			throw new ForumException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void saveAndUpdate(Answers answer) throws ForumException{
		try {
			session = sessionFactory.openSession();
			transaction = session.getTransaction();
			session.beginTransaction();
			session.update(answer);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			throw new ForumException(e);
		} finally {
			session.close();
		}
	}
}
