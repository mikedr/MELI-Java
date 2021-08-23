package com.meli.webservice.fuego.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.meli.webservice.fuego.model.Satellites;

@Component
public class TopSecretServiceImpl implements TopSecretService{
	
	@Value("${satellite.position.x.kenobi}")
	float xPositionKenobi;
	
	@Value("${satellite.position.y.kenobi}")
	float yPositionKenobi;
	
	@Value("${satellite.position.x.skywalker}")
	float xPositionSkywalker;
	
	@Value("${satellite.position.y.skywalker}")
	float yPositionSkywalker;
	
	@Value("${satellite.position.x.sato}")
	float xPositionSato;
	
	@Value("${satellite.position.y.sato}")
	float yPositionSato;
	
	@Override
	public float[] getLocation(float... distances) {
		float[] enemyPosition = new float[2];
		float distanceKenobi = distances[0];
		float distanceSkywaler = distances[1];
		float distanceSato = distances[2];
		float term01 = (float) (Math.pow(distanceSkywaler, 2) * (2*yPositionSato - 2*yPositionKenobi));//pow(d2,2)*(2*b3-2*b1)
		float term02 = (float) (Math.pow(distanceKenobi, 2) * (2*yPositionSato - 2*yPositionKenobi));//pow(d1,2)*(2*b3-2*b1)
		float term03 = (float) (Math.pow(xPositionKenobi, 2) * (2*yPositionSato - 2*yPositionKenobi));//pow(a1,2)*(2*b3-2*b1)
		float term04 = (float) (Math.pow(yPositionKenobi, 2) * (2*yPositionSato - 2*yPositionKenobi));//pow(b1,2)*(2*b3-2*b1)
		float term05 = (float) (Math.pow(xPositionSkywalker, 2) * (2*yPositionSato - 2*yPositionKenobi));//pow(a2,2)*(2*b3-2*b1)
		float term06 = (float) (Math.pow(yPositionSkywalker, 2) * (2*yPositionSato - 2*yPositionKenobi));//pow(b2,2)*(2*b3-2*b1)
		float term07 = (float) (Math.pow(distanceKenobi, 2) * (2*yPositionSkywalker - 2*yPositionKenobi));//pow(d1,2)*(2*b2-2*b1)
		float term08 = (float) (Math.pow(distanceSato, 2) * (2*yPositionSkywalker - 2*yPositionKenobi));//pow(d3,2)*(2*b2-2*b1)
		float term09 = (float) (Math.pow(xPositionKenobi, 2) * (2*yPositionSkywalker - 2*yPositionKenobi));//pow(a1,2)*(2*b2-2*b1)
		float term10 = (float) (Math.pow(yPositionKenobi, 2) * (2*yPositionSkywalker - 2*yPositionKenobi));//pow(b1,2)*(2*b2-2*b1)
		float term11 = (float) (Math.pow(xPositionSato, 2) * (2*yPositionSkywalker - 2*yPositionKenobi));//pow(a3,2)*(2*b2-2*b1)
		float term12 = (float) (Math.pow(yPositionSato, 2) * (2*yPositionSkywalker - 2*yPositionKenobi));//pow(b3,2)*(2*b2-2*b1)
		float term13 = (4*yPositionSato*xPositionKenobi-4*yPositionKenobi*xPositionKenobi);//4*b3*a1-4*b1*a1
		float term14 = (4*yPositionSato*xPositionSkywalker-4*yPositionKenobi*yPositionSkywalker);//4*b3*a2-4*b1*a2
		float term15 = (4*yPositionSkywalker*xPositionKenobi-4*yPositionKenobi*xPositionKenobi);//4*b2*a1-4*b1*a1
		float term16 = (4*yPositionSkywalker*xPositionSato-4*yPositionKenobi*xPositionSato);//4*b2*a3-4*b1*a3
		float dividend = term01 - term02 + term03 + term04 - term05 - term06 + term07 - term08 - term09 - 
				term10 + term11 + term12;
		float divisor = term13 - term14 - term15 + term16;
		float x = Math.round(dividend/divisor);
		float y = Math.round((float) (Math.pow(distanceKenobi, 2)) - (float) (Math.pow(distanceSato, 2)) +
				2*x*xPositionKenobi - (float) (Math.pow(yPositionKenobi,2)) - 
						(float) (Math.pow(yPositionKenobi, 2)) - 2*x*xPositionSato +
								(float) (Math.pow(xPositionSato, 2)) + (float) (Math.pow(xPositionSato, 2)))/(2*yPositionSato-2*yPositionKenobi);
		/*
		(pow(d1,2)-pow(d3,2)
				+2*X*a1-pow(a1,2)-
					pow(b1,2)-2*X*a3+
						pow(a3,2)+pow(b3,2))/(2*b3-2*b1)
		*/
		enemyPosition[0] = x;
		enemyPosition[1] = y;
		return enemyPosition;
	}

	@Override
	public boolean positionValidator(Satellites satellites, float x, float y) {
		float distanceKenobi = satellites.getSatellites()[0].getDistance();
		float distanceSkywaler = satellites.getSatellites()[1].getDistance();
		float distanceSato = satellites.getSatellites()[2].getDistance();
		float dist1 = (float) Math.sqrt((float) (Math.pow(x-xPositionKenobi, 2))+(float) (Math.pow(y-yPositionKenobi, 2)));
		boolean firstVal = (dist1-distanceKenobi)<=1;
		float dist2 = (float) Math.sqrt((float) (Math.pow(x-xPositionSkywalker, 2))+(float) (Math.pow(y-yPositionSkywalker, 2)));
		boolean secondVal = (dist2-distanceSkywaler)<=1;
		float dist3 = (float) Math.sqrt((float) (Math.pow(x-xPositionSato, 2))+(float) (Math.pow(y-yPositionSato, 2)));
		boolean thirdVal = (dist3-distanceSato)<=1;
		return firstVal&&secondVal&&thirdVal;
	}

	@Override
	public String getMessage(String[]... messages) {
		StringBuilder message = new StringBuilder();
		for(String[] aArrayOfMessages : messages) {
			for(String aMessage : aArrayOfMessages) {
				if(!aMessage.equals("")) {
					message.append(aMessage+" ");
				}
			}
		}
		return message.toString();
	}

}
