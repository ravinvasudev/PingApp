package com.docler.ping.helper;

public class AppException extends Exception {

	private static final long serialVersionUID = -2264323134422282271L;
	private String message;

	public AppException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
