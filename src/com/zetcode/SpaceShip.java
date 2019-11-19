package com.zetcode;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class SpaceShip extends Sprite{
    private int dx;
    private int dy;
    private List<Missile> missiles;

    public SpaceShip(int x, int y) {
        super(x,y);
    	
    	initSpaceShip();
    }
    
    public void initSpaceShip() {
    	missiles = new ArrayList<>();
    	
    	loadImage("src/resources/spaceship.png",60,120);
    	getImageDimensions();
    }

    public void move() {
        x += dx;
        y += dy;
    }
    
    public List<Missile> getMissiles() {
    	return missiles;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT) dx = -2;
        else if(key == KeyEvent.VK_RIGHT) dx = 2;
        else if(key == KeyEvent.VK_UP) dy = -2;
        else if(key == KeyEvent.VK_DOWN) dy = 2;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT) dx = 0;
        else if(key == KeyEvent.VK_RIGHT) dx = 0;
        else if(key == KeyEvent.VK_UP) dy = 0;
        else if(key == KeyEvent.VK_DOWN) dy = 0;
    }
    
    public void fire() {
    	missiles.add(new Missile(x + w, y + h / 2));
    }
}