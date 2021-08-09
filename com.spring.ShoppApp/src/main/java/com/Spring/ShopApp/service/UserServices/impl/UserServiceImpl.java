package com.Spring.ShopApp.service.UserServices.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Spring.ShopApp.Repository.UserRepository;
import com.Spring.ShopApp.model.User;
import com.Spring.ShopApp.service.UserServices.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository ur;

	@Override
	public User findByMobile(String mobile) throws Exception {
		return ur.findByMobile(mobile).orElseThrow(() -> new Exception("Kullanici Bulunamadi!"));
	}

	@Override
	public User getUserDetailById(long userId) throws Exception {

		return ur.findById(userId).orElseThrow(() -> new Exception("Kullanici Bulunamadi!"));
	}

	@Override
	public User signUpUser(HashMap<String, String> signupRequest) throws Exception {
		try {
			if (ur.findByMobile(signupRequest.get("mobile")).isPresent()) {
				throw new Exception("Bu numara ile giris yapilmis.");

				/*
				 * Postmandan kontrol ettiğimde; localhost:1083/api/signup/user
				 * 
				 * {"name":"asd", "email":"asd@gmail.com", "password":"ayfer1",
				 * "mobile":"542627"
				 * 
				 * { "message": "Bu numara ile giris yapilmis.", "token": null, "array": null,
				 * "data": null, "jsonobj": null, "errors": [ "" ] }
				 * 
				 */ 
			}
			User user = new User();
			user.setName(signupRequest.get("name"));
			user.setEmail(signupRequest.get("email"));
			user.setMobile(signupRequest.get("mobile"));
			user.setPassword(signupRequest.get("password"));
			ur.save(user);
			return user;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
}
