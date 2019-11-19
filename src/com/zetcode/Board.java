package com.zetcode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{
	private Timer timer;
	private SpaceShip spaceShip;
	private final int DELAY = 10;
	private final int ICRAFT_X = 40;
	private final int ICRAFT_Y = 60;
	public static List<Asteroid> asteroids;
	private boolean inGame;
	
	private final int[][] pos = {
	        {2380, 29}, {2500, 59}, {1380, 350},
	        {780, 109}, {580, 139}, {680, 239},
	        {790, 259}, {760, 50}, {790, 150},
	        {980, 300}, {560, 45}, {510, 70},
	        {930, 159}, {590, 80}, {530, 60},
	        {940, 59}, {990, 379}, {920, 200},
	        {900, 445}, {660, 50}, {540, 450},
	        {810, 220}, {860, 380}, {740, 180},
	        {820, 128}, {490, 170}, {700, 400}
	    };
	
	public Board() {
		initBoard();
	}
	
	private void initBoard() {
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		inGame = true;
		
		setPreferredSize(new Dimension(600, 500));
		
		spaceShip = new SpaceShip(ICRAFT_X,ICRAFT_Y);
		
		initAsteroids();
		
		timer = new Timer(DELAY, this);
		
		timer.start();
		
	}
	
	public void initAsteroids() {
		asteroids = new ArrayList<>();
		
		for (int[] p : pos) {
			asteroids.add(new Asteroid(p[0], p[1]));
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(inGame) doDrawing(g);
		else drawGameOver(g);
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if(spaceShip.isVisible()) g2d.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
		
		List<Missile> missiles = spaceShip.getMissiles();
		
		for (Missile missile : missiles) {
			if(missile.isVisible()) g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
		}
		
		for (Asteroid asteroid : asteroids) {
			if(asteroid.isVisible()) g2d.drawImage(asteroid.getImage(), asteroid.getX(), asteroid.getY(), this);
		}
		
		g.setColor(Color.WHITE);
		g.drawString("Asteroids left:" + asteroids.size(), 5, 15);
	}
	
	private void drawGameOver(Graphics g) {
		String msg = "Game Over";
		Font small = new Font("Helvetice", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);
		
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (600 - fm.stringWidth(msg)) / 2, (500 - fm.stringWidth(msg)) / 2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		inGamee();
		
		updateMissiles();
		updateSpaceShip();
		updateAsteroid();
		
		checkCollisions();
		
		repaint();
	}
	
	
	public void inGamee() {
		if(!inGame) timer.stop();
	}
	
	private void updateMissiles() {
		List<Missile> missiles = spaceShip.getMissiles();
		
		for (int i = 0; i < missiles.size(); i++) {
			Missile missile = missiles.get(i);
			
			if(missile.isVisible()) missile.move();
			else missiles.remove(i);
		}
	}
	
	public void updateSpaceShip() {
		if(spaceShip.isVisible()) spaceShip.move();
	}
	
	public void updateAsteroid() {
		if (asteroids.isEmpty()) {
			inGame = false;
			return;
		}
		
		for (int i = 0; i < asteroids.size(); i++) {
			Asteroid asteroid = asteroids.get(i);
			
			if(asteroid.isVisible()) asteroid.move();
			else asteroids.remove(i);
		}
	}
	
	public void checkCollisions() {
		for(Asteroid asteroid : asteroids) {
			if((spaceShip.getX() + 60 == asteroid.getX() - 30) && (spaceShip.getY() + 30 > asteroid.getY() && spaceShip.getY() - 30 < asteroid.getY())) {
				spaceShip.setVisible(false);
				asteroid.setVisible(false);
				inGame = false;
			}
		}
		
		List<Missile> ms = spaceShip.getMissiles();
		
		for(Missile m : ms) {
			
			for(Asteroid asteroid : asteroids) {
				
				if((m.getX() - 25 < asteroid.getX() + 30 && m.getX() - 25 > asteroid.getX() - 30) && (m.getY() < asteroid.getY() + 30 && m.getY() > asteroid.getY() - 30)) {
					m.setVisible(false);
					asteroid.setVisible(false);
					break;
				}
			}
		}
	}
	
	private class TAdapter extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			spaceShip.keyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			spaceShip.keyPressed(e);
		}
	}
}
