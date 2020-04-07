package com.kuleuven.swop.group17.RobotGameWorld.applicationLayer;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementRepository;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.SolidElement;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUISubject;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotChangeEvent;
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

	public RobotController(ElementRepository elementRepository) {
		super();
		this.elementRepository = elementRepository;
	}

	private void fireRobotAddedEvent(Coordinate coordinate, Orientation orientation) {
		RobotAddedEvent event = new RobotAddedEvent(coordinate, orientation);

		for (GUIListener listener : guiListeners) {
			listener.onRobotAddedEvent(event);
		}
	}

	private void fireRobotChangeEvent() {
		Robot robot = getRobot();
		RobotChangeEvent event = new RobotChangeEvent(robot.getCoordinate(), robot.getOrientation());

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
		Orientation newOrientation = currentOrientation.getLeft();
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
	 */
	void addRobot(Coordinate coordinate, Orientation orientation) {
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

	private Robot getRobot() {

		Optional<Robot> robot = elementRepository.getElementByType(ElementType.ROBOT).stream().map(s -> (Robot) s)
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

		switch (currentRobotOrientation) {
		case UP:
			rc.setY(rc.getY() - 1);
			break;
		case DOWN:
			rc.setY(rc.getY() + 1);
			break;
		case LEFT:
			rc.setX(rc.getX() - 1);
			break;
		case RIGHT:
			rc.setX(rc.getX() + 1);
			break;
		}
		return rc;
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
