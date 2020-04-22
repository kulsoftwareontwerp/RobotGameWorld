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

	ElementFactory() {	
	}

	/**
	 * Create an element with the given type and coordinate.
	 * @param type	The type to set this element to.
	 * @param coordinate	The coordinate to set this element to.
	 * @throws 	IllegalArgumentException
	 * 			thrown when type is null or sand.
	 * @throws 	IllegalArgumentException
	 * 			thrown when coordinate is null.
	 * @return an element with the given type and coordinate.
	 */
	public Element createElement(ElementType type, Coordinate coordinate) {
		if(coordinate==null) {
			throw new IllegalArgumentException("Coordinate can't be null");
		}
		if(type==null) {
			throw new IllegalArgumentException("ElementType can't be null");
		}
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