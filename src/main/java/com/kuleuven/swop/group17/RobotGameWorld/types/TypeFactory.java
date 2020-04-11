/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.types;

import java.util.Set;

import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element;

/**
 * TypeFactory
 *
 * @version 0.1
 * @author group17
 */
public class TypeFactory {
	public TypeFactory() {
		super();
	}

	/**
	 * Create a RobotGameWorldSnapshot with the given state.
	 * 
	 * @param elements The given state of the RobotGameWorld to create a snapshot
	 *                 of.
	 * @return the RobotGameWorldSnapshot associated with the given state.
	 */
	public RobotGameWorldSnapshot createSnapshot(Set<Element> elements) {
		return new RobotGameWorldSnapshot(elements);
	}

	/**
	 * Create a RobotGameWorldType.
	 * 
	 * @return a RobotGameWorldType
	 */
	public RobotGameWorldType createType() {
		return new RobotGameWorldType();
	}
	
	/**
	 * Create a RobotGameWorldAction
	 * 
	 * @param action The supportedAction for this RobotGameWorldAction
	 * @return a RobotGameWorldAction with the given SupportedAction
	 */
	public RobotGameWorldAction createAction(SupportedActions action) {
		return new RobotGameWorldAction(action);
	}
	
	/**
	 * Create a RobotGameWorldPredicate
	 * @param predicate The SupportedPredicate for this RobotGameWolrdPredicate
	 * @return a RobotGameWorldPredicate with the given SupportedPredicate
	 */
	public RobotGameWorldPredicate createPredicate(SupportedPredicates predicate) {
		return new RobotGameWorldPredicate(predicate);
	}
	
	
	
	/**
	 * Create a new Coordinate with the given x and y values.
	 * @param x the X value for the coordinate.
	 * @param y the Y value for the coordinate.
	 * @return A coordinate with the given x and y values.
	 */
	public Coordinate createCoordinate(int x,int y) {
		return new Coordinate(x, y);
	}
	
	
}
