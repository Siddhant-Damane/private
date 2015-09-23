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

import com.webonise.dao.QuestionDao;
import com.webonise.models.Question;

@Repository("questionDao")
public class QuestionDaoImpl implements QuestionDao {

	final static Logger logger = Logger.getLogger(QuestionDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public Question findByQuestionId(long questionId) {
		// TODO Auto-generated method stub

		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		Question question = (Question) session.get(Question.class, questionId);

		tx.commit();
		//logger.info("Question found is " + question.getQuestion());

		return question;
	}

	@Override
	public List<Question> findByQuestion(String question) {
		// TODO Auto-generated method stub

		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Question.class);
		criteria.add(Restrictions.like("question", "%" + question+ "%"));
		
		ArrayList<Question> questionlist = (ArrayList<Question>) criteria.list();
		tx.commit();

		logger.info("Question list  retrived is " + questionlist);
		return questionlist;

	}

	@Override
	public List<Question> findAll() {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		ArrayList<Question> questionlist = (ArrayList<Question>) session.createCriteria(Question.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		tx.commit();
		return questionlist;
	}

	@Override
	public void saveAndFlush(Question question) {
		// TODO Auto-generated method stub

		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		session.save(question);
		tx.commit();

	}

	@Override
	public void delete(long questionId) {
		// TODO Auto-generated method stub

		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(session.get(Question.class, questionId));
		tx.commit();

	}

}
