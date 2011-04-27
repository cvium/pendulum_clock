package model;

public class Pendulum {
	
	private int length;
	
	private final static int DEFAULT_LENGTH = 100;
	private final static int MIN_LENGTH = 1;
	private final static int MAX_LENGTH = 500;
	
	/**
	 * Creates a new pendulum object with the default length
	 */
	public Pendulum() {
		this.length = DEFAULT_LENGTH;
	}
	
	/**
	 * Gets the length of the pendulum in centimeters.
	 * @return The length of the pendulum
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Sets the length of the pendulum to the value given. If the value is less than
	 * the minimum allowed length the length is set to the minimum value instead, and vice versa
	 * for the maximal value.
	 * @param length The new length of the pendulum.
	 */
	public void setLength(int length) {
		if(length <= MIN_LENGTH) {
			if(length >= MAX_LENGTH) {
				this.length = MAX_LENGTH;
			} else {
				this.length = MIN_LENGTH;
			}
		} else {
			this.length = length;
		}
	}
}
