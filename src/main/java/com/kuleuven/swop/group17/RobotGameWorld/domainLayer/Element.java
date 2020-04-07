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
	 * @param coordinate
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
		return coordinate;
	}

	/**
	 * Set the Coordinate of this Element.
	 * 
	 * @param coordinate The coordinate to set this element to.
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Retrieve the type of this Element
	 * 
	 * @return the type of this Element.
	 */
	public abstract ElementType getType();

}