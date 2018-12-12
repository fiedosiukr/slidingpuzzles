package com.baltsite.objects;

import com.baltsite.graphics.Bitmap;
import com.baltsite.graphics.Screen;

public class Tile {
	private Bitmap bitmap;
	private int position;
	private double x, y;
	private double xDest, yDest;
	private boolean moving;
	private int gridSize;
	
	private static final double tween = 0.4;
	
	public Tile(Bitmap bitmap, int gridSize, int position) {
		this.bitmap = bitmap;
		this.gridSize = gridSize;
		this.position = position;
		moving = false;
		x = xDest = (int) ((position % gridSize) * 100);
		y = yDest = (int) ((position / gridSize) * 100);
	}
	
	
	public void update() {
		x += (xDest - x) * tween;
		y += (yDest - y) * tween;
		
		if ((xDest - x) < 1 && (xDest - x) > -1 && (yDest - y) < 1 && (yDest- y > -1)) {
			moving = false;
			x = xDest;
			y = yDest;
		}
		
		
	}
	
	public void setPosition(int position) {
		this.position = position;
		xDest = (int) ((position % gridSize) * 100);
		yDest = (int) ((position / gridSize) * 100);
		moving = true;
	}
	
	public int getPosition() {
		return position;
	}
	
	
	public boolean isMoving() {
		return moving;
	}
	
	public void render(Screen screen) {
		screen.draw(bitmap, (int)x, (int)y);
	}
	
}
