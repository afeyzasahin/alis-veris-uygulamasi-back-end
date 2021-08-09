package com.Spring.ShopApp.JWTConfiguration;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Spring.ShopApp.model.User;
import com.Spring.ShopApp.service.UserServices.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
		try {
			User user = userService.findByMobile(mobile);
			return UserPrincipal.create(user);
		} catch (Exception e) {
			throw new UsernameNotFoundException("Bu numara sistemde kayıtlı değil. (UsernameNotFoundException)");
		}
	}

	// WTAuthenticationFilter tarafından kullanılacak metot
	
	@Transactional
	public UserDetails loadUserById(Long id) {
		try {
			User user = userService.getUserDetailById(id);
			return UserPrincipal.create(user);
		} catch (Exception e) {
			throw new UsernameNotFoundException("Bu id sistemde kayıtlı değil. (UsernameNotFoundException)");
		}

	}

}
