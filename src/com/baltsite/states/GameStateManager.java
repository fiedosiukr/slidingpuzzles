package com.baltsite.states;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import com.baltsite.graphics.Screen;

public class GameStateManager {
	
	private LinkedList<GameState> gameStates;
	
	public GameStateManager() {
		gameStates = new LinkedList<GameState>();
		gameStates.add(new MenuState(this));
	}
	
	public void update(double delta) {
		gameStates.peek().update(delta);
	}
	
	public void render(Screen screen) {
		gameStates.peek().render(screen);
	}
	
	public void pop() {
		gameStates.pop();
	}
	
	public void setState(GameState gameState) {
		gameStates.add(gameState);
	}

	public void keyReleased(KeyEvent e) {
		gameStates.peek().keyReleased(e);
	}

	public void keyPressed(KeyEvent e) {
		gameStates.peek().keyPressed(e);
		
	}
}
