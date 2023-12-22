package com.chat.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chat.application.entity.Status;
import com.chat.application.entity.User;
import com.chat.application.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	public User saveUser(User user) {
		user.setStatus(Status.ONLINE);
	   return userRepository.save(user);
	}
	
	public void disconnectUser(User user) {
		User storedUser = userRepository.findById(user.getId()).orElse(null);
		if (storedUser!=null) {
			storedUser.setStatus(Status.OFFLINE);
			userRepository.save(storedUser);
		}
	}
	
	public List<User> findAllConnectedUser(){
		return userRepository.findAllByStatus(Status.ONLINE);
	}
}
