package model;

public class Clock {

	private Time time;
	private Pendulum pendulum;
	private int timeStamp;
	/**
	 * Creates a new clock
	 */
	public Clock() {
		time = Environment.getSystemTime();
		pendulum = new Pendulum();
		timeStamp = Environment.getSystemTime().toSeconds();
	}
	
	/**
	 * Gets the time of the clock
	 * @return The time of the clock
	 */
	public Time getTime() {
		return time;
	}
	
	/**
	 * Gets the pendulum-object associated with the clock
	 * @return The pendulum of the clock
	 */
	public Pendulum getPendulum() {
		return pendulum;
	}
	
	/**
	 * Gets the period of the clock pendulum
	 * @return The period of the clock pendulum
	 */
	public int getPeriod() {
		return pendulum.getPeriod();
	}
	
	public int getTimeGained() {
		return pendulum.getTimeGained();
	}
	
	/**
	 * Sets the clock time to a given number of seconds after midnight
	 * @param seconds The number of seconds after midnight
	 */
	public void updateTime() {
		
	}
	
	/**
	 * Resets the clock time to system time
	 */
	public void resetTime() {
		time = Environment.getSystemTime();
	}
	
	/**
	 * Resets the clock time to system time and the pendulum to
	 * the default configuration
	 */
	public void reset() {
		resetTime();
		pendulum.reset();
	}
}
