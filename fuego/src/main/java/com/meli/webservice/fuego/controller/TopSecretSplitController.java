package com.meli.webservice.fuego.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopSecretSplitController {
	
	@RequestMapping(method = RequestMethod.GET, path = "/topsecret_split/{name}")
	public String topSecretSplit(@PathVariable String name) {
		return "Hello"+name;
	}
}