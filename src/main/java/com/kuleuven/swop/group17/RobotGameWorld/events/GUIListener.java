package com.kuleuven.swop.group17.RobotGameWorld.events;

/**
 * A GUIlistener resides in the GUI layer and updates the GUI according to
 * events emerging from the domain.
 * 
 * @version 0.1
 * @author group17
 */
public interface GUIListener {

	/**
	 * Perform the needed actions in the GUI after the robot changed in the domain.
	 * 
	 * @param event A RobotChangeEvent
	 * @throws IllegalArgumentException when the given event is null.
	 * 
	 */
	void onRobotChangeEvent(RobotChangedEvent event);

	/**
	 * Perform the needed actions in the GUI after a robot has been added to the
	 * domain.
	 * 
	 * @param event A RobotAddedEvent
	 * @throws IllegalArgumentException when the given event is null.
	 * 
	 */
	void onRobotAddedEvent(RobotAddedEvent event);

	/**
	 * Perform the needed actions in the GUI after an element has been added to the
	 * domain.
	 * 
	 * @param event A ElementAddedEvent
	 * @throws IllegalArgumentException when the given event is null.
	 * 
	 */
	void onElementAddedEvent(ElementAddedEvent event);

	/**
	 * Perform the needed actions in the GUI after all elements are removed from the
	 * domain.
	 * 
	 * @param event A ElementsClearedEvent
	 * @throws IllegalArgumentException when the given event is null.
	 * 
	 */
	void onElementsClearedEvent(ElementsClearedEvent event);

}