package com.webonise.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webonise.dao.AnswerDao;
import com.webonise.models.Answers;
import com.webonise.models.Question;

@Repository("answerDao")
public class AnswerDaoImpl implements AnswerDao{

	

	final static Logger logger = Logger.getLogger(AnswerDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	@Override
	public List<Answers> findByQuestionId(long questionId) {
		// TODO Auto-generated method stub
		
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Answers.class);
		criteria.add(Restrictions.like("questionid", questionId));
		ArrayList<Answers> answerlist = (ArrayList<Answers>) criteria.list();
		tx.commit();

		logger.info("Question list  retrived is " + answerlist.get(0));
		return answerlist;

		
		
	}

	@Override
	public Answers findByAnswerId(long answerId) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		Answers answers = (Answers) session.get(Answers.class, answerId);
		
		tx.commit();
		logger.info("Answers found is " + answers.getQuestion());

		return answers;
	}

	@Override
	public void saveAndFlush(Answers answer) {
		// TODO Auto-generated method stub

		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		session.save(answer);
		tx.commit();
		
	}

	@Override
	public Answers findOne(long answerId) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		Answers answers = (Answers) session.get(Answers.class, answerId);
		
		tx.commit();
		logger.info("Answers found is " + answers.getQuestion());

		return answers;
	}

	@Override
	public void delete(long answerId) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(session.get(Answers.class,answerId));
		tx.commit();
	}

	@Override
	public void saveAndUpdate(Answers answer) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		session.update(answer);
		tx.commit();
	}

}
