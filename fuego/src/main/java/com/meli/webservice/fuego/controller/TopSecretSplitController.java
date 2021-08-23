package com.meli.webservice.fuego.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meli.webservice.fuego.model.SatelliteSplit;
import com.meli.webservice.fuego.service.TopSecretSplitService;
import com.meli.webservice.fuego.service.TopSecretSplitServiceImpl;

@RestController
public class TopSecretSplitController {
	
	@Autowired
	TopSecretSplitService topSecretSplitService; 
	
	@RequestMapping(method = RequestMethod.GET, path = "/topsecret_split/{name}")
	public String topSecretSplit(@PathVariable String name) {
		topSecretSplitService.receiveOneSatellite(new SatelliteSplit());
		return "Hello"+name;
	}
}