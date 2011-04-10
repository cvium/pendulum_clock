package main;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import view.ClockCanvas;

public class Main extends MIDlet implements CommandListener {

	private Display display;
	
	public Main() {
	}
	
	protected void startApp() throws MIDletStateChangeException {
		display = Display.getDisplay(this);
		display.setCurrent(new ClockCanvas());
	}

	public void commandAction(Command arg0, Displayable arg1) {
		// TODO Auto-generated method stub
		
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub
		
	}
	
    public void doQuit() {
    	display.setCurrent(null);
        notifyDestroyed();
    }


}
