package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import controller.CanvasController;

@SuppressWarnings("serial")
public class ToolkitPanel extends JPanel {

	private static final int WIDTH = 200;
	private static final int DEFAULT_SIZE = 512;
	
	private static final int SLIDER_MIN = 0;
	private static final int SLIDER_MAX = 500;
	private static final double FREQUENCY_MIN_SCALED = 1.0;
	private static final double FREQUENCY_MAX_SCALED = 7.0;
	private static final double EXPONENT_MIN_SCALED = 0.5;
	private static final double EXPONENT_MAX_SCALED = 10.0;
	private static final double E_SLIDER_MIN_SCALED = 0.0;
	private static final double E_SLIDER_MAX_SCALED = 1.0;
	
	private JTextField canvasWidthTf;
	private JTextField canvasHeightTf;
	private JSlider frequencySlider;
	private JSlider exponentSlider;
	private JSlider seedSlider;
	private JSlider e1Slider;
	private JSlider e2Slider;
	private JSlider e3Slider;
	private JSlider e4Slider;
	
	public ToolkitPanel() {
		
		super();
		setPreferredSize(new Dimension( WIDTH, getPreferredSize().height));
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
		
		JPanel uniformRandomPanel = new JPanel();
		uniformRandomPanel.setBorder( BorderFactory.createTitledBorder("Uniform Random"));
		
		JButton uniformRandomButton = new JButton( "Generate");
		uniformRandomPanel.add( uniformRandomButton);
		
		JPanel noisePanel = new JPanel();
		noisePanel.setBorder( BorderFactory.createTitledBorder("Noise Random"));
		BoxLayout noisePanelLayout = new BoxLayout(noisePanel, BoxLayout.Y_AXIS);
		noisePanel.setLayout(noisePanelLayout);
		
		
		noisePanel.add(new JLabel("Frequency : "));
		frequencySlider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, 1);
		noisePanel.add(frequencySlider);
		
		noisePanel.add(new JLabel("Exponent : "));
		exponentSlider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, 1);
		noisePanel.add(exponentSlider);
		
		noisePanel.add(new JLabel("e1 : "));
		e1Slider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, 1);
		noisePanel.add(e1Slider);
		
		noisePanel.add(new JLabel("e2 : "));
		e2Slider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, 1);
		noisePanel.add(e2Slider);
		
		noisePanel.add(new JLabel("e3 : "));
		e3Slider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, 1);
		noisePanel.add(e3Slider);
		
		noisePanel.add(new JLabel("e4 : "));
		e4Slider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, 1);
		noisePanel.add(e4Slider);
		
		noisePanel.add(new JLabel("Seed : "));
		seedSlider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, 1);
		noisePanel.add(seedSlider);
		
		JButton noiseRandomButton = new JButton( "Generate");
		noisePanel.add( noiseRandomButton);
		
		JPanel sizePanel = new JPanel();
		sizePanel.setBorder( BorderFactory.createTitledBorder("Canvas Size"));
		
		canvasWidthTf = new JTextField();
		canvasWidthTf.setText(""+DEFAULT_SIZE);
		canvasHeightTf = new JTextField();
		canvasHeightTf.setText(""+DEFAULT_SIZE);
		
		BoxLayout sizePanelLayout = new BoxLayout(sizePanel, BoxLayout.Y_AXIS);
		sizePanel.setLayout( sizePanelLayout);
		sizePanel.add( new JLabel( "Width : "));
		sizePanel.add( canvasWidthTf);
		sizePanel.add( new JLabel( "Height : "));
		sizePanel.add( canvasHeightTf);
		
		add( uniformRandomPanel);
		add(sizePanel);
		add(noisePanel);
		
		Component glue = Box.createVerticalGlue();
		glue.setPreferredSize(new Dimension(0, Short.MAX_VALUE));
		add(glue);
		
		uniformRandomButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int width = getCanvasWidth();
				int height = getCanvasHeight();
				CanvasController controller = CanvasController.getInstance();
				controller.generateUniformRandom(width, height);
			}
		});
		
		noiseRandomButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int width = getCanvasWidth();
				int height = getCanvasHeight();
				CanvasController controller = CanvasController.getInstance();
				
				double frequency = getFrequency();
				double exponent = getExponent();
				double e1 = getE1();
				double e2 = getE2();
				double e3 = getE3();
				double e4 = getE4();
				int seed = getSeed();
				controller.generateNoiseRandom(width, height, frequency, exponent, e1, e2, e3, e4, seed);
			}
		});
	}
	
	private int getCanvasWidth() {
		
		String strWidth = canvasWidthTf.getText();
		int retval = DEFAULT_SIZE;
		try {
			
			retval = Integer.parseInt(strWidth);
		}
		catch( NumberFormatException exc) {}
		return retval;
	}
	
	private int getCanvasHeight() {
		
		String strHeight = canvasHeightTf.getText();
		int retval = DEFAULT_SIZE;
		try {
			
			retval = Integer.parseInt(strHeight);
		} 
		catch( NumberFormatException exc) {}
		return retval;
	}
	
	private double getFrequency() {
		
		int value = frequencySlider.getValue();
		double frequencyNormal = ((double)value - SLIDER_MIN) / SLIDER_MAX;
		double frequencyScaled = frequencyNormal * (FREQUENCY_MAX_SCALED - FREQUENCY_MIN_SCALED) + FREQUENCY_MIN_SCALED;
		return frequencyScaled;
	}
	
	private double getExponent() {
		
		int value = exponentSlider.getValue();
		double exponentNormal = ((double)value - SLIDER_MIN) / SLIDER_MAX;
		double exponentScaled = exponentNormal * (EXPONENT_MAX_SCALED - EXPONENT_MIN_SCALED) + EXPONENT_MIN_SCALED;
		return exponentScaled;
	}
	
	private double getE1() {
		
		int value = e1Slider.getValue();
		double e1Normal = ((double)value - SLIDER_MIN) / SLIDER_MAX;
		double e1Scaled = e1Normal * (E_SLIDER_MAX_SCALED - E_SLIDER_MIN_SCALED) + E_SLIDER_MIN_SCALED;
		return e1Scaled;
	}
	
	private double getE2() {
		
		int value = e2Slider.getValue();
		double e2Normal = ((double)value - SLIDER_MIN) / SLIDER_MAX;
		double e2Scaled = e2Normal * (E_SLIDER_MAX_SCALED - E_SLIDER_MIN_SCALED) + E_SLIDER_MIN_SCALED;
		return e2Scaled;
	}
	
	private double getE3() {
		
		int value = e3Slider.getValue();
		double e3Normal = ((double)value - SLIDER_MIN) / SLIDER_MAX;
		double e3Scaled = e3Normal * (E_SLIDER_MAX_SCALED - E_SLIDER_MIN_SCALED) + E_SLIDER_MIN_SCALED;
		return e3Scaled;
	}
	
	private double getE4() {
		
		int value = e4Slider.getValue();
		double e4Normal = ((double)value - SLIDER_MIN) / SLIDER_MAX;
		double e4Scaled = e4Normal * (E_SLIDER_MAX_SCALED - E_SLIDER_MIN_SCALED) + E_SLIDER_MIN_SCALED;
		return e4Scaled;
	}
	
	private int getSeed() {
		
		int value = seedSlider.getValue();
		return value;
	}
}
