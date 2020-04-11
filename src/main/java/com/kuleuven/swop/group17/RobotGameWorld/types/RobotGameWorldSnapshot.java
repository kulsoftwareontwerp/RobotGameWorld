/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.types;

import java.util.HashSet;
import java.util.Set;

import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element;

/**
 * The RobotGameWorldSnapshot is a snapshot of the state of a robotGameWorld.
 * 
 * @version 0.1
 * @author group17
 */
public class RobotGameWorldSnapshot implements GameWorldSnapshot {
	private Set<Element> elements;

	
	/**
	 * Create a snapshot of the robotGameWorld.
	 * @param elements All the elements from the robotGameWorld that define the current state.
	 */
	RobotGameWorldSnapshot(Set<Element> elements) {
		super();
		setElements(elements);
	}

	/**
	 * Retrieve all the elements in this RobotGameWorldSnapshot
	 * @return all the elements in this RobotGameWorldSnapshot
	 */
	public Set<Element> getElements() {
		return new HashSet<Element>(elements);
	}

	
	private void setElements(Set<Element> elements) {		
		this.elements = new HashSet<Element>(elements);
	}
	
}
