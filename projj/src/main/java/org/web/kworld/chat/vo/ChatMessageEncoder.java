package org.web.kworld.chat.vo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage>{

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public String encode(ChatMessage message) throws EncodeException {
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		return Json.createObjectBuilder()
					.add("type", message.getType())
					.add("context", message.getContext())
					.add("id", message.getId())
					.add("date",format.format(message.getDate())).build().toString();
	}
	
}
