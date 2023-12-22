package com.chat.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.application.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {

	Optional<ChatRoom> findBySenderIdAndReceiverId(String senderId, String receiverId);

	
}
