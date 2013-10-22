package com.ibukanov.chat.service.impl;

import com.ibukanov.chat.service.UserService;
import com.ibukanov.chat.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
public class AuthenticationUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthenticationUserDetailsServiceImpl.log.info("loadUserDetails " + username);
		com.ibukanov.chat.model.User user = userService.findByLogin(username);
		if (user == null) {
			String message = "User with login " + username
					+ " was not found in the database";
			AuthenticationUserDetailsServiceImpl.log.error(message);
			throw new UsernameNotFoundException(message);
		}

		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>(1);
		grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_USER));
		return new User(username, user.getPassword(), grantedAuthorities);
	}
}
