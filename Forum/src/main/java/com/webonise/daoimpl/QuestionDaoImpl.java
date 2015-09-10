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
import com.webonise.dao.QuestionDao;
import com.webonise.models.Question;

@Repository("questionDao")
public class QuestionDaoImpl implements QuestionDao {

	final static Logger LOGGER = Logger.getLogger(QuestionDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction transaction = null;

	@Override
	public Question findByQuestionId(long questionId) throws ForumException {

		Question question = null;
		try {
			session = sessionFactory.openSession();
			question = (Question) session.get(Question.class, questionId);
			
		} catch (HibernateException e) {
			throw new ForumException(e);
		}
		finally {
			session.close();
		}
		return question;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> findByQuestion(String question)throws ForumException {

		
		List<Question> questionlist = null;
		try {
			session = sessionFactory.openSession();
			
			questionlist = session.createQuery("FROM Question WHERE question LIKE '%"+question+"%'").list();
			LOGGER.info("Question list  retrived is " + questionlist);
			
		} catch (HibernateException e) {
			throw new ForumException(e);
		}
		finally {
			session.close();
		}
		return questionlist;

	}

	@Override
	public List<Question> findAll() throws ForumException{

		ArrayList<Question> questionlist = null;
		try {
			
			session = sessionFactory.openSession();
			questionlist = (ArrayList<Question>) session.createQuery("FROM Question").list();

			
		} catch (HibernateException e) {
			throw new ForumException(e);
		}
		finally {
			session.close();
		}
		return questionlist;
	}

	@Override
	public void saveQuestion(Question question) throws ForumException{
		try {
			session = sessionFactory.openSession();
			transaction = session.getTransaction();
			session.beginTransaction();
			session.save(question);
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
	public void delete(long questionId) throws ForumException{
		try {
			session = sessionFactory.openSession();
			transaction = session.getTransaction();
			session.beginTransaction();
			session.delete(session.get(Question.class, questionId));
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
