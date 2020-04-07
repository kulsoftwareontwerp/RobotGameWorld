package com.kuleuven.swop.group17.RobotGameWorld.applicationLayer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementRepository;
import com.kuleuven.swop.group17.RobotGameWorld.events.ElementsClearedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUISubject;
import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;


/**
 * The ElementController handles all requests regarding the management of elements.
 * 
 * @version 0.1
 * @author group17
 */
public class ElementController implements GUISubject {

	private Collection<GUIListener> guiListeners;
	private ElementRepository elementRepository;

	/**
	 * Create an ElementController
	 * @param elementRepository The elementrepository associated with this controller.
	 */
	ElementController(ElementRepository elementRepository) {
		guiListeners = new HashSet<GUIListener>();
		this.elementRepository = elementRepository;
	}

	private void fireElementAddedEvent(ElementType element, Coordinate coordinate) {
		ElementAddedEvent event = new ElementAddedEvent(coordinate, element);
		for (GUIListener listener : guiListeners) {
			listener.onElementAddedEvent(event);
		}
	}
	
	private void fireElementClearedEvent() {
		ElementsClearedEvent event = new ElementsClearedEvent();
		for (GUIListener listener : guiListeners) {
			listener.onElementsClearedEvent(event);
		}
	}
	
	/**
	 * Remove all elements in the gameWorld
	 * @event ElementsClearedEvent When the elements have been removed from the domain.
	 */
	void clearElements() {
		elementRepository.clearElements();
		fireElementClearedEvent();
	}

	/**
	 * Add an element to the RobotGameWorld.
	 * 
	 * @param element The type of element.
	 * @param coordinate The coordinate of the element.
	 * @event ElementAddedEvent When the operation is successful ElementAddedEvent will be thrown to all the listeners.
	 */
	void addElement(ElementType element, Coordinate coordinate) {
		
		elementRepository.addElement(element, coordinate);

		fireElementAddedEvent(element, coordinate);
	}
	
	/**
	 * Retrieve all elements in the RobotGameWorld.
	 * @return all elements in the RobotGameWorld.
	 */
	public Set<Element> getElements(){
		return elementRepository.getElements();
	}

	

	@Override
	public void removeListener(GUIListener listener) {
		guiListeners.remove(listener);

	}

	@Override
	public void addListener(GUIListener listener) {
		guiListeners.add(listener);
	}


}
