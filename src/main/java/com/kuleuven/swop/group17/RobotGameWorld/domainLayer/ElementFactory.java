package com.kuleuven.swop.group17.RobotGameWorld.domainLayer;

import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
/**
 * The ElementFactory is responsible for creating elements in the domain.
 * 
 * 
 * @version 0.1
 * @author group17
 */
public class ElementFactory {

	public ElementFactory() {	
	}

	/**
	 * 
	 * @param type
	 * @param coordinate
	 * @throws 	IllegalArgumentException
	 * 			thrown when type is null or sand.
	 */
	public Element createElement(ElementType type, Coordinate coordinate) {
		Element element;
		switch (type) {
		case GOAL:
			element=new Goal(coordinate);
			break;
		case ROBOT:
			element=new Robot(coordinate);
			break;
		case WALL:
			element=new Wall(coordinate);
			break;
		default:
			throw new IllegalArgumentException("Illegal elementType to create: " + type);
		}
		
		return element;
	}


}