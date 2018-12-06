package com.baltsite.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class SlidingPuzzle {
	
	public static final int WIDTH = 640;
	public static final int SCALE = 2;
	public static final int HEIGHT = WIDTH / 16 * 9;

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		Dimension dim = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		frame.setPreferredSize(dim);
		frame.setMinimumSize(dim);
		frame.setMaximumSize(dim);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		Game game = new Game();
		frame.add(game);
		frame.pack();
		
		game.start();
		
		frame.setVisible(true);
	}
}
