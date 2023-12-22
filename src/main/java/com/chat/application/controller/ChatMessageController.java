package com.chat.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.chat.application.entity.ChatMessage;
import com.chat.application.entity.ChatNotification;
import com.chat.application.services.ChatMessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

	@Autowired
	private final SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private final ChatMessageService chatMessageService;
	
	@MessageMapping("/chat")
	public ChatMessage saveMessage(@Payload ChatMessage chatMessage) {
		ChatMessage save = chatMessageService.save(chatMessage);
		simpMessagingTemplate.convertAndSendToUser(
				chatMessage.getReceiverId(),
				"/queue/messages",
				ChatNotification.builder()
				.senderId(chatMessage.getSenderId())
				.receiverId(chatMessage.getReceiverId())
				.content(chatMessage.getContent())
				.id(chatMessage.getId().toString())
				.build()
				);
		return chatMessage;
	}
	
	@GetMapping("/messages/{senderId}/{receiverId}")
	public ResponseEntity<List<ChatMessage>> findChatMessages(
			@PathVariable String senderId,
			@PathVariable String receiverId
			){
		return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, receiverId));
		
	}
}
