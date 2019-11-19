package com.zetcode;

public class Asteroid extends Sprite {
	private final int dx = -2;
	
	public Asteroid(int x,int y) {
		super(x,y);
		
		initAsteroid();
	}
	
	private void initAsteroid() {
		loadImage("src/resources/asteroid.png",60,60);
		getImageDimensions();
	}
	
	public void move() {
		x += dx;
		
		if(x <= 0) visible = false;
	}
}
