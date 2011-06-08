package function;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import model.Clock;

import view.ClockCanvas;

public class Main extends MIDlet implements CommandListener {

	private final static Command APPLY_COMMAND = new Command("Apply", Command.OK, 1);
	private final static Command EXIT_COMMAND = new Command("Exit", Command.EXIT, 2);
	private final static Command BACK_COMMAND = new Command("Back", Command.EXIT, 2);
	private final static Command YES_COMMAND = new Command("Yes", Command.OK, 1);
	private final static Command NO_COMMAND = new Command("No", Command.CANCEL, 2);
	private final static Command STOP_COMMAND = new Command("Stop", Command.OK, 1);
	private final static Command START_COMMAND = new Command("Start", Command.OK, 1);
	private final static Command SETTINGS_COMMAND = new Command("Settings", Command.ITEM, 3);
	
	private Display display;
	private ClockUpdater updater;
	private ClockCanvas canvas;
	private List startList;
	private List settingsList;
	private Form setLengthForm;
	private Alert resetClockAlert;
	private Alert resetAllAlert;
	private Gauge lengthGauge;
	
	private Clock clock;
	
	public Main() {
	}
	
	protected void startApp() throws MIDletStateChangeException {
		clock = new Clock();
		
		canvas = new ClockCanvas(clock);
		canvas.addCommand(EXIT_COMMAND);
		canvas.addCommand(STOP_COMMAND);
		canvas.addCommand(SETTINGS_COMMAND);
		canvas.setCommandListener(this);
		
		String[] startStrings = {"Start", "Instructions"};
		startList = new List(null, Choice.IMPLICIT, startStrings, null);
		startList.addCommand(EXIT_COMMAND);
		startList.setCommandListener(this);
		
		String[] settingsStrings = {"Set length", "Reset Clock", "Reset All"};
		settingsList = new List("Settings", Choice.IMPLICIT, settingsStrings, null);
		settingsList.addCommand(BACK_COMMAND);
		settingsList.setCommandListener(this);
		
		setLengthForm = new Form(null);
		lengthGauge = new Gauge("Length in cm", true, 50, 25);
		setLengthForm.append(lengthGauge);
		setLengthForm.addCommand(BACK_COMMAND);
		setLengthForm.addCommand(APPLY_COMMAND);
		setLengthForm.setCommandListener(this);
		
		resetClockAlert = new Alert("Reset clock", "Do you want to reset the clock?", null, null);
		resetClockAlert.addCommand(NO_COMMAND);
		resetClockAlert.addCommand(YES_COMMAND);
		resetClockAlert.setCommandListener(this);
		
		resetAllAlert = new Alert("Reset all", "Do you want to reset all settings?", null, null);
		resetAllAlert.addCommand(NO_COMMAND);
		resetAllAlert.addCommand(YES_COMMAND);
		resetAllAlert.setCommandListener(this);
		
		display = Display.getDisplay(this);
		display.setCurrent(startList);
		
	}

	public void commandAction(Command command, Displayable displayable) {
		//Start-up list actions
		if(displayable == startList) {
			if(command == List.SELECT_COMMAND) {
				switch(((List)displayable).getSelectedIndex()) {
				case 0:
					updater = new ClockUpdater(clock, canvas, this);
					display.setCurrent(canvas);
					break;
				case 1:
					//TODO: help screen
					break;
				}
			} else if(command == EXIT_COMMAND) {
				doQuit();
			}
		//Settings list actions
		} else if(displayable == settingsList) {
			if(command == List.SELECT_COMMAND) {
				switch(((List)displayable).getSelectedIndex()) {
				case 0:
					lengthGauge.setValue((int)clock.getPendulum().getLength());
					display.setCurrent(setLengthForm);
					break;
				case 1:
					display.setCurrent(resetClockAlert);
					break;
				case 2:
					display.setCurrent(resetAllAlert);
					break;
				}
			} else if(command == BACK_COMMAND) {
				display.setCurrent(canvas);
			}
		//Set length form actions
		} else if(displayable == setLengthForm) {
			if(command == APPLY_COMMAND) {
				clock.getPendulum().setLength(lengthGauge.getValue());
				clock.resetTime();
				display.setCurrent(canvas);
			} else if(command == BACK_COMMAND) {
				display.setCurrent(settingsList);
			}
		//Reset clock alert actions
		} else if(displayable == resetClockAlert) {
			if(command == YES_COMMAND) {
				clock.resetTime();
				display.setCurrent(canvas);
			} else if(command == NO_COMMAND) {
				display.setCurrent(settingsList);
			}
		//Reset all alert actions
		} else if(displayable == resetAllAlert) {
			if(command == YES_COMMAND) {
				clock.reset();
				display.setCurrent(canvas);
			} else if(command == NO_COMMAND) {
				display.setCurrent(settingsList);
			}
		//Canvas actions
		} else if(displayable == canvas) {
			if(command == SETTINGS_COMMAND) {
				display.setCurrent(settingsList);
			} else if(command == EXIT_COMMAND) {
				updater.stopThread();
			} else if(command == STOP_COMMAND) {
				canvas.stopClock();
				canvas.removeCommand(STOP_COMMAND);
				canvas.addCommand(START_COMMAND);
			} else if(command == START_COMMAND) {
				canvas.startClock();
				canvas.removeCommand(START_COMMAND);
				canvas.addCommand(STOP_COMMAND);
			}
		}
	}

	protected void destroyApp(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub
		
	}
	
    public void doQuit() {
    	display.setCurrent(null);
    	destroyApp(true);
        notifyDestroyed();
    }


}
