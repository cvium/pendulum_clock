package function;

import view.ClockCanvas;
import model.Clock;

public class ClockUpdater implements Runnable {
	
	private Clock clock;
	private int period;
	private int timer;
	private Thread updateThread;
	private boolean threadShouldRun;
	private ClockCanvas canvas;
	private Main parentMIDlet;
	
	public ClockUpdater(Clock clock, ClockCanvas canvas, Main midlet) {
		this.clock = clock;
		this.canvas = canvas;
		this.parentMIDlet = midlet;
		period = clock.getPeriod();
		threadShouldRun = true;
		updateThread = new Thread(this);
		updateThread.start();
	}
	
	public void run() {
		while(threadShouldRun) {
				clock.updateTime();
				//TODO: update graphics/system time?
				//Base time on system time instead...
				}
			canvas.repaint();
			parentMIDlet.doQuit();
	}
		
	public void stopThread() {
		threadShouldRun = false;
	}
	
	public void clockSettingsUpdated() {
		timer = 0;
		period = clock.getPeriod();
	}
}