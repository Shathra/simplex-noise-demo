package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DemoFrame extends JFrame{
	
	private ToolkitPanel toolkitPanel;
	private CanvasPanel canvasPanel;
	
	public DemoFrame() {
		
		setTitle( "Demo Terrain Generation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout( new BorderLayout());
		getContentPane().add( containerPanel);
		
		toolkitPanel = new ToolkitPanel();
		containerPanel.add( toolkitPanel, BorderLayout.EAST);
		
		canvasPanel = new CanvasPanel();
		containerPanel.add( canvasPanel, BorderLayout.CENTER);
	}

	public CanvasPanel getCanvasPanel() {
		
		return canvasPanel;
	}
}
