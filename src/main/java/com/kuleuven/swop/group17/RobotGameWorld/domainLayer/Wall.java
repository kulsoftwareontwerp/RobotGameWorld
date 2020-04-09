package com.kuleuven.swop.group17.RobotGameWorld.domainLayer;

import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;


/**
 * A wall is a type of element in the RobotGameWorld, it has a type and a
 * coordinate.
 * 
 * @version 0.1
 * @author group17
 */
public class Wall extends Element implements SolidElement {

	/**
	 * Create a wall
	 * @param coordinate The coordinate of this wall.
	 */
	public Wall(Coordinate coordinate) {
		super(coordinate);
	}

	@Override
	public ElementType getType() {
		return ElementType.WALL;
	}



}