package com.baltsite.states;

import com.baltsite.graphics.Screen;

public abstract class GameState {
	
	protected GameStateManager gsm;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void update(double delta);
	public abstract void render(Screen screen);
}
