//package com.chaoxing.oa.websocket;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.Map;
//import java.util.concurrent.ConcurrentSkipListMap;
//
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//
//import com.chaoxing.oa.entity.page.websocket.Messages;
//import com.chaoxing.oa.util.system.ResourceUtil;
//import com.google.gson.Gson;
//
//
//public class MyWebsocketHandller implements WebSocketHandler {
//	public static final Map<Long, WebSocketSession> userChatSession;
//	
//	static{
//		userChatSession = new ConcurrentSkipListMap<Long, WebSocketSession>();
//	}
//	
//	private static synchronized void addChatSession(WebSocketSession session, Long uid){
//		if(null == userChatSession.get("uid")){
//			userChatSession.put(uid, session);
//		}
//	}
//	
//	private static synchronized void removeSession(WebSocketSession session, Long uid){
//		
//	}
//	
//	/**
//	 * 连接关闭
//	 */
//	@Override
//	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
//		Long uid = (Long) webSocketSession.getAttributes().get("uid");
//		removeSession(webSocketSession, uid);
//	}
//
//	/**
//	 * 建立连接之后，在userChatSession里面添加用户，userChatSession起到总体管理作用
//	 */
//	@Override
//	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
//		Long uid = (Long) webSocketSession.getAttributes().get("uid");
//		addChatSession(webSocketSession, uid);
//	}
//
//	@Override
//	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
//		if(webSocketMessage.getPayloadLength() == 0) return ;
//		webSocketSession.getId();
//		Messages message = new Gson().fromJson(webSocketMessage.getPayload().toString(), Messages.class);
//		message.setDate(new Date());
//		if(message.getMsg_type() == Messages.NORMAL_MESSAGES){
//			sendMessageToUser(message);
//		}
//	}
//
//	@Override
//	public void handleTransportError(WebSocketSession webSocketSession, Throwable e) throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public boolean supportsPartialMessages() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
//	/**
//	 * 给某个用户发送消息
//	 * 
//	 * @param userName
//	 * @param message
//	 * @throws IOException
//	 */
//	public void sendMessageToUser(Long uid, TextMessage message)
//			throws IOException {
//		WebSocketSession session = userChatSession.get(uid);
//		if (session != null && session.isOpen()) {
//			session.sendMessage(message);
//		}
//	}
//	public void sendMessageToUser(Messages message) throws IOException {
//		
////		WebSocketSession session = userChatSession.get(uid);
////		if (session != null && session.isOpen()) {
////			session.sendMessage(message);
////		}
//	}
//
//}
