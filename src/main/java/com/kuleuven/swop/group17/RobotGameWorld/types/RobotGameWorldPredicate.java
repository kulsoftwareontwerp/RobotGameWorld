package com.kuleuven.swop.group17.RobotGameWorld.types;

import com.kuleuven.swop.group17.GameWorldApi.Predicate;

public class RobotGameWorldPredicate implements Predicate {
	SupportedPredicates predicate;

	/**
	 * Create a RobotGameWorldPredicate
	 * @param action the SupportedPredicates associated with this RobotGameWorldPredicate. 
	 */
	public RobotGameWorldPredicate(SupportedPredicates predicate) {
		super();
		this.predicate = predicate;
	}

	/**
	 * Retrieve the SupportedPredicates associated with this RobotGameWorldPredicate.
	 * 
	 * @return the SupportedPredicate associated with this RobotGameWorldPredicate
	 */
	public SupportedPredicates getPredicate() {
		return predicate;
	}

	@Override
	public String toString() {
		return predicate.toString();
	}

}
