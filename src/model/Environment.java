package model;

import java.util.TimeZone;

public class Environment {
	
	public static final int GRAVITATIONAL_ACC = 982; //Gravitational acceleration in Denmark in cm/s^2
	private static final int MILLISECONDS_PER_DAY = 86400000;
	private static final int TIME_OFFSET = TimeZone.getDefault().getRawOffset();
	
	public static Time getSystemTime() {
		// Computes the number of seconds since last midnight. 500 is added to ensure that numbers are rounded of correctly.
		int milliseconds = (int)((System.currentTimeMillis() + 3600000 * TIME_OFFSET) % MILLISECONDS_PER_DAY);
		return new Time(milliseconds);
	}
	
	public static int getSystemTimeInMilliSeconds() {
		int milliSeconds = (int)((System.currentTimeMillis() + 3600000 * TIME_OFFSET) % (MILLISECONDS_PER_DAY));
		return milliSeconds;
	}
}
