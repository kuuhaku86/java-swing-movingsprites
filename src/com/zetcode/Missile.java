package com.zetcode;

public class Missile extends Sprite{
	private final int BOARD_WIDTH = 700;
	private final int MISSILE_SPEED = 1;
	
	public Missile(int x, int y) {
		super(x,y);
		
		initMissile();
	}
	
	private void initMissile() {
		loadImage("src/resources/bullet.png",50,25);
		getImageDimensions();
	}
	
	public void move() {
		x += MISSILE_SPEED;
		
		if (x > BOARD_WIDTH) {
			visible = false;
		}
	}
}
