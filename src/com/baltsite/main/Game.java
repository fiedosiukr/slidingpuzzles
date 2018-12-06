package com.baltsite.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.baltsite.graphics.Screen;
import com.baltsite.states.GameStateManager;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage img;
	private int[] pixels;
	private BufferStrategy bs;
	private Graphics g;
	private Screen screen;
	
	private GameStateManager gsm;
	
	public Game() {
		if (thread == null) {
			thread = new Thread(this);
		}
		
		img = new BufferedImage(SlidingPuzzle.WIDTH, SlidingPuzzle.HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
	
		screen = new Screen(SlidingPuzzle.WIDTH, SlidingPuzzle.HEIGHT);
		
		gsm = new GameStateManager();
	}
	
	
	public synchronized void start() {
		if (!running)
			running = true;
		
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			bs = getBufferStrategy();
		}
		
		
		
		thread.start();
	}
	
	public synchronized void stop() {
		if (running)
			running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long currentTime, lastTime, sleepTime;
		double delta = 0, timePerTick = 1000000000 / 60.0;
		boolean ticked = false;
		
		lastTime = System.nanoTime();
		
		while (running) {
			currentTime = System.nanoTime();
			delta += ((currentTime - lastTime)) / timePerTick;
			lastTime = currentTime;
			
			while (delta >= 1) {
				update(delta);
				delta -= 1;
				ticked = true;
			}
			
			if (ticked) {
				render();
				ticked = false;
			}
			
			try {
				sleepTime = (long) ((lastTime - System.nanoTime() + timePerTick) / 1000000);
				if (sleepTime < 0) sleepTime = 0;
				Thread.sleep(sleepTime);
			} catch (Exception e) { e.printStackTrace(); } 
			
		}
	}
	
	private void update(double delta) {
		gsm.update(delta);
	}
	private void render() {	
		
		gsm.render(screen);
		
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		bs = getBufferStrategy();
		g = bs.getDrawGraphics();
		
		g.drawImage(img, 0, 0, SlidingPuzzle.WIDTH * SlidingPuzzle.SCALE, SlidingPuzzle.HEIGHT * SlidingPuzzle.SCALE, null);
		
		bs.show();
		g.dispose();
	}
}
