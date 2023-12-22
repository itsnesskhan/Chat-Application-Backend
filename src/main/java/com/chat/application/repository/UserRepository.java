package com.chat.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.application.entity.Status;
import com.chat.application.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findAllByStatus(Status online);

}
