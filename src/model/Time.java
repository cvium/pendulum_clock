package model;

public class Time {

	private int hours, minutes, seconds;
	
	/**
	 * Creates a new object representing a given time of the day
	 * @param hours
	 * @param minutes
	 * @param seconds
	 */
	public Time(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	/**
	 * Creates a new object representing a given time of the day
	 * @param s The number of seconds after midnight
	 */
	public Time(int s) {
		hours = s / 3600;
		s = s % 3600;
		minutes = s / 60;
		s = s % 60;
		seconds = s;
	}
	
	/**
	 * Adds a second to the time
	 */
	public void increment() {
		if(seconds == 59) {
			if(minutes == 59) {
				if(hours == 23){
					hours = 0;
				} else {
					hours++;
				}
				minutes = 0;
			} else {
				minutes++;
			}
			seconds = 0;
		} else {
			seconds++;
		}
	}
	
	/**
	 * Creates a string representation of the time
	 * @return The time as a string
	 */
	public String toString() {
		String s = hours + ":";
		if(minutes < 10) {
			s += "0" + minutes + ":";
		} else {
			s += minutes + ":";
		}
		if(seconds < 10) {
			s += "0" + seconds;
		} else {
			s += seconds;
		}
		return s;
	}
	
	/**
	 * Returns the time in seconds after midnight
	 * @return The time in seconds after midnight
	 */
	public int toSeconds() {
		return seconds + 60*minutes + 3600*hours;
	}
}
