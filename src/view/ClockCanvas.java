package view;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import model.Clock;
import model.Environment;

public class ClockCanvas extends Canvas {
	
	private Clock clock;
	private int length, seconds, pendulumX, pendulumY;
	private boolean pendulumPressed, clockRunning;
	private Image pendulum, clockface, background;
	private double angle; //Angle between pendulum chord and vertical axis in radians.
	
	//Lengths of the clock hands in pixels
	private final static double HOUR_HAND_LENGTH = 15.0;
	private final static double MINUTE_HAND_LENGTH = 20.0;
	private final static double SECONDS_HAND_LENGTH = 25.0;
	private final static double PI = 3.14;
	private final static double MAX_ANGLE = PI/15.0;
	
	/**
	 * Creates a new ClockCanvas
	 */
	public ClockCanvas(Clock clock) {
		super();
		this.clock = clock;
		length = 25;
		seconds = 0;
		pendulumPressed = false;
		clockRunning = true;
		angle = 0;
		pendulumX = 69;
		pendulumY = 68 + 3 * length;
		
		try {
			clockface = Image.createImage("/view/clockface.png");
			pendulum = Image.createImage("/view/pendul.png");
			background = Image.createImage("/view/bg.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//protected void showNotify() {
	//	setFullScreenMode(true);
	//}

	protected void paint(Graphics g) {
		if(!pendulumPressed) {
			length = (int)clock.getPendulum().getLength();
			pendulumY = 68 + 3 * length;
		}
		
		
		
		//Draws the background
		g.setColor(0, 0, 0);
		g.drawImage(background, 0, 0, Graphics.TOP|Graphics.LEFT);
		
		//Draw the clock face
		g.drawImage(clockface, 70, 40, Graphics.VCENTER|Graphics.HCENTER);
		
		
		//Draw the hour hand of the clock
		if(clockRunning) {
			//Only updates the position of the hands when the clock should be running
			seconds = clock.getTime().toSeconds();
		}
		int x, y;
		x = (int)(HOUR_HAND_LENGTH * Math.cos(-PI/2.0 + 2*PI * seconds / 43200.0));
		y = (int)(HOUR_HAND_LENGTH * Math.sin(-PI/2.0 + 2*PI * seconds / 43200.0));
		drawThickLine(70, 40, 70 + x, 40 + y, 2, g);
		
		//Draw the minute hand of the clock
		x = (int)(MINUTE_HAND_LENGTH * Math.cos(-PI/2.0 + (seconds % 3600) / 60 * 2*PI / 60.0));
		y = (int)(MINUTE_HAND_LENGTH * Math.sin(-PI/2.0 + (seconds % 3600) / 60 * 2*PI / 60.0));
		g.drawLine(70, 40, 70 + x, 40 + y);
		
		//Draw the seconds hand of the clock
		x = (int)(SECONDS_HAND_LENGTH * Math.cos(-PI/2.0 + (seconds % 60) * 2*PI / 60.0));
		y = (int)(SECONDS_HAND_LENGTH * Math.sin(-PI/2.0 + (seconds % 60) * 2*PI / 60.0));
		g.setColor(255, 0, 0);
		g.drawLine(70, 40, 70 + x, 40 + y);
		g.setColor(0, 0, 0);
		
		//Draw the pendulum.
		//drawThickLine(69, 68, 70, 70 + 3* length, 2, g);
		//g.drawImage(pendulum, 70, 70 + 3*length, Graphics.HCENTER|Graphics.TOP);
		drawPendulum(g);
		//Draw the info boxes
		g.drawString("System time", 135, 25, Graphics.BOTTOM|Graphics.LEFT);
		//g.drawRect(135, 25, 90, 30);
		g.drawString(Environment.getSystemTime().toString(), 180, 50, Graphics.BASELINE|Graphics.HCENTER);
		
		g.drawString("Clock time", 135, 100, Graphics.BOTTOM|Graphics.LEFT);
		//g.drawRect(135, 100, 90, 30);
		g.drawString(clock.getTime().toString(), 180, 125, Graphics.BASELINE|Graphics.HCENTER);
		
		g.drawString("Seconds", 135, 200 - g.getFont().getHeight(), Graphics.BOTTOM|Graphics.LEFT);
		g.drawString("gained/day", 135, 200, Graphics.BOTTOM|Graphics.LEFT);
		//g.drawRect(135, 200, 90, 30);
		g.drawString(clock.getTimeGained()+"", 180, 225, Graphics.BASELINE|Graphics.HCENTER);
		
	}
	
	public void stopClock() {
		clockRunning = false;
		pendulumX = 69;
		pendulumY = 68 + 3 * length;
	}
	
	public void startClock() {
		clockRunning = true;
	}
	/**
	 * Draws a line with the specified number of pixels in thickness.
	 * @param startx
	 * @param starty
	 * @param endx
	 * @param endy
	 * @param thickness
	 * @param g
	 */
	private void drawThickLine(int startx, int starty, int endx, int endy, int thickness, Graphics g) {
		double tan;
		int offsetx, offsety;
		if(endx != startx) {
			tan = ((double)(endy - starty)) / ((double)(endx - startx));
		} else {
			tan = 10.0;
		}
		double sin = simpleSin(tan);
		double cos = simpleCos(tan);
		double i = 1.0;
		for(double n = 1.0; n <= thickness; n += 1.0) {
			offsety = (int)(n/2 * i * cos + 0.5);
			offsetx = (int)(n/2 * i * sin + 0.5);
			g.drawLine(startx + offsetx, starty + offsety, endx + offsetx, endy + offsety);
			i *= -1.0;
		}
	}
	
	/**
	 * Converts the tangent of an angle to the sine.
	 * @param a tangent of an angle
	 * @return Sine of the angle
	 */
	private double simpleSin(double a) {
		double x = Math.abs(a);
		if(x < 0.5) {
			return 0.0;
		} else if(x < 2.0) {
			return 0.7;
		} else {
			return 1.0;
		}
	}
	/**
	 * Converts the tangent of an angle to the cosine.
	 * @param a tangent of an angle
	 * @return Coine of the angle
	 */
	private double simpleCos(double x) {
		if(x < 0.5) {
			return 1.0;
		} else if(x < 2.0) {
			return 0.7;
		} else {
			return 0.0;
		}
	}
	
	private void drawPendulum(Graphics graphic) {
		if(clockRunning) {
			if(!pendulumPressed) {
				angle = MAX_ANGLE * Math.cos( 2 * PI * Environment.getSystemTimeInMilliSeconds() / clock.getPeriod());
				pendulumX = 69 + (int)(3 * length * Math.sin(angle));
				pendulumY = 68 + (int)(3 * length * Math.cos(angle));
			}
			drawThickLine(69, 68, pendulumX, pendulumY, 2, graphic);
			graphic.drawImage(pendulum, pendulumX, pendulumY, Graphics.HCENTER|Graphics.TOP);
		} else {
			drawThickLine(69, 68, 69, 68 + 3* length, 2, graphic);
			graphic.drawImage(pendulum, pendulumX, pendulumY, Graphics.HCENTER|Graphics.TOP);
		}
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
		if(x > pendulumX - 20 && x < pendulumX + 20 && y > pendulumY - 10 && y < pendulumY + 30) {
			pendulumPressed = true;
		}	
	}
	
	protected void pointerDragged(int x, int y) {
		if(pendulumPressed) {
			if(x < 120) {
				pendulumX = x;
			} else
				pendulumX = 120;
			if(y > 70) {
				pendulumY = y;
			} else {
				pendulumY = 70;
			}
			int dx = pendulumX - 70;
			int dy = pendulumY - 70;
			length = (int)(Math.sqrt(dx*dx + dy*dy));
			if(length>50) {
				length = 50;
			} else if(length < 1) {
				length = 1;
			}
			repaint();
		}
	}
	
	protected void pointerReleased(int x, int y) {
		if(pendulumPressed) {
			length = (y - 70) / 3;
			if(length>50) {
				length = 50;
			} else if(length < 1) {
				length = 1;
			}
			pendulumPressed = false;
			clock.getPendulum().setLength(length);
		}
		repaint();
	}
}