package com.meli.webservice.fuego.service;

import com.meli.webservice.fuego.model.Satellites;

public interface TopSecretService {

	public float[] getLocation(float... distances);
	
	public String getMessage(String[]... messages);
	
	public boolean positionValidator(Satellites satellites, float x, float y);
}
