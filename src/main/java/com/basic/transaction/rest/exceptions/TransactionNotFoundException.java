package com.basic.transaction.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -5570312733540628184L;
	
	public TransactionNotFoundException() {
        super();
    }
	
    public TransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public TransactionNotFoundException(String message) {
        super(message);
    }
    
    public TransactionNotFoundException(Throwable cause) {
        super(cause);
    }
	
	
}
