/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.applicationLayer;

import java.util.Set;

import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldSnapshot;
import com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldType;

/**
 * DependencyFactory
 *
 * @version 0.1
 * @author group17
 */
class DependencyFactory {

	/**
	 * Create a DependencyFactory
	 */
	DependencyFactory() {
		super();
	}

	/**
	 * Create a RobotGameWorldSnapshot with the given state.
	 * @param elements The given state of the RobotGameWorld to create a snapshot of.
	 * @return the RobotGameWorldSnapshot associated with the given state.
	 */
	RobotGameWorldSnapshot createSnapshot(Set<Element> elements){
		return new RobotGameWorldSnapshot(elements);
	}
	
	/**
	 * Create a RobotGameWorldType.
	 * @return a RobotGameWorldType
	 */
	RobotGameWorldType createType() {
		return new RobotGameWorldType();
	}
	
}
