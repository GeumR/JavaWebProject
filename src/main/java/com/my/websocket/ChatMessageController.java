package com.my.websocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import com.my.security.SecurityUserDetail;

@Controller
public class ChatMessageController {

	private final SimpMessagingTemplate template;

	private static List<SecurityUserDetail> sessionUsers = Collections
			.synchronizedList(new ArrayList<SecurityUserDetail>());

	@Autowired
	public ChatMessageController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@MessageMapping("/chat/join")
	public void join(ChatMessage message, Authentication authentication) {
		message.setMessage(message.getWriter() + "¥‘¿Ã ¿‘¿Â«œºÃΩ¿¥œ¥Ÿ.");
		SecurityUserDetail user = (SecurityUserDetail) authentication.getPrincipal();
		template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
	}

	@MessageMapping("/chat/message")
	public void message(ChatMessage message) {
		System.out.println(message);
		template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
	}
}