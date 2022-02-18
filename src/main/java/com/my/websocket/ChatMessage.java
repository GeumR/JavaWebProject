package com.my.websocket;

import java.awt.TrayIcon.MessageType;

public class ChatMessage {
	private String chatRoomId;
	private String writer;
	private String message;
	private MessageType type;

	public ChatMessage() {
		super();
	}

	public ChatMessage(String chatRoomId, String writer, String message, MessageType type) {
		super();
		this.chatRoomId = chatRoomId;
		this.writer = writer;
		this.message = message;
		this.type = type;
	}

	public String getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ChatMessage [chatRoomId=" + chatRoomId + ", writer=" + writer + ", message=" + message + ", type="
				+ type + "]";
	}

}