package com.sshs.core.websocket.handler;

import com.sshs.core.constant.Global;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Suny
 */
@Component
public class ChatWebSocketHandler implements WebSocketHandler {

    static Map<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>();
    static Map<String, String> names = new HashMap<String, String>();

    public static void login(WebSocketSession session) {
        sessions.put(session.getId(), session);
    }

    public static void logout(WebSocketSession session) {
        sessions.remove(session.getId());
    }

    @Override
    public List<String> getSubProtocols() {
        return null;
    }

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        if (!sessions.containsKey(webSocketSession.getId())) {
            sessions.put(webSocketSession.getId(), webSocketSession);
        }
        Flux<String> message = Flux.empty();
        // webSocketSession.send(Mono.just(webSocketSession.textMessage("sssssss")));
        Mono<Void> input = webSocketSession.receive().map(msg -> message.concatWith(Flux.just(msg.getPayloadAsText()))).then();

        if (message.toString().startsWith(Global.CHARACTER_COLON)) {
            //webSocketSession.getAttributes().put("name", message.getPayload().substring(2));
            names.put(webSocketSession.getId(), message.toString().substring(2));
            //return ;
        }
        Mono<Void> output = null;
        for (WebSocketSession s : sessions.values()) {
            String name = names.get(webSocketSession.getId());
            if (s.getId().equals(webSocketSession.getId())) {
                name = "我";
            }
            String finalName = name;
            output = s.send(Mono.just(s.textMessage(finalName + " 说：" + message)));
        }
        // webSocketSession.textMessage("服务端返回： ->" + msg.getPayloadAsText());
        return Mono.zip(input, output).then();
    }
}
