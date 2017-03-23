//package com.chaoxing.oa.websocket;
//
//import javax.annotation.Resource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
//
///**
// * 配置websocket拦截处理
// * @author dengxf
// *
// */
////@Configuration
////@EnableWebSocket
//public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
//
//	@Resource
//	MyWebsocketHandller handler;
//
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
////		registry.addHandler(handler, "/chat.action").addInterceptors(new HandShake());
////
////		registry.addHandler(handler, "/chat.action/sockjs").addInterceptors(new HandShake()).withSockJS();
//	}
//	
//	/**
//	 * 配置聊天区缓存
//	 * @return
//	 */
//	@Bean
//    public ServletServerContainerFactoryBean createWebSocketContainer() {
//        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
//        container.setMaxTextMessageBufferSize(8192);
//        container.setMaxBinaryMessageBufferSize(8192);
//        return container;
//    }
//	
//
//}