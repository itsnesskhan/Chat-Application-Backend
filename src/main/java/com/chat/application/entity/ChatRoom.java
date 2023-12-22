package com.chat.application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatRoom {

	@Id
	private Integer id;
	private String chatId;
	private String senderId;
	private String receiverId;
}
