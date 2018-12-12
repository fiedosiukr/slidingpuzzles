package com.baltsite.states;

import java.awt.event.KeyEvent;

import com.baltsite.graphics.Screen;

public abstract class GameState {
	
	protected GameStateManager gsm;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void update(double delta);
	public abstract void render(Screen screen);

	protected abstract void keyPressed(KeyEvent e);

	protected abstract void keyReleased(KeyEvent e);
}
