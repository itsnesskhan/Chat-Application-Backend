package com.chat.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.application.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {

	List<ChatMessage> findByChatId(String chatId);
}
