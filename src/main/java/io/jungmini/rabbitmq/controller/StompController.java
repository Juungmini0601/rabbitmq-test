package io.jungmini.rabbitmq.controller;

import static io.jungmini.rabbitmq.config.RabbitMQConfig.*;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import io.jungmini.rabbitmq.model.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompController {

	private final RabbitTemplate messagingTemplate;

	@MessageMapping("/send")
	public void sendMessage(NotificationMessage notificationMessage) {
		// stopm를 통해서 메시지를 받고 메시지 템플릿으로 메시지를 보냄
		String message = notificationMessage.getMessage();

		log.info("[#] message: {}", message);
		// 여기가 메세지 큐를 통해서 나가는 곳 아닌거 같은데
		messagingTemplate.convertAndSend(FANOUT_EXCHANGE, QUEUE_NAME, message);
	}
}