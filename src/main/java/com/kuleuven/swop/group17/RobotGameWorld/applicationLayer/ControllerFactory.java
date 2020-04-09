/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.applicationLayer;

import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementRepository;

/**
 * ControllerFactory
 *
 * @version 0.1
 * @author group17
 */
class ControllerFactory {

	/**
	 * Create a ControllerFactory
	 */
	public ControllerFactory() {
		super();
	}
	
	
	/**
	 * Create a new ElementController with the given ElementRepository.
	 * @param repository The ElementRepository for the ElementController to use.
	 * @return a new ElementController
	 */
	public ElementController createElementController(ElementRepository repository){
		return new ElementController(repository);
	}
	
	/**
	 * Create a new RobotController with the given ElementRepository.
	 * @param repository The ElementRepository for the RobotController to use.
	 * @return a new RobotController
	 */
	public RobotController createRobotController(ElementRepository repository) {
		return new RobotController(repository);
	}
	
}
