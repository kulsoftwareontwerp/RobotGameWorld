package com.kuleuven.swop.group17.RobotGameWorld.types;

/**
 * The elementTypes in the RobotGameWorld.
 * 
 * @version 0.1
 * @author group17
 */
public enum ElementType {
	WALL{
		public String toString() {
			return "wall";
		}
		public String toOrientationString(Orientation orientation) {
			return toString();
		}
	},
	ROBOT{
		public String toString() {
			return "robot";
		}

		@Override
		public String toOrientationString(Orientation orientation) {
			if(orientation==null) {
				orientation = Orientation.UP;
			}

			return toString()+orientation.toString();				
		}
		
	},
	GOAL{
		public String toString() {
			return "goal";
		}

		@Override
		public String toOrientationString(Orientation orientation) {
			return toString();
		}
	}, 
	SAND{
		public String toString() {
			return "sand";
		}

		@Override
		public String toOrientationString(Orientation orientation) {
			return toString();
		}
	};
	
	public abstract String toOrientationString(Orientation orientation);
}