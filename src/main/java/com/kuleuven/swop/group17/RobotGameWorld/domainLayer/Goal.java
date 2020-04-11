package com.kuleuven.swop.group17.RobotGameWorld.domainLayer;

import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;

/**
 * A goal is a type of element in the RobotGameWorld, it has a type and a
 * coordinate.
 * 
 * @version 0.1
 * @author group17
 */
public class Goal extends Element {

	/**
	 * Create a Goal
	 * @param coordinate the coordinate for this goal.
	 */
	Goal(Coordinate coordinate) {
		super(coordinate);

	}

	@Override
	public ElementType getType() {
		return ElementType.GOAL;
	}



}