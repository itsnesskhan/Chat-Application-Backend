package com.chat.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.application.entity.ChatMessage;
import com.chat.application.repository.ChatMessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

	@Autowired
	private final ChatMessageRepository chatMessageRepository;
	
	@Autowired
	private final ChatRoomService chatRoomService;
	
	public ChatMessage save(ChatMessage chatMessage) {
		String chatId = chatRoomService.getChatRoomId(
						chatMessage.getSenderId(),
						chatMessage.getReceiverId(),
						true)
				.orElseThrow(); // can throw any kind of a exception over here
		chatMessage.setChatId(chatId);
		return chatMessageRepository.save(chatMessage); 
	}
	
	public List<ChatMessage> findChatMessages(String senderId, String receiverId){
		
		Optional<String> chatRoomId = chatRoomService.getChatRoomId(senderId, receiverId, false);
		
		return chatRoomId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
	}

	
}
