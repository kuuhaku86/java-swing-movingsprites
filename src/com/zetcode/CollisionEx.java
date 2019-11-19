package com.zetcode;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class CollisionEx extends JFrame{
	
	public CollisionEx() {
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
			CollisionEx ex = new CollisionEx();
			ex.setVisible(true);
		});

	}

}
