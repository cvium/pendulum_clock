package model;

public class Time {

	private int hours, minutes, seconds, milliseconds;
	
	/**
	 * Creates a new object representing a given time of the day
	 * @param hours
	 * @param minutes
	 * @param seconds
	 */
	public Time(int hours, int minutes, int seconds, int milliseconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}
	/**
	 * Creates a new object representing a given time of the day
	 * @param s The number of milliseconds after midnight
	 */
	public Time(int s) {
		s = s % 86400000;
		hours = s / 3600000;
		s = s % 3600000;
		minutes = s / 60000;
		s = s % 60000;
		seconds = s / 1000;
		s = s % 1000;
		milliseconds = s;
	}
	
	/**
	 * Adds a given amount of milliseconds to the time
	 * @param s Number of milliseconds to add
	 */
	public void increment(int s) {
		milliseconds += s;
		if(milliseconds >= 1000) {
			seconds += milliseconds / 1000;
			milliseconds = milliseconds % 1000;
			if(seconds >= 60) {
				minutes += seconds / 60;
				seconds = seconds % 60;
				if(minutes >= 60) {
					hours += minutes / 60;
					minutes = minutes % 60;
					if(hours >= 24){
						hours = hours % 24;
					}
				}
			}
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
	
	/**
	 * Returns the time in milliseconds after midnight
	 * @return The time in milliseconds after midnight
	 */
	public int getMilliSeconds() {
		return milliseconds;
	}
}
