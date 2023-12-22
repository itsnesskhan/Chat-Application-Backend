package com.chat.application.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").withSockJS();
//		on this endpoint we connect to server
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
//		prefix for the application
		registry.enableSimpleBroker("/user");
//		on this endpoint we will consume message
		registry.setUserDestinationPrefix("/user");
	}

	@Override
	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		DefaultContentTypeResolver contentTypeResolver = new DefaultContentTypeResolver();
		contentTypeResolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setObjectMapper(new ObjectMapper());
		converter.setContentTypeResolver(contentTypeResolver);
		messageConverters.add(converter);
		return false;
//		return false cause we don't want default converter
	}
	
	

	
}
