package io.jungmini.rabbitmq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 31.
 */
@Controller
public class HomeController {

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("message", "Welcome to RabbitMQ Sample!");
		return "home";
	}
}
