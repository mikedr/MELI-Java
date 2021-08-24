package com.meli.webservice.fuego.service;

import org.springframework.stereotype.Component;

import com.meli.webservice.fuego.model.Satellite;
import com.meli.webservice.fuego.model.SatelliteSplit;
import com.meli.webservice.fuego.model.Satellites;

@Component
public class TopSecretSplitServiceImpl implements TopSecretSplitService{
	
	public void receiveOneSatellite(SatelliteSplit satelliteSplit, String name) {
		receivedSatellites.add(new Satellite(name,satelliteSplit.getDistance(),satelliteSplit.getMessage()));
	}

	@Override
	public boolean checkReceivedSatellites() {
		return (receivedSatellites.size()>=3);
	}

	@Override
	public String[][] getMessagesFromReceivedSatellites() {
		String[][] messagesFromReceivedSatellites = new String[receivedSatellites.size()][];
		for(int i = 0; i < receivedSatellites.size(); i++) {
			messagesFromReceivedSatellites[i] = receivedSatellites.get(i).getMessage();
		}
		return messagesFromReceivedSatellites;
	}

	@Override
	public Satellites getSatellites() {
		Satellite[] allSatellites = new Satellite[receivedSatellites.size()];
		Satellites satellites = new Satellites();
		for(int i = 0; i<receivedSatellites.size(); i++) {
			allSatellites[i] = receivedSatellites.get(i);
		}
		satellites.setSatellites(allSatellites);
		return satellites;
	}
}
