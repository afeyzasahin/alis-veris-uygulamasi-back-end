package com.Spring.ShopApp.controller;

import java.util.HashMap;
import com.Spring.ShopApp.JWTConfiguration.AuthManager;
import com.Spring.ShopApp.JWTConfiguration.JwtTokenProvider;
import com.Spring.ShopApp.JWTConfiguration.UserPrincipal;
import com.Spring.ShopApp.controller.RequestPojo.ApiResponse;
import com.Spring.ShopApp.controller.RequestPojo.LoginRequest;
import com.Spring.ShopApp.model.User;
import com.Spring.ShopApp.service.UserServices.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LoginController {
	@Autowired
	UserDetailsService userDetailservice;
	@Autowired
	UserService userservice;
	@Autowired
	AuthManager authManager;
	@Autowired
	JwtTokenProvider tokenProvider;

	/*
	 * @Controller public class LoginController {
	 * 
	 * @GetMapping("/login") public String login(Model model, String error){ if
	 * (error != null) model.addAttribute("error",
	 * "Your username and password is invalid.");
	 * 
	 * return "login"; }
	 */

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("status") // post ve get
	public ResponseEntity<?> serverStatus() {
		return new ResponseEntity<>(new ApiResponse("Server is running.", ""), HttpStatus.OK);
	}

	@RequestMapping("login/user") // post ve get
	public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) {

		try {

			Authentication authentication = authManager.authenticate(
			new UsernamePasswordAuthenticationToken(loginRequest.getMobile(), loginRequest.getPassword()), loginRequest);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = tokenProvider.generateToken(authentication);
			JSONObject obj = this.getUserResponse(token);
			if (obj == null) {
				throw new Exception("Error!");
			}

			return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Error!", e);
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}

	}

	private JSONObject getUserResponse(String token) {

		try {
			User user = userservice.getUserDetailById(_getUserId());
			HashMap<String, String> response = new HashMap<String, String>();
			response.put("user_id", "" + _getUserId());
			response.put("email", user.getEmail());
			response.put("name", user.getName());
			response.put("mobile", user.getMobile());

			JSONObject obj = new JSONObject();

			obj.put("user_profile_details", response);
			obj.put("token", token);
			return obj;
		} catch (Exception e) { // kullaici adının okunmasinde hata
			logger.info("Error!", e);
		}
		return null;
	}

	private long _getUserId() {
		logger.info("user id vaildating. " + SecurityContextHolder.getContext().getAuthentication());
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("(LoginController)user id is " + userPrincipal.getId());
		return userPrincipal.getId();
	}

}
