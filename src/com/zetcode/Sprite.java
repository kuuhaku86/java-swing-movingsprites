package com.zetcode;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Sprite {
    protected int x = 40;
    protected int y = 60;
    protected int w;
    protected int h;
    protected boolean visible;
    protected Image image;
    
    public Sprite(int x, int y) {
    	this.x = x;
    	this.y = y;
    	visible = true;
    }
    
    protected void loadImage(String imageName,int height,int width) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
        image = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
    }
    
    protected void getImageDimensions() {
    	w = image.getWidth(null);
    	h = image.getHeight(null);
    }
    
    public Image getImage() {
        return image;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean isVisible() {
    	return visible;
    }
    
    public void setVisible(Boolean visible) {
    	this.visible = visible;
    }
}
