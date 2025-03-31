package io.jungmini.rabbitmq.component;

import org.springframework.stereotype.Component;

@Component
public class Receiver {
    // 여기 메서드명이 MessageListenerAdapter에서 틀리면 안됨 조심!!
    public void receiveMessage(String message) {
        System.out.println("[#] Received: " + message);
    }
}