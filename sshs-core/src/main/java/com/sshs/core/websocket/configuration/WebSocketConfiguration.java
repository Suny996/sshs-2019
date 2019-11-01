package com.sshs.core.websocket.configuration;

import com.sshs.core.websocket.handler.ChatWebSocketHandler;
import com.sshs.core.websocket.handler.DefaultWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * websocket配置
 * 
 * @author Suny
 * @date 2018-01-23
 */
@Configuration
public class WebSocketConfiguration {

	@Autowired
	@Bean
	public HandlerMapping webSocketMapping(final DefaultWebSocketHandler handler) {
		/**
		 * 使用 map 指定 WebSocket 协议的路由，路由为 ws://localhost:8080/echo
		 */
		final Map<String, WebSocketHandler> map = new HashMap<>();
		map.put("/wstest", handler);

		/**
		 * SimpleUrlHandlerMapping 指定了 WebSocket 的路由配置
		 */
		final SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
		mapping.setUrlMap(map);
		return mapping;
	}
	@Autowired
	@Bean
	public HandlerMapping webSocketMapping2(final ChatWebSocketHandler handler) {
		/**
		 * 使用 map 指定 WebSocket 协议的路由，路由为 ws://localhost:8080/echo
		 */
		final Map<String, WebSocketHandler> map = new HashMap<>();
		map.put("/chatting", handler);

		/**
		 * SimpleUrlHandlerMapping 指定了 WebSocket 的路由配置
		 */
		final SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
		mapping.setUrlMap(map);
		return mapping;
	}
	/**
	 * WebSocketHandlerAdapter 负责将 EchoHandler 处理类适配到 WebFlux 容器中
	 * @return
	 */
	@Bean
	public WebSocketHandlerAdapter handlerAdapter() {
		return new WebSocketHandlerAdapter();
	}
}