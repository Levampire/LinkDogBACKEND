package com.linkdog.webSocketService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig  implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 这里的路径就是访问时的路径
        registry.addHandler(myHandler(), "/linkDogWebsocket").setAllowedOrigins("*");
    }

    @Bean
    public MyWebSocketHandler myHandler() {
        return new MyWebSocketHandler();
    }
}
