package com.Spring.ShopApp.service.UserServices;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.Spring.ShopApp.model.User;

/*https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
 * public interface IUserService {
  User registerNewUserAccount(UserDto userDto);
 }*/

@Service
public interface UserService {
	User findByMobile(String mobile) throws Exception;
	User getUserDetailById(long userId) throws Exception;
	User signUpUser(HashMap<String,String> signupRequest) throws Exception;
	
	//e-posta veya cep telefonu daha önceden varsa hata göndermem gerekiyor,
	//userServiceImp sınıfında unimplemented metot oluşturacağım.
	

}
