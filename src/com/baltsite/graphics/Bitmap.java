package com.baltsite.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bitmap {
	
	private static final int TRANSPARENCY_COLOR = 0xFFFF00FF;
	
	public int width, height;
	public int pixels[];
	
	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];
	}
	
	public Bitmap(String path) {
		BufferedImage temp = null;
		
		try {
			temp = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("Failed to laod image. :(");
		}
		
		width = temp.getWidth();
		height = temp.getHeight();
		
		pixels = new int[width * height];
		
		pixels = temp.getRGB(0, 0, width, height, null, 0, width);
		
	}
	
	public void draw(Bitmap bitmap, int xOff, int yOff) {
		for (int y = 0; y < bitmap.height; y++) {
			int yPix = y + yOff;
			if (yPix < 0 || yPix >= height) continue;
			
			for (int x = 0; x < bitmap.width; x++) {
				int xPix = x + xOff;
				if (xPix < 0 || xPix >= width) continue;
				
				int pixel = bitmap.pixels[x + y * bitmap.width];
				
				if (pixel == TRANSPARENCY_COLOR) continue;
				
				pixels[xPix + yPix * width] = pixel;
			}
			
		}
	}
	
	public Bitmap cut(int xOff, int yOff, int w, int h) {
		Bitmap temp = new Bitmap(w, h);
		
		for (int y = 0; y < h; y++) {
			int yPix = yOff + y;
			if (yPix < 0 || yPix >= width) continue;
			for (int x = 0; x < w; x++) {
				int xPix = x + xOff;
				if (xPix < 0 || xPix >= width) continue;
				temp.pixels[x + y * w] = pixels[xPix + yPix * width];
			}
		}
		
		return temp;
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void clear(int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
}
