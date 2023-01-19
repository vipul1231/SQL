package com.example.perf.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BaseSystemException extends RuntimeException {

	private static final long serialVersionUID = -1107485149307890548L;
	
	private final List<ErrorMessage> errorList;
	
	public BaseSystemException() {
		this.errorList= new ArrayList<>();
	}
	
	public BaseSystemException(ErrorMessage errorMessage) {
		this();
		this.errorList.add(errorMessage);
	}
	
	public BaseSystemException(List<ErrorMessage> errorMessageList) {
		this();
		this.errorList.addAll(errorMessageList);
	} 
	
	public BaseSystemException(String message) {
		super(message);
		this.errorList= new ArrayList<>();
	} 
	
	public BaseSystemException(Throwable cause) {
		super(cause);
		this.errorList= new ArrayList<>();
	} 
	
	public BaseSystemException(String message, Throwable cause) {
		super(message,cause);
		this.errorList= new ArrayList<>();
	} 
	

}
