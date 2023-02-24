package com.learning.spring.miniproject.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends Exception{
		public ItemNotFoundException(String errorMessage) {  
	    super(errorMessage);  
	    }  
}
