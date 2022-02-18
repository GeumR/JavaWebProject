package com.my.websocket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatHandler extends TextWebSocketHandler {

	private static Map<String, WebSocketSession> sessionUsers = new HashMap<String, WebSocketSession>();

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("@@@@@@@@@@@@@@@@@@@handleMessage@@@@@@@@@@@@@@@@@@@@@");
		super.handleMessage(session, message);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("@@@@@@@@@@@@@@@@@@@afterConnectionEstablished@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(session.getId() + " ¿¬°á µÊ.");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("@@@@@@@@@@@@@@@@@@@afterConnectionClosed@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(session.getId() + " ¿¬°á ²÷±è.");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("@@@@@@@@@@@@@@@@@@@handleTextMessage@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(session.getId() + " ·ÎºÎÅÍ [" + message.getPayload() + "] ¹ÞÀ½");
		session.sendMessage(new TextMessage("echo : " + message.getPayload()));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("@@@@@@@@@@@@@@@@@@@handleTransportError@@@@@@@@@@@@@@@@@@@@@");
		super.handleTransportError(session, exception);
	}
}
