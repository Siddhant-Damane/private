package com.webonise.service;

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

import com.webonise.dao.LoginDao;
import com.webonise.models.UserRole;
import com.webonise.models.Users;

@Service("loginService")
public class LoginServiceImpl implements UserDetailsService {

	final static Logger logger = Logger.getLogger(LoginServiceImpl.class);

	@Autowired
	private  LoginDao loginDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = loginDao.findByUserName(username);

		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		return buildUserForAuthentication(user, authorities);
	}

	private User buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

	public void registerUser(Users user) {
		// TODO Auto-generated method stub

		user.setEnabled(true);
		logger.info("User is " + user.getUserid() + "  " + user.getUsername() + "  " + user.getPassword());
		loginDao.saveuser(user);
		logger.info("User is " + user.getUserid() + "  " + user.getUsername() + "  " + user.getPassword());
	}

}
