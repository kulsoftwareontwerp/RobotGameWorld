/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.applicationLayer;

import java.util.Set;

import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldSnapshot;

/**
 * RobotGameWorldSnapeshotFactory
 *
 * @version 0.1
 * @author group17
 */
class RobotGameWorldSnapshotFactory {

	/**
	 * Create a RobotGameWorldSnapeshotFactory
	 */
	RobotGameWorldSnapshotFactory() {
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
	
}
