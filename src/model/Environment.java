package model;

import java.util.TimeZone;

public class Environment {
	
	static final int GRAVITATIONAL_ACC = 982; //Gravitational acceleration in Denmark in cm/s^2
	private static final int SECONDS_PER_DAY = 86400;
	private static final int TIME_OFFSET = TimeZone.getDefault().getRawOffset();
	
	public static Time getSystemTime() {
		// Computes the number of seconds since last midnight. 500 is added to ensure that numbers are rounded of correctly.
		int seconds = (int)((System.currentTimeMillis() + 500)/1000 + 3600 * TIME_OFFSET) % SECONDS_PER_DAY;
		return new Time(seconds);
	}
}
