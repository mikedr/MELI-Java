package com.meli.webservice.fuego.service;

import java.util.ArrayList;
import java.util.List;

import com.meli.webservice.fuego.model.Satellite;
import com.meli.webservice.fuego.model.SatelliteSplit;
import com.meli.webservice.fuego.model.Satellites;

public interface TopSecretSplitService {
	
	List<Satellite> receivedSatellites = new ArrayList<Satellite>();
	
	public void receiveOneSatellite(SatelliteSplit satelliteSplit, String name);
	
	public boolean checkReceivedSatellites();
	
	public String[][] getMessagesFromReceivedSatellites();
	
	public Satellites getSatellites();
}
