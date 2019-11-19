package com.zetcode;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main extends JFrame{
	
	public Main() {
		initUI();
	}
	
	private void initUI() {
		add(new Board());
		
//		setSize(600,500);
		setResizable(false);
		pack();
		
		setTitle("Space Shooter");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Main ex = new Main();
			ex.setVisible(true);
		});

	}

}
