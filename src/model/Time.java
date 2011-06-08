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
		s = s % 86400;
		hours = s / 3600;
		s = s % 3600;
		minutes = s / 60;
		s = s % 60;
		seconds = s;
	}
	
	/**
	 * Adds a given amount of seconds to the time
	 * @param s Number of seconds to add
	 */
	public void increment(int s) {
		seconds += s;
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
