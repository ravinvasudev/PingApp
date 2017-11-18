package com.docler.ping.model;

/**
 * A class to handle operational statuses
 * 
 * @author Ravin Vasudev
 * @version 1.0
 */
public class Status {

	public enum Code {
		SUCCESS, FAILURE
	}

	private Code code;

	private String text;

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return String.format("Status [code=%s, text=%s]", code, text);
	}

}
