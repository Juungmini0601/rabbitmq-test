package io.jungmini.rabbitmq.model;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 31.
 */
public class NotificationMessage {
	private final String message;

	// 기본 생성자 (필수)
	public NotificationMessage() {
		message = "";
	}

	// 선택
	public NotificationMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
