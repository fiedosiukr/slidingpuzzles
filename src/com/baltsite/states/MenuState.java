package com.baltsite.states;

import java.awt.event.KeyEvent;

import com.baltsite.graphics.Screen;
import com.baltsite.objects.Grid;

public class MenuState extends GameState {
	
	private Grid grid;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		grid = new Grid("res/test.png", 4);
		
	}

	public void update(double delta) {
		grid.update();
		
		
	}

	public void render(Screen screen) {
		grid.render(screen);
		
	}

	protected void keyPressed(KeyEvent e) {
		grid.keyPressed(e);
	}

	protected void keyReleased(KeyEvent e) {
		grid.keyReleased(e);
	}

}
