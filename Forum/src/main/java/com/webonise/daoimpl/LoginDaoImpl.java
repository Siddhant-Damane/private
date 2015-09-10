package com.webonise.daoimpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webonise.custom.exceptions.ConstraintException;
import com.webonise.custom.exceptions.DataLimitException;
import com.webonise.custom.exceptions.ForumException;
import com.webonise.dao.LoginDao;
import com.webonise.models.UserRole;
import com.webonise.models.Users;

@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {

	final static Logger LOGGER = Logger.getLogger(LoginDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction transaction = null;

	@Override
	public Users findByUserName(String username) throws ForumException {

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Users.class);
			criteria.add(Restrictions.like("username", username));
			@SuppressWarnings("unchecked")
			ArrayList<Users> userlist = (ArrayList<Users>) criteria.list();

			if (userlist.size() == 1) {

				LOGGER.info("user retrived is " + userlist.get(0));
				return userlist.get(0);
			} else {
				session.close();
				LOGGER.error("User: " + username + " Can Not Be Retrived");
				return null;
			}
		} catch (HibernateException e) {

			throw new ForumException(e);
		}

	}

	@Override
	public void saveuser(Users user) throws ForumException, ConstraintException ,DataLimitException{

		try {
			session = sessionFactory.openSession();
			transaction = session.getTransaction();
			session.beginTransaction();
			session.save(user);
			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole("ROLE_USER");
			session.save(userRole);
			transaction.commit();
		} catch (ConstraintViolationException e) {
			if (transaction != null)
				transaction.rollback();
			LOGGER.debug("Actual Exception Message is  " + e.getMessage());
			throw new ConstraintException("User name already present, try other !");

		}
		
		catch (DataException e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			LOGGER.debug("Actual Exception Class is  " + e.getClass());
			throw new DataLimitException("Please Enter proper data !");
		}

		catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			LOGGER.debug("Actual Exception Class is  " + e.getClass());
			throw new ForumException("Internal Error Occured try again ");
		} finally {
			
			session.close();
		}
	}
}
