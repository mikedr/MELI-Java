package com.meli.webservice.fuego.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meli.webservice.fuego.model.Position;
import com.meli.webservice.fuego.model.Satellite;
import com.meli.webservice.fuego.model.SatelliteSplit;
import com.meli.webservice.fuego.service.TopSecretService;
import com.meli.webservice.fuego.service.TopSecretSplitService;

@RestController
public class TopSecretSplitController {
	
	@Autowired
	TopSecretSplitService topSecretSplitService;
	
	@Autowired
	TopSecretService topSecretService; 
	
	@Value("${message.position.fail.parameters}")
	String positionNotCalculatedWrongParameters;
	
	@Value("${message.position.fail.lackSatellites}")
	String positionNotCalculatedLackSatellites;
	
	@RequestMapping(method = RequestMethod.POST, path = "/topsecret_split/{name}")
	public void topSecretSplit(@RequestBody SatelliteSplit satelliteSplit, @PathVariable String name) {
		topSecretSplitService.receiveOneSatellite(satelliteSplit,name);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/topsecret_split/")
	public Position topSecretSplit() {
		if(topSecretSplitService.checkReceivedSatellites()) {
			Satellite[] allSatellites = topSecretSplitService.getSatellites().getSatellites();
			float[] xy = topSecretService.getLocation(allSatellites[0].getDistance(),allSatellites[1].getDistance(),allSatellites[2].getDistance());
			if(topSecretService.positionValidator(topSecretSplitService.getSatellites(), xy[0], xy[1])) {
				return new Position(xy[0], xy[1], topSecretService.getMessage(topSecretSplitService.getMessagesFromReceivedSatellites()));	
			} else {
				throw new PositionNotCalculedException(positionNotCalculatedWrongParameters);
			}
		} else {
			throw new PositionNotCalculedException(positionNotCalculatedLackSatellites);
		}
	}
}