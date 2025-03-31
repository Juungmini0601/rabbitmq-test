package io.jungmini.rabbitmq.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import io.jungmini.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationSubscriber {

	public static final String CLIENT_URL = "/topic/notifications";
	// WebSocket으로 메시지를 전달하기 위한 Spring의 템플릿 클래스.
	private final SimpMessagingTemplate simpMessagingTemplate;

	public NotificationSubscriber(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	// 아 여기서 클라이언트로 던져줘야 되네
	// 이렇게 하면 리스너 조금 더 편하게 등록 할 수 있네
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void subscriber(String message) {
		log.info("[#] received notification: {}", message);
		simpMessagingTemplate.convertAndSend(CLIENT_URL, message);
	}

}