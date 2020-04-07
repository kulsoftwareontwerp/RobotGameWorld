package com.kuleuven.swop.group17.RobotGameWorld.types;
/**
 * The supported actions of the RobotGameWorld.
 * 
 * @version 0.1
 * @author group17
 */
public enum SupportedActions {
	TURNLEFT{
		public String toString() {
			return "Turn Left";
		}
	},TURNRIGHT{
		public String toString() {
			return "Turn Right";
		}
	},MOVEFORWARD{
		public String toString() {
			return "Move Forward";
		}
	}
}
