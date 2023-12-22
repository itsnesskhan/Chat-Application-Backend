package com.chat.application.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chat.application.entity.ChatRoom;
import com.chat.application.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

	private final ChatRoomRepository chatRoomRepository;
	
	Optional<String> getChatRoomId(String senderId, String receiverId, boolean createRoomIfNotExists){
		 return chatRoomRepository.findBySenderIdAndReceiverId(senderId, receiverId)
				 .map(ChatRoom::getChatId)
				 .or(()->{
					 if(createRoomIfNotExists) {
						 String chatId = createChatId(senderId, receiverId);
						 return Optional.of(chatId);
					 }
				
				  return Optional.empty();
				 });
	}

	private String createChatId(String senderId, String receiverId) {
		String chatId = String.format("%s_%s", senderId, receiverId);
		
		chatRoomRepository.save(
				ChatRoom.builder()
				.chatId(chatId)
				.senderId(senderId)
				.receiverId(receiverId)
				.build()
				);
		
		chatRoomRepository.save(
				ChatRoom.builder()
				.chatId(chatId)
				.senderId(receiverId)
				.receiverId(senderId)
				.build()
				);
		
		return chatId;
		
	}
	
}
