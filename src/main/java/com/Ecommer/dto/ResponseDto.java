package com.Ecommer.dto;

public class ResponseDto {

	private String status;
	private String message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ResponseDto(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	
	
}
