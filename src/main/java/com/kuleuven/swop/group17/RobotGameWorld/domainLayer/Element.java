package com.kuleuven.swop.group17.RobotGameWorld.domainLayer;

import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;

/**
 * An Element is a concept in the RobotGameWorld, it has a type and a
 * coordinate.
 * 
 * @version 0.1
 * @author group17
 */
public abstract class Element {

	private Coordinate coordinate;

	/**
	 * Create an element given a certain coordinate
	 * 
	 * @param coordinate the coordinate for this element.
	 */
	public Element(Coordinate coordinate) {
		setCoordinate(coordinate);
	}

	/**
	 * Retrieve the coordinate for this element.
	 * 
	 * @return the coordinate for this element.
	 */
	public Coordinate getCoordinate() {
		Coordinate copy = new Coordinate(coordinate.getX(),coordinate.getY());
		return copy;
	}

	/**
	 * Set the Coordinate of this Element.
	 * 
	 * @param coordinate The coordinate to set this element to.
	 */
	public void setCoordinate(Coordinate coordinate) {
		Coordinate copy = new Coordinate(coordinate.getX(),coordinate.getY());
		this.coordinate = copy;
	}

	/**
	 * Retrieve the type of this Element
	 * 
	 * @return the type of this Element.
	 */
	public abstract ElementType getType();

}