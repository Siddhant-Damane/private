package com.webonise.daoimpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webonise.dao.LoginDao;
import com.webonise.models.UserRole;
import com.webonise.models.Users;

@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {

	final static Logger logger = Logger.getLogger(LoginDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public Users findByUserName(String username) {
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Users.class);
		criteria.add(Restrictions.like("username", username));
		ArrayList<Users> userlist = (ArrayList<Users>) criteria.list();
		tx.commit();

		if (userlist.size() == 1) {

			logger.info("user retrived is " + userlist.get(0));
			return userlist.get(0);
		}

		else {
			logger.info("database error");
			return null;
		}
	}

	@Override
	public void saveuser(Users user) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		session.save(user);
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole("ROLE_USER");
		session.save(userRole);
		tx.commit();

	}

}
