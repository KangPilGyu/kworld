package org.web.kworld.chat.vo;

import java.io.StringReader;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage>{

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public ChatMessage decode(String str) throws DecodeException {
		JsonObject jsonObject = Json.createReader(new StringReader(str)).readObject();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setType(jsonObject.getString("type"));
        chatMessage.setContext(jsonObject.getString("context"));
        chatMessage.setId(jsonObject.getString("id"));
        chatMessage.setDate(new Date());
        return chatMessage;
	}

	@Override
	public boolean willDecode(String s) {
		try {
		      // Check if incoming message is valid JSON
		      Json.createReader(new StringReader(s)).readObject();
		      return true;
		    } catch (Exception e) {
		      return false;
		    }
	}

}
