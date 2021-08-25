package com.meli.webservice.fuego.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meli.webservice.fuego.exceptions.PositionNotCalculedException;
import com.meli.webservice.fuego.model.Position;
import com.meli.webservice.fuego.model.Satellite;
import com.meli.webservice.fuego.model.Satellites;
import com.meli.webservice.fuego.service.TopSecretService;

@RestController
public class TopSecretController {
	
	@Autowired
	TopSecretService topSecretService; 
	
	@Value("${message.position.fail.parameters}")
	String positionNotCalculatedWrongParameters;	
	
	@RequestMapping(method = RequestMethod.POST, path = "/topsecret/")
	public Position topSecretSplit(@RequestBody Satellites satellites) {
		Satellite[] allSatellites = satellites.getSatellites();
		float[] xy = topSecretService.getLocation(allSatellites[0].getDistance(),allSatellites[1].getDistance(),allSatellites[2].getDistance());
		boolean isValidPosition = topSecretService.positionValidator(satellites, xy[0], xy[1]);
		if(isValidPosition) {
			return new Position(xy[0], xy[1], topSecretService.getMessage(allSatellites[0].getMessage(),allSatellites[1].getMessage(),allSatellites[2].getMessage()));	
		} else {
			throw new PositionNotCalculedException(positionNotCalculatedWrongParameters);
		}
		
	}
}