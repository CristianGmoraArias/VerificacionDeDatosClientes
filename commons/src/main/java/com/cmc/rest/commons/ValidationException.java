package com.cmc.rest.commons;

import java.io.IOException;

public class ValidationException extends IOException  {

	public ValidationException(String message){
		super(message);
	}
	
}
