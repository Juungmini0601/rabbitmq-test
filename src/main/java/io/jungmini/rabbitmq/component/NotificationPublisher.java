package io.jungmini.rabbitmq.component;

import static io.jungmini.rabbitmq.config.RabbitMQConfig.*;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 31.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationPublisher {
	private final RabbitTemplate rabbitTemplate;

	public void send(String message) {
		log.info("[#] published notification: {}", message);
		rabbitTemplate.convertAndSend(FANOUT_EXCHANGE, message);
	}
}
