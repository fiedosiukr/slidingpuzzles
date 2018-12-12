package com.baltsite.objects;

import java.awt.event.KeyEvent;

import com.baltsite.graphics.Bitmap;
import com.baltsite.graphics.Screen;

public class Grid {
	private Tile tiles[][];
	private int gridSize;
	private Bitmap photo;
	private int freeTile;
	private int direction;
	
	public Grid(String path, int gridSize) {
		photo = new Bitmap(path);
		this.gridSize = gridSize;
		
		tiles = new Tile[gridSize][gridSize];
		
		freeTile = 15;
		
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (i == 3 && j == 3) continue;
				tiles[j][i] = new Tile(photo.cut(j * 100, i * 100, 100, 100), gridSize, j + i * gridSize);
			}
		}
		
	}
	
	public void update() {
		
		
		moveTile();
		
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (i == gridSize - 1 && j == gridSize - 1) continue;
				tiles[j][i].update();
			}
		}
		
	}
	
	private void moveTile() {
		int tile;
		switch(direction) {
		case 1:
			tile = freeTile + gridSize;
			if (tile >= 0 && tile < gridSize * gridSize) {
				Tile t = getTileByPosition(tile);
				t.setPosition(freeTile);
				freeTile = tile;
			}
			break;
		case 2:
			tile = freeTile - 1;
			if (tile % 4 >= 0 && tile % 4 < 3) {
				Tile t = getTileByPosition(tile);
				t.setPosition(freeTile);
				freeTile = tile;
			}
			break;
		case 3:
			tile = freeTile - gridSize;
			if (tile >= 0 && tile < gridSize * gridSize) {
				Tile t = getTileByPosition(tile);
				t.setPosition(freeTile);
				freeTile = tile;
			}
			break;
		case 4:
			tile = freeTile + 1;
			if (tile % 4 > 0 && tile % 4 <= 3) {
				Tile t = getTileByPosition(tile);
				t.setPosition(freeTile);
				freeTile = tile;
			}
			break;
		}
		
		direction = 0;
	}
	
	private Tile getTileByPosition(int position) {
		Tile tile = null;
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (i == 3 && j == 3) continue;
				if (tiles[i][j].getPosition() == position)
					tile = tiles[i][j];
			}
		}
		
		return tile;
	}
	
	public void render(Screen screen) {
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				if (i == 3 && j == 3) continue;
				tiles[j][i].render(screen);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			direction = 1;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			direction = 2;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			direction = 3;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			direction = 4;
	}

	public void keyPressed(KeyEvent e) {
	}
}
