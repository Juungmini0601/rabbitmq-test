package io.jungmini.rabbitmq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jungmini.rabbitmq.component.Sender;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 31.
 */
@RestController
public class MessageController {

	private final Sender sender;

	public MessageController(Sender sender) {
		this.sender = sender;
	}

	@GetMapping("/send")
	public String sendMessage(@RequestParam String message) {
		sender.send(message);
		return "message sent";
	}
}
