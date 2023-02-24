package com.learning.spring.miniproject.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OutOfStockException extends Exception{
	public OutOfStockException(String errorMessage) {  
	    super(errorMessage);  
	    }  
}
