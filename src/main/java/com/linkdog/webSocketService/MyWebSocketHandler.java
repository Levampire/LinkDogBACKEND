package com.linkdog.webSocketService;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyWebSocketHandler extends AbstractWebSocketHandler implements HandlerInterceptor {

    // 这里使用一个map来维护全部客户端用户
    private static Map<String, WebSocketSession> userMap = new ConcurrentHashMap<>();
    private String projectName;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 当WebSocket连接建立成功时调用
        this.projectName = getParam(String.valueOf(session.getUri()), "projectName");
        userMap.put(projectName, session);
        session.sendMessage(new TextMessage(projectName + ":连接已建立,脚本即将开始执行"));
    }

    /**
     * 给project推送日志
     */
    public void sendMessageToProject(String projectName, String message) throws IOException {
        WebSocketSession session = userMap.get(projectName);
        if (session != null) {
            session.sendMessage(new TextMessage(message));
        }

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 处理接收到的WebSocket消息
        String payload = message.getPayload();
        System.out.println("接收到消息：" + session);

        // 发送回复消息给客户端
        String replyMessage = "收到消息：" + session;
        session.sendMessage(new TextMessage(session.getId()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 当WebSocket连接关闭时调用
        System.out.println("WebSocket连接已关闭");
    }

    public static String getParam(String url, String name) {

        if (url == null) {
            return null;
        }
        url = url.trim();
        if (url.equals("")) {
            return null;
        }
        String[] urlParts = url.split("\\?");
        //没有参数
        if (urlParts.length == 1) {
            return null;
        }
        //有参数
        String[] params = urlParts[1].split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue[0].equals(name)) {
                return keyValue[1];
            }
        }
        return null;
    }
}
