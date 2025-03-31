package io.jungmini.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 31.
 */
@EnableRabbit
@Configuration
public class RabbitMQConfig {

	public static final String QUEUE_NAME = "notificationQueue";
	public static final String FANOUT_EXCHANGE = "notificationExchange";

	// RabbitMQ 큐 정의
	// false 파라미터는 큐가 휘발성인지 영속성인지를 지정하는 옵션
	// false로 설정하면 서버가 종료되거나 재시작될 때 큐의 메시지가 사라짐
	@Bean
	public Queue notificationQueue() {
		return new Queue(QUEUE_NAME, false); // 메시지는 volatile로 설정
	}

	// Fanout Exchange
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(FANOUT_EXCHANGE);
	}

	// BindingBuilder.bind().to() 를 통해 큐와 익스체인지를 연결
	@Bean
	public Binding bindNotification(Queue notificationQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(notificationQueue).to(fanoutExchange);
	}
}
