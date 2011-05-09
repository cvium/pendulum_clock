package view;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import model.Clock;
import model.Environment;

public class ClockCanvas extends Canvas {
	
	private Clock clock;
	
	/**
	 * Creates a new ClockCanvas
	 */
	public ClockCanvas(Clock clock) {
		super();
		this.clock = clock;
	}

	//protected void showNotify() {
	//	setFullScreenMode(true);
	//}

	protected void paint(Graphics g) {
		//Clear the screen
		g.setColor(255, 255, 255);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//Draw the clock face
		g.setColor(0, 0, 0);
		g.drawArc(50, 10, 60, 60, 0, 360);
			//TODO: Also draw the minute and hour hands
		
		//Draw the pendulum
		g.drawLine(80, 70, 80, 215); //Make two last args variable for animation/changing length
		g.fillArc(67, 215, 25, 25, 0, 360);
		
		//Draw the info boxes
		g.drawString("System time", 135, 25, Graphics.BOTTOM|Graphics.LEFT);
		g.drawRect(135, 25, 90, 30);
		g.drawString(Environment.getSystemTime().toString(), 180, 50, Graphics.BASELINE|Graphics.HCENTER);
		
		g.drawString("Clock time", 135, 120, Graphics.BOTTOM|Graphics.LEFT);
		g.drawRect(135, 120, 90, 30);
		g.drawString(clock.getTime().toString(), 180, 145, Graphics.BASELINE|Graphics.HCENTER);
		
		g.drawString("Seconds", 135, 215 - g.getFont().getHeight(), Graphics.BOTTOM|Graphics.LEFT);
		g.drawString("gained/day", 135, 215, Graphics.BOTTOM|Graphics.LEFT);
		g.drawRect(135, 215, 90, 30);
		g.drawString(clock.getTimeGained()+"", 180, 240, Graphics.BASELINE|Graphics.HCENTER);
		
	}
	
	private void handleKeyEvent(int ga) {		
		repaint();
	}
	
	protected void keyPressed(int keyCode){
		handleKeyEvent(getGameAction(keyCode));
	}
	
	/*
	 * Standard keyRepeated handler (called by system)
	 * When holding key (repeat), handle event
	 */
	protected void keyRepeated(int keyCode){
		handleKeyEvent(getGameAction(keyCode));
	}
	
	// Handle touch events for devices with a touch screen
	protected void pointerPressed(int x, int y) {
		super.pointerPressed(x, y);
		
		// NOTE:  This is a very quick method to detect touch events in 
		//        the border of the screen and translate to key game action events.
		if( x>getWidth()-50 )
			handleKeyEvent(Canvas.RIGHT);
		else if( x<50 )
			handleKeyEvent(Canvas.LEFT);
		else if( y>getHeight()-50 )
			handleKeyEvent(Canvas.DOWN);
		else if( y<50 )
			handleKeyEvent(Canvas.UP);
	}
}