package com.meli.webservice.fuego.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
	
	public static final String STATUS_MESSAGE = "I feel good";

	@RequestMapping(method = RequestMethod.GET, path = "/healthCheck")
	public String healthCheck() {
		return STATUS_MESSAGE;
	}
}
