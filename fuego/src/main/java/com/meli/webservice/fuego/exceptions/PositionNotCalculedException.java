package com.meli.webservice.fuego.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PositionNotCalculedException extends RuntimeException {

	public PositionNotCalculedException(String message) {
		super(message);
		setStackTrace(new StackTraceElement[0]);
	}

}
