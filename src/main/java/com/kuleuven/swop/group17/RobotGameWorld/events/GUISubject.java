package com.kuleuven.swop.group17.RobotGameWorld.events;

/**
 * A GUISubject maintains it's list of GUIListeners and fires the DomainEvents.
 * 
 * @version 0.1
 * @author group17
 */
public interface GUISubject {

	/**
	 * Add a GUIListener
	 * @param 	listener
	 * 			The listener to be added.
	 */
	void addListener(GUIListener listener);

	/**
	 * Remove a GUIListener
	 * @param 	listener
	 * 			The listener to be removed.
	 */
	void removeListener(GUIListener listener);

}