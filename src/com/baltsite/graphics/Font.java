package com.baltsite.graphics;

public class Font {

	private static final Bitmap FONT = new Bitmap("res/font.png");
	private static final String LETTERS = "ABCDEFGHIJKLMNOPRSTUWYZ";
	
	public static void renderMessage(Screen screen, String message, int xOff, int yOff) {
		message = message.toUpperCase();
		for (int i = 0; i < message.length(); i++) {
			int index = LETTERS.indexOf(message.charAt(i));
			screen.draw(FONT.cut(index * 5, 0, 5, 7), (i * 6) + xOff, yOff);
		}
	}
	
	
}
