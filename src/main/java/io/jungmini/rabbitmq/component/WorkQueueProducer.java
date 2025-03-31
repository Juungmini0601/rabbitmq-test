package io.jungmini.rabbitmq.component;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import io.jungmini.rabbitmq.config.RabbitMQConfig;

@Component
public class WorkQueueProducer {
    private final RabbitTemplate rabbitTemplate;

    public WorkQueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendWorkQueue(String workQueueMessage, int duration) {
        String message = workQueueMessage + "|" + duration;
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);
        System.out.println("Sent workqueue " + message);
    }

}