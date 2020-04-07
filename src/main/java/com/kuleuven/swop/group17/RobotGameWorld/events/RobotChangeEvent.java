package com.kuleuven.swop.group17.RobotGameWorld.events;

import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;

/**
 * The event thrown when the robot has been changed in the domain.
 * 
 * @version 0.1
 * @author group17
 */
public class RobotChangeEvent implements EventObject {

	private Coordinate coordinate;
	private Orientation orientation;

	/**
	 * Create the RobotChangeEvent
	 * 
	 * @param coordinate  The coordinate to which the element was added.
	 * @param orientation The orientation
	 */
	public RobotChangeEvent(Coordinate coordinate, Orientation orientation) {
		super();
		this.coordinate = coordinate;
		this.orientation = orientation;
	}

	/**
	 * Retrieve the coordinate of the robot.
	 * 
	 * @return the coordinate of the robot
	 */
	public Coordinate getCoordinate() {

		return new Coordinate(coordinate.getX(), coordinate.getY());
	}

	/**
	 * Retrieve the orientation
	 * 
	 * @return the new orientation of the robot
	 */
	public Orientation getOrientation() {
		return orientation;
	}

}