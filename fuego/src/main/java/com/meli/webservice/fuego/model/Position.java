package com.meli.webservice.fuego.model;

public class Position {

	private float x;
	private float y;
	private String message;
	
	public Position(float x, float y, String message) {
		super();
		this.x = x;
		this.y = y;
		this.message = message;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
