package com.chat.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.chat.application.entity.User;
import com.chat.application.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	private final UserService userService;
	
	@MessageMapping("/user.addUser")
	@SendTo("/user/topic")
	public User saveUser(@Payload User user) {
		User saveUser = userService.saveUser(user);
		return saveUser;
	}
		

	@MessageMapping("/user.disconnectUser")
	@SendTo("/user/topic")
	public User disConnectUser(@Payload User user) {
		userService.disconnectUser(user);
		return user;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> findConnectedUser(){
		List<User> allConnectedUser = userService.findAllConnectedUser();
		return ResponseEntity.ok(allConnectedUser);
	}
}
