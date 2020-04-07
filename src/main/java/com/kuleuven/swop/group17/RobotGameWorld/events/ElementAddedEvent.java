package com.kuleuven.swop.group17.RobotGameWorld.events;

import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;

/**
 * The event thrown when an element has been added to the domain.
 * 
 * @version 0.1
 * @author group17
 */
public class ElementAddedEvent implements EventObject {

	private Coordinate coordinate;
	private ElementType type;
	
	/**
	 * Create the ElementAddedEvent
	 * 
	 * @param 	coordinate
	 * 			The coordinate to which the element was added.
	 * @param 	type
	 * 			The ElementType
	 */
	public ElementAddedEvent(Coordinate coordinate, ElementType type) {
		super();
		this.coordinate = coordinate;
		this.type = type;
	}

	/**
	 * Retrieve the coordinate
	 * @return the coordinate of the added element
	 */
	public Coordinate getCoordinate() {
		
		return new Coordinate(coordinate.getX(), coordinate.getY());
	}

	/**
	 * Retrieve the ElementType
	 * @return the ElementType of the added element
	 */
	public ElementType getType() {
		return type;
	}
	
	
	



}