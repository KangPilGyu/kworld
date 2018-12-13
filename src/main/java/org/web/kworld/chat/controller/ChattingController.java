package org.web.kworld.chat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web.kworld.chat.vo.ChatMessage;
import org.web.kworld.chat.vo.ChatMessageDecoder;
import org.web.kworld.chat.vo.ChatMessageEncoder;

@Controller
@ServerEndpoint(value="/echo", 
encoders = {ChatMessageEncoder.class},
decoders = {ChatMessageDecoder.class})
public class ChattingController {
	
	private static int increaseNum = 1;
	private static final List<Session> sessionList = new ArrayList<Session>();
	private static final Logger logger = LoggerFactory.getLogger(ChattingController.class);


	@RequestMapping(value="/chat")
	public String getChatViewPage(Model model) {
		model.addAttribute("cnt", increaseNum);
		return "chat.chat";
	}

	@OnOpen
	public void onOpen(Session session) {
		// 소켓 Connection Open
		logger.info("Open session id" + session.getId());
		sessionList.add(session);
		increaseNum++;
		
		try {
			final Basic basic = session.getBasicRemote();
			ChatMessage message=new ChatMessage();
			message.setType("id");
			message.setContext(session.getId());
			message.setId("id");
			message.setDate(new Date());
			basic.sendObject(message);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}


	@OnMessage
	public void onMessage(ChatMessage message, Session session) {
		String type = message.getType();
		logger.info("onMessage - "+type);
		
		if(type=="username") {
			String nickname = (String) session.getUserProperties().get("username");	
			if(nickname==null) {
				session.getUserProperties().put("username", message.getContext());
				sendAllalert(session,"입장");
				sendAllSessionToList();
			}
		}else if(type=="send") {
			sendAllSessionToMessage(session,message);
		}

	}

	private void sendAllSessionToMessage(Session self,ChatMessage message) {
		message.setType("receive");
		message.setId((String)self.getUserProperties().get("username"));
		for (Session session : sessionList) {
			if(!self.getId().equals(session.getId())) {
				try {
					session.getBasicRemote().sendObject(message);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}

	private void sendAllSessionToList() {
		ChatMessage message = new ChatMessage();
		message.setType("list");
		message.setId("no");
		message.setDate(new Date());

		JsonArrayBuilder builder =  Json.createArrayBuilder();
		for (Session session : sessionList) {
			builder.add(
					Json.createObjectBuilder()
					.add("num", session.getId())
					.add("name", (String) session.getUserProperties().get("username"))
					.build()
					);
		}
		message.setContext(builder.build().toString());

		for (Session session : sessionList) {
			try {
				session.getBasicRemote().sendObject(message);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (EncodeException e) {
				e.printStackTrace();
			}
		}		
	}

	private void sendAllalert(Session self, String enterClose) {
		ChatMessage message = new ChatMessage();
		message.setType("alert");
		message.setId("no");
		message.setDate(new Date());
		message.setContext(self.getUserProperties().get("username")+"님이 "+enterClose+"하셨습니다");
		
		for (Session session : sessionList) {
			try {
				session.getBasicRemote().sendObject(message);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (EncodeException e) {
				e.printStackTrace();
			}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		logger.info("Session "+session.getId()+" has ended");
		sessionList.remove(session);
		sendAllalert(session,"퇴장");
		sendAllSessionToList();
		
	}


}
