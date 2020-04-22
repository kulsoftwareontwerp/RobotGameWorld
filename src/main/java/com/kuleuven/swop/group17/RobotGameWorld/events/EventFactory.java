/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.events;

import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;

/**
 * EventFactory
 *
 * @version 0.1
 * @author group17
 */
public class EventFactory {
	/**
	 * Create a EventFactory
	 */
	public EventFactory() {
		super();
	}
	
	/**
	 * Create a RobotChangeEvent with the given Coordinate and Orientation.
	 * @param coordinate The new Coordinate of the Robot.
	 * @param orientation The new Orientation of the Robot.
	 * @return a new RobotChangeEvent.
	 */
	public RobotChangedEvent createRobotChangedEvent(Coordinate coordinate,Orientation orientation) {
		if(coordinate==null) {
			throw new NullPointerException("A robot needs to have a coordinate");
		}
		if(orientation==null) {
			throw new NullPointerException("A robot needs to have an orientation");
		}
		return new RobotChangedEvent(coordinate, orientation);
	}
	
	/**
	 * Create a RobotAddedEvent with the given Coordinate and Orientation.
	 * @param coordinate The new Coordinate of the Robot.
	 * @param orientation The new Orientation of the Robot.
	 * @return a new RobotAddedEvent.
	 */
	public RobotAddedEvent createRobotAddedEvent(Coordinate coordinate,Orientation orientation) {
		if(coordinate==null) {
			throw new NullPointerException("A robot needs to have a coordinate");
		}
		if(orientation==null) {
			throw new NullPointerException("A robot needs to have an orientation");
		}
		return new RobotAddedEvent(coordinate, orientation);
	}
	
	/**
	 * Create a new ElementsClearedEvent
	 * @return a new ElementsClearedEvent
	 */
	public ElementsClearedEvent createElementsClearedEvent() {
		return new ElementsClearedEvent();
	}
	
	/**
	 * Create a new ElementAddedEvent with the given Coordinate and ElementType
	 * @param coordinate The Coordinate for the new Element.
	 * @param type The ElementType of the new Element.
	 * @return a new ElementAddedEvent
	 */
	public ElementAddedEvent createElementAddedEvent(Coordinate coordinate,ElementType type) {
		if(coordinate==null) {
			throw new NullPointerException("An element needs to have a coordinate");
		}
		if(type==null) {
			throw new NullPointerException("An element needs to have a type");
		}
		return new ElementAddedEvent(coordinate, type);
	}
	
	
}
