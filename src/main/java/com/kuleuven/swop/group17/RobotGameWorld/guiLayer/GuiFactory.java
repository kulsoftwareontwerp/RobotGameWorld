/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.guiLayer;

/**
 * GuiFactory
 *
 * @version 0.1
 * @author group17
 */
public class GuiFactory {
/**
 * Create a new GuiFactory
 */
	public GuiFactory() {
		super();
	}
	
	/**
	 * Create a new RobotCanvas
	 * @return a new RobotCanvas
	 */
	public RobotCanvas createRobotCanvas() {
		return new RobotCanvas();
	}
}
