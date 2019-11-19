package com.zetcode;

public class Asteroid extends Sprite {
	private final int dx = -1;
	
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
		
		if(x < -50) x = 650;
	}
}
