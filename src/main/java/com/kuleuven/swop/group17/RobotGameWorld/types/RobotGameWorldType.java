package com.kuleuven.swop.group17.RobotGameWorld.types;

import java.util.HashSet;
import java.util.Set;

import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldType;
import com.kuleuven.swop.group17.GameWorldApi.Predicate;

/**
 * The RobotGameWorldType defines the characteristics of the RobotGameWorld.
 * 
 * @version 0.1
 * @author group17
 */
public class RobotGameWorldType implements GameWorldType {
	
	
	/**
	 * Create a robotGameWorldType
	 */
	public RobotGameWorldType() {
		super();
	}

	/**
	 * Retrieve all the supported actions for the RobotGameWorld.
	 * @return A set containing all the actions in the robotGameWorld.
	 */
	public Set<Action> supportedActions() {
		Set<Action> actions = new HashSet<Action>();
		for (SupportedActions action :  SupportedActions.values()) {
			actions.add(new RobotGameWorldAction(action));		
		}
		return actions;
	}

	
	/**
	 * Retrieve all the supported predicates for the RobotGameWorld.
	 * @return A set containing all the predicates in the robotGameWorld.
	 */
	public Set<Predicate> supportedPredicates() {
		Set<Predicate> predicates = new HashSet<Predicate>();
		for (SupportedPredicates predicate :  SupportedPredicates.values()) {
			predicates.add(new RobotGameWorldPredicate(predicate));		
		}
		return predicates;
	}

}
