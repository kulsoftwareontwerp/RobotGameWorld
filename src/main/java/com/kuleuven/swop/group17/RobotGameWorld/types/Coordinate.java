package com.kuleuven.swop.group17.RobotGameWorld.types;


/**
 * The concept of a coordinate, a point matching a certain location in the robotGameworld.
 * 
 * @version 0.1
 * @author group17
 */
public class Coordinate {
	private int x;
	private int y;
	
	/**
	 * Create a coordinate with the given x and y values.
	 * @param x	The x value for the coordinate.
	 * @param y	The y value for the coordinate.
	 */
	public Coordinate(int x, int y) {
		super();
		setX(x);
		setY(y);
	}
	
	/**
	 * Retrieve the x value for the coordinate.
	 * @return the x value for the coordinate.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Set the x value for the coordinate.
	 * @param x The x value for the coordinate.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Retrieve the y value for the coordinate.
	 * @return the y value for the coordinate.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Set the y value for the coordinate.
	 * @param y The y value for the coordinate.
	 */
	public void setY(int y) {
		this.y = y;
	}

	
	
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coordinate) {
			Coordinate compare = (Coordinate) obj;
			return compare.getX() == this.getX() && compare.getY()==this.getY();
		}
		else {
			return false;			
		}
	}
	
	
	
	
	
	
	
}
