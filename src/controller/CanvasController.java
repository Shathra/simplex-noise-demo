package controller;

import java.awt.Color;

import gui.CanvasPanel;
import gui.DemoFrame;
import model.SimplexNoise;

public class CanvasController {

	private static CanvasController object;
	
	public static CanvasController getInstance() {
		
		if( object == null) {
			
			object = new CanvasController();
		}
		
		return object;
	}
	
	//Fields
	private CanvasPanel canvasPanel;
	private DemoFrame frame;
	
	private CanvasController() {
		
		frame = new DemoFrame();
		canvasPanel = frame.getCanvasPanel();
		
	}
	
	public void showFrame() {
		
		
		frame.pack();
		frame.setVisible(true);
	}

	public void generateUniformRandom(int width, int height) {
		
		Color[][] colorMatrix = new Color[width][height];
		for( int i = 0; i < width; i++) {
			
			for( int j = 0; j < height; j++) {
				
				int r = (int)(Math.random() * 256);
				Color c = new Color(r, r, r);
				colorMatrix[i][j] = c;
			}
		}
		
		canvasPanel.paintCanvas(colorMatrix);
	}
	
	private double noise(double x, double y, int seed) {
		
		double value = SimplexNoise.noise(x + seed, y + seed * 100) + 1;
		value = value * 0.5;
		return value;
	}

	public void generateNoiseRandom(int width, int height, double frequency, double exponent, double e1, double e2, double e3, double e4, int seed) {
		
		Color[][] colorMatrix = new Color[width][height];
		
		for( int i = 0; i < width; i++) {
			
			for( int j = 0; j < height; j++) {
				
				double nx = ((double)i)/width - 0.5;
				double ny = ((double)j)/height - 0.5;
			    double e = (e1 * noise( frequency * 1 * nx,  frequency * 1 * ny, seed)
			           + e2 * noise( frequency * 2 * nx,  frequency * 2 * ny, seed)
			           + e3 * noise( frequency * 4 * nx,  frequency * 4 * ny, seed)
			           + e4 * noise( frequency * 8 * nx,  frequency * 8 * ny, seed));
			    e /= (e1+e2+e3+e4);
			    e = Math.pow(e, exponent);
				
				int r = (int) (256 - e * 256);
				Color c = new Color(r, r, r);
				colorMatrix[i][j] = c;
			}
		}
		
		canvasPanel.paintCanvas(colorMatrix);
	}
}
