package io.jungmini.rabbitmq.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jungmini.rabbitmq.component.Receiver;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 31.
 */
@Configuration
public class RabbitMQConfig {

	public static final String QUEUE_NAME = "test_queue";

	// RabbitMQ 큐 정의
	// false 파라미터는 큐가 휘발성인지 영속성인지를 지정하는 옵션
	// false로 설정하면 서버가 종료되거나 재시작될 때 큐의 메시지가 사라짐
	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}

	// 메시지를 주고 받기 위한 빈 생성
	// Sender에서 rabbitTemplate.convertAndSend를 이용해서 전송
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		return new RabbitTemplate(connectionFactory);
	}

	// 메세지를 비동기적으로 수신하기 위한 빈 특정 큐를 지속적으로 모니터링하고 수신하면 MessageListenerAdapter를 통해서 처리함
	// SimpleMessageListenerContainer가 없으면 폴링해서 메시지를 가져와야 함
	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUE_NAME);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	// 메세지가 들어 왔을때 호출할 메소드 이름을 지정하는 역할을 한다. 여기서 메소드 이름 틀리면 잘 안돌아감
	@Bean
	public MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
}
