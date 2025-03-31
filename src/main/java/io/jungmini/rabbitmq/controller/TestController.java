package io.jungmini.rabbitmq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 31.
 */
@RestController
public class TestController {
	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
