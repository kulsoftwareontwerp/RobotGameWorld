package com.kuleuven.swop.group17.RobotGameWorld.types;

import com.kuleuven.swop.group17.GameWorldApi.Action;


/**
 * RobotGameWorldActions are used to specify what the RobotGameWorld can do.
 * 
 * @version 0.1
 * @author group17
 */
public class RobotGameWorldAction implements Action {
	SupportedActions action;

	/**
	 * Create a RobotGameWorldAction
	 * @param action the SupportedAction associated with this RobotGameWorldAction. 
	 */
	public RobotGameWorldAction(SupportedActions action) {
		super();
		this.action = action;
	}
	
	
	
	/**
	 * Retrieve the SupportedAction associated with this RobotGameWorldAction.
	 * @return the SupportedAction associated with this RobotGameWorldAction
	 */
	public SupportedActions getAction() {
		return action;
	}




	@Override
	public String toString() {
		return action.toString();
	}
	
	

}