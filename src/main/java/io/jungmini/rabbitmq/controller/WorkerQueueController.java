package io.jungmini.rabbitmq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jungmini.rabbitmq.component.WorkQueueProducer;
import lombok.RequiredArgsConstructor;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 31.
 */
@RestController
@RequiredArgsConstructor
public class WorkerQueueController {

	private final WorkQueueProducer producer;

	@GetMapping("/workqueue")
	public String send(@RequestParam String message, @RequestParam int duration) {
		producer.sendWorkQueue(message, duration);
		return "Work queue sent = " + message + ", (" + duration + ")";
	}
}
