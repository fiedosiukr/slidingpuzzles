package com.baltsite.states;

import com.baltsite.graphics.Font;
import com.baltsite.graphics.Screen;

public class MenuState extends GameState {

	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	public void update(double delta) {
	}

	public void render(Screen screen) {
		Font.renderMessage(screen, "RENDERING SIMPLE MESSAGE", 100, 100);
	}

}
