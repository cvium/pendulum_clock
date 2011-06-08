package model;

public class Pendulum {
	
	private double length; //Length of the pendulum in centimeters
	private int period; //Period of the swinging pendulum in milliseconds 
	private int timeGained; //Delay compared to system clock in seconds per day.
	private final static int DEFAULT_LENGTH = 25;
	private final static int MIN_LENGTH = 1;
	private final static int MAX_LENGTH = 50;
	private final static double TWOTHOUSAND_PI = 6283.0;
	
	/**
	 * Creates a new pendulum object with the default length
	 */
	public Pendulum() {
		this.length = DEFAULT_LENGTH;
		period = (int)(TWOTHOUSAND_PI * Math.sqrt(length / Environment.GRAVITATIONAL_ACC));
		timeGained = 86400 * 1000 / period - 86400;
	}
	
	/**
	 * Gets the period of the pendulum in seconds.
	 * @return The period of the swinging pendulum
	 */
	public int getPeriod() {
		return period;
	}
	
	/**
	 * Gets the length of the pendulum in centimeters.
	 * @return The length of the pendulum
	 */
	public double getLength() {
		return length;
	}
	
	public int getTimeGained() {
		return timeGained;
	}
	/**
	 * Sets the length of the pendulum to the value given. If the value is less than
	 * the minimum allowed length the length is set to the minimum value instead, and vice versa
	 * for the maximal value.
	 * @param length The new length of the pendulum.
	 */
	public void setLength(double length) {
		if(length <= MIN_LENGTH) {
			this.length = MIN_LENGTH;
		} else if(length >= MAX_LENGTH) {
			this.length = MAX_LENGTH;
		} else {
			this.length = length;
		}
		period = (int)(TWOTHOUSAND_PI * Math.sqrt(length / Environment.GRAVITATIONAL_ACC));
		timeGained = 86400 * 1000 / period - 86400;
	}
	
	public void reset() {
		setLength(DEFAULT_LENGTH);
		
	}
}
