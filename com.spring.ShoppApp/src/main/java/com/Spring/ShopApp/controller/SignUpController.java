package com.Spring.ShopApp.controller;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Spring.ShopApp.controller.RequestPojo.ApiResponse;
import com.Spring.ShopApp.model.User;
import com.Spring.ShopApp.service.UserServices.UserService;

@RestController
@RequestMapping("api/signup")
public class SignUpController {


	@Autowired
	UserService userservice;

	@RequestMapping("user")
	public ResponseEntity<?> userLogin(@RequestBody HashMap<String, String> signupRequest) {
		try {
			User user = userservice.signUpUser(signupRequest);
			return ResponseEntity.ok(user);
		} catch (Exception exception) {
			ResponseEntity<ApiResponse> returnval;
			returnval = ResponseEntity.badRequest().body(new ApiResponse(exception.getMessage(), ""));
			return returnval;
		}
	}
}
