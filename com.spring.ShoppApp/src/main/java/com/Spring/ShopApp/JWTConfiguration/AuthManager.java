package com.Spring.ShopApp.JWTConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.Spring.ShopApp.controller.RequestPojo.LoginRequest;
import com.Spring.ShopApp.model.User;
import com.Spring.ShopApp.service.UserServices.UserService;

@Configuration
public class AuthManager {
	@Autowired
	UserService userService;
	
	/*Bu sınıfta kullanicinin giriş bilgileri kontrol edilecek şifresi yanlış, mobil numarası yanlış
	 *  veya girdiği veriler sistemde kayıtlı değil ise uyarı alacak.*/
	
	private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);

	public Authentication authenticate(UsernamePasswordAuthenticationToken authentication, LoginRequest loginRequest)
			throws AuthenticationException {
		
		String mobile;
		String password;
		User user;
		
		
		mobile = authentication.getPrincipal() + "";
		password = authentication.getCredentials() + "";
		

		
		try {

			if (userService == null) {
				logger.info("Kullanici Bulunamadı");
				throw new BadCredentialsException("1001");
			}
			user = userService.findByMobile(mobile);
			if (user == null) {
				throw new BadCredentialsException("Kullanıcı Bulunamadı!");
			}

			logger.info("from authentication " + password + " from db " + user.getPassword());
			if (!this.passwordMatch(password, user.getPassword())) {
				logger.info("Sifre Yanlis");
				throw new BadCredentialsException("Kullanıcı bilgileri uyuşmuyor!");
			}

			return new UsernamePasswordAuthenticationToken(new UserPrincipal(user.getId(), mobile, password), password);
		} catch (Exception e) {
			logger.info("Error", e);
			throw new BadCredentialsException(e.getMessage());
		}

	}

	private Boolean passwordMatch(String girilenSifre, String asilSifre) {

		return girilenSifre.equals(asilSifre);

	}

}
