package soa;

import javax.jms.TextMessage;

public class Messaging {
	TextMessage message;

	public TextMessage getMessage() {
		return message;
	}

	public void setMessage(TextMessage message) {
		this.message = message;
	}
	
}
