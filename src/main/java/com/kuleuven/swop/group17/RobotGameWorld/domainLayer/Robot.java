package com.kuleuven.swop.group17.RobotGameWorld.domainLayer;

import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;

/**
 * A robot is a type of element in the RobotGameWorld, it has a type, a
 * coordinate and an orientation.
 * 
 * @version 0.1
 * @author group17
 */
public class Robot extends Element implements SolidElement {
	private Orientation orientation;

	/**
	 * Create a Robot element.
	 * 
	 * @param coordinate The coordinate for this robot.
	 */
	Robot(Coordinate coordinate) {
		super(coordinate);
		this.orientation = null;
	}

	/**
	 * Retrieve the orientation
	 * 
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return this.orientation;
	}

	/**
	 * Set the orientation to the given orientation
	 * 
	 * @param orientation the orientation to which the robot orientation must be
	 *                    set.
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	@Override
	public ElementType getType() {
		return ElementType.ROBOT;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;

		Robot robot = (Robot) obj;
		if (robot.getType() != getType())
			return false;
		if (robot.getOrientation() != getOrientation())
			return false;

		return true;
	}

}