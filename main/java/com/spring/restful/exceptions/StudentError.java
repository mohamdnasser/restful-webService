package com.spring.restful.exceptions;

public class StudentError {
	
	private int stutsCode;
	private String message;
	private long timeStamp;
	
	
	public int getStutsCode() {
		return stutsCode;
	}


	public void setStutsCode(int stutsCode) {
		this.stutsCode = stutsCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public long getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}


}
