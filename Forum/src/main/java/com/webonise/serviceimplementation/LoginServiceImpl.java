package com.webonise.serviceimplementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webonise.custom.exceptions.ConstraintException;
import com.webonise.custom.exceptions.DataLimitException;
import com.webonise.custom.exceptions.ForumException;
import com.webonise.dao.LoginDao;
import com.webonise.models.UserRole;
import com.webonise.models.Users;
import com.webonise.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements UserDetailsService,LoginService {

	final static Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);

	@Autowired
	private  LoginDao loginDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = null;
		try {
			user = loginDao.findByUserName(username);
		} catch (ForumException e) {
			LOGGER.error(e.getMessage(),e);
		}
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
		return buildUserForAuthentication(user, authorities);
	}

	public User buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {
		
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	public List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();		
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}

	public void registerUser(Users user) throws ForumException,ConstraintException ,DataLimitException{

		user.setEnabled(true);		
		loginDao.saveuser(user);		
	}

}
