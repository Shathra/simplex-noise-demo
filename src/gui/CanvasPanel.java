package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CanvasPanel extends JPanel{

	private static final int HEIGHT = 600;
	private static final int WIDTH = 600;
	
	private Color[][] colorMatrix;
	
	public CanvasPanel() {
		
		super();
		setPreferredSize( new Dimension(WIDTH, HEIGHT));
	}
	
	public void paintCanvas( Color[][] colorMatrix) {
		
		this.colorMatrix = colorMatrix;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if( colorMatrix != null) {
			
			for( int i = 0; i < colorMatrix.length; i++) {
				
				for( int j = 0; j < colorMatrix[0].length; j++) {
					
					g.setColor( colorMatrix[i][j]);
					g.drawLine(i, j, i, j);
				}
			}
		}
	}
}
