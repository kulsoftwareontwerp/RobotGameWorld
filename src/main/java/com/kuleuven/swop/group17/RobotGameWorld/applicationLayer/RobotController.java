package com.kuleuven.swop.group17.RobotGameWorld.applicationLayer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementRepository;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.SolidElement;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall;
import com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUISubject;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotChangedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;

/**
 * The RobotController handles all requests regarding the management of a robot.
 * 
 * @version 0.1
 * @author group17
 */
public class RobotController implements GUISubject {

	private Collection<GUIListener> guiListeners;
	private ElementRepository elementRepository;
	private EventFactory eventFactory;

	RobotController(ElementRepository elementRepository) {
		if (elementRepository == null) {
			throw new NullPointerException("The given elementRepository can't be null.");
		}

		createRobotController(new EventFactory(), elementRepository, new HashSet<GUIListener>());

	}

	@SuppressWarnings("unused")
	private RobotController(ElementRepository elementRepository, Collection<GUIListener> guiListeners,
			EventFactory eventFactory) {
		createRobotController(eventFactory, elementRepository, guiListeners);
	}

	private void createRobotController(EventFactory eventFactory, ElementRepository elementRepository,
			Collection<GUIListener> guiListeners) {
		this.eventFactory = eventFactory;
		this.elementRepository = elementRepository;
		this.guiListeners = guiListeners;
	}

	private void fireRobotAddedEvent(Coordinate coordinate, Orientation orientation) {
		RobotAddedEvent event = eventFactory.createRobotAddedEvent(coordinate, orientation);

		for (GUIListener listener : guiListeners) {
			listener.onRobotAddedEvent(event);
		}
	}

	private void fireRobotChangeEvent() {
		Robot robot = getRobot();
		RobotChangedEvent event = eventFactory.createRobotChangedEvent(robot.getCoordinate(), robot.getOrientation());

		for (GUIListener listener : guiListeners) {
			listener.onRobotChangeEvent(event);
		}
	}

	/**
	 * Turn the robot left
	 * 
	 * @event RobotChangeEvent If the operation was successful.
	 */
	void turnLeft() {
		Robot robot = getRobot();
		Orientation currentOrientation = robot.getOrientation();
		Orientation newOrientation = currentOrientation.getLeft();
		robot.setOrientation(newOrientation);
		fireRobotChangeEvent();
	}

	/**
	 * Turn the robot right
	 * 
	 * @event RobotChangeEvent If the operation was successful.
	 */
	void turnRight() {
		Robot robot = getRobot();
		Orientation currentOrientation = robot.getOrientation();
		Orientation newOrientation = currentOrientation.getRight();
		robot.setOrientation(newOrientation);
		fireRobotChangeEvent();
	}

	/**
	 * Move the robot forward
	 * 
	 * @event RobotChangeEvent If the operation was successful.
	 */
	void moveForward() {

		Coordinate cifor = getCoordinatesInFrontOfRobot();
		Set<Element> elements = elementRepository.getElements(cifor);

		// Check if the robot stays within the boundries of the Game Area, else do
		// nothing
		if (cifor.getX() < 0 || cifor.getX() > elementRepository.getGameAreaWidth() - 1 || cifor.getY() < 0
				|| cifor.getY() > elementRepository.getGameAreaHeight() - 1) {
			return;
		}

		Iterator<Element> iterator = elements.iterator();
		while (iterator.hasNext()) {
			Element element = iterator.next();
			// If another solid element is already in the new spot of the robot, the
			// position of the robot stays the same.
			if (element instanceof SolidElement) {
				return;
			}
		}

		// If no solid objects are found on the new position, we move the robot into
		// that position.
		getRobot().setCoordinate(cifor);

		fireRobotChangeEvent();

	}

	/**
	 * Add a robot to the RobotGameWorld.
	 * 
	 * @param coordinate  The coordinate for the Robot.
	 * @param orientation The orientation for the Robot.
	 * @event RobotAddedEvent When the operation is successful and elementType is
	 *        robot the RobotAddedEvent will be thrown to all the listeners.
	 * @throws NullPointerException When the given Orientation or the given
	 *                              Coordinate is null.
	 * 
	 */
	void addRobot(Coordinate coordinate, Orientation orientation){
		if (orientation == null) {
			throw new NullPointerException("The given Orientation can't be null.");
		}
		if (coordinate == null) {
			throw new NullPointerException("The given Coordinate can't be null.");
		}
		elementRepository.addElement(ElementType.ROBOT, coordinate);
		getRobot().setOrientation(orientation);
		fireRobotAddedEvent(coordinate, orientation);
	}

	/**
	 * Check if there is a wall in front of the robot.
	 * 
	 * @return True if there is a wall in front of the robot, false otherwise.
	 */
	Boolean checkIfWallInFront() {
		Coordinate cifor = getCoordinatesInFrontOfRobot();
		Set<Element> elements = elementRepository.getElements(cifor);

		Iterator<Element> iterator = elements.iterator();
		while (iterator.hasNext()) {
			Element element = iterator.next();
			if (element instanceof Wall) {
				return true;
			}
		}
		return false;
	}

	private Robot getRobot() throws NoSuchElementException {

		Optional<Robot> robot = elementRepository.getElementsByType(ElementType.ROBOT).stream().map(s -> (Robot) s)
				.findFirst();
		if (robot.isPresent()) {
			return robot.get();
		} else {
			throw new NoSuchElementException("No robot has been added to the RobotGameWorld.");
		}
	}

	private Coordinate getCoordinatesInFrontOfRobot() {
		Robot robot = getRobot();
		Orientation currentRobotOrientation = robot.getOrientation();

		Coordinate rc = robot.getCoordinate();
		Coordinate toReturn = rc;

		switch (currentRobotOrientation) {
		case UP:
			toReturn = rc.setY(rc.getY() - 1);
			break;
		case DOWN:
			toReturn = rc.setY(rc.getY() + 1);
			break;
		case LEFT:
			toReturn = rc.setX(rc.getX() - 1);
			break;
		case RIGHT:
			toReturn = rc.setX(rc.getX() + 1);
			break;
		}
		return toReturn;
	}

	@Override
	public void removeListener(GUIListener listener) {
		if (listener == null) {
			throw new NullPointerException("The given GUIListener can't be null.");
		}
		guiListeners.remove(listener);

	}

	@Override
	public void addListener(GUIListener listener) {
		if (listener == null) {
			throw new NullPointerException("The given GUIListener can't be null.");
		}
		guiListeners.add(listener);
	}

}
