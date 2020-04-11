package com.kuleuven.swop.group17.RobotGameWorld.applicationLayer;

import java.awt.Graphics;

import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.GameWorld;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldType;
import com.kuleuven.swop.group17.GameWorldApi.Predicate;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.DomainFactory;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementRepository;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot;
import com.kuleuven.swop.group17.RobotGameWorld.guiLayer.GuiFactory;
import com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas;
import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;
import com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldAction;
import com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldPredicate;
import com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldSnapshot;
import com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory;

/**
 * A GameWorld is described by a GameWorldType. GameWorlds are able to perform
 * Actions, evaluate predicates, save and restore their state and draw
 * themselves on a given graphics object.
 * 
 * @version 0.1
 * @author group17
 */
public class RobotGameWorld implements GameWorld {
	private RobotController robotController;
	private ElementController elementController;
	private RobotCanvas robotCanvas;
	private TypeFactory typeFactory;

	/**
	 * Create a RobotGameWorld
	 */
	public RobotGameWorld() {
		super();
		DomainFactory domainFactory = new DomainFactory();
		ElementRepository elementRepository = domainFactory.createElementRepository();
		
		RobotController robotController = new RobotController(elementRepository);
		ElementController elementController = new ElementController(elementRepository);
		createRobotGameWorld(robotController, elementController, new TypeFactory(),  new GuiFactory());

	}

	// This private constructor is called using reflection by RobotGameWorldTest to
	// initialize the fields with mocks.
	@SuppressWarnings("unused")
	private RobotGameWorld(RobotController robotController, ElementController elementController,
			TypeFactory typeFactory,  GuiFactory guiFactory) {
		super();
		createRobotGameWorld(robotController, elementController, typeFactory, guiFactory);
	}

	private void createRobotGameWorld(RobotController robotController, ElementController elementController,
			TypeFactory typeFactory, GuiFactory guiFactory) {


		this.typeFactory=typeFactory;
		
		
		this.robotCanvas = guiFactory.createRobotCanvas();
		
		this.elementController = elementController;
		this.robotController = robotController;

		this.robotController.addListener(robotCanvas);
		this.elementController.addListener(robotCanvas);

		initializeRobotGameWorld();
	}

	private void initializeRobotGameWorld() {
		robotController.addRobot(typeFactory.createCoordinate(2, 3), Orientation.UP);
		elementController.addElement(ElementType.WALL, typeFactory.createCoordinate(0, 0));
		elementController.addElement(ElementType.WALL, typeFactory.createCoordinate(4, 0));
		elementController.addElement(ElementType.WALL, typeFactory.createCoordinate(1, 2));
		elementController.addElement(ElementType.WALL, typeFactory.createCoordinate(2, 2));
		elementController.addElement(ElementType.WALL, typeFactory.createCoordinate(3, 2));
	}

	/**
	 * Perform the given action on the gameWorld.
	 * 
	 * @param action The action that should be performed.
	 * @throws UnsupportedOperationException when an action was given that's not
	 *                                       listed in the supportedActions of the
	 *                                       corresponding gameWorldType of this
	 *                                       gameWorld.
	 */
	public void performAction(Action action) throws UnsupportedOperationException {
		if (action == null) {
			throw new NullPointerException("The given action can't be null");

		}
		if (!(action instanceof RobotGameWorldAction)) {
			throw new UnsupportedOperationException("The given action is not a supported action for a RobotGameWorld.");
		}
		RobotGameWorldAction robotAction = (RobotGameWorldAction) action;
		switch (robotAction.getAction()) {
		case MOVEFORWARD:
			robotController.moveForward();
			break;
		case TURNLEFT:
			robotController.turnLeft();
			break;
		case TURNRIGHT:
			robotController.turnRight();
			break;
		}

	}

	/**
	 * Evaluates the given predicate on the gameWorld.
	 * 
	 * @param predicate The predicate that should be evaluated.
	 * @throws UnsupportedOperationException when a predicate was given that's not
	 *                                       listed in the supportedPredicates of
	 *                                       the corresponding gameWorldType of this
	 *                                       gameWorld.
	 * @throws NullPointerException          when the given predicate is null.
	 * @return the evaluation of the given predicate.
	 */
	public Boolean evaluate(Predicate predicate) throws UnsupportedOperationException {
		if (predicate == null) {
			throw new NullPointerException("The given predicate can't be null");
		}

		if (!(predicate instanceof RobotGameWorldPredicate)) {
			throw new UnsupportedOperationException(
					"The given predicate is not a supported predicate for a RobotGameWorld.");
		}
		RobotGameWorldPredicate robotPredicate = (RobotGameWorldPredicate) predicate;

		boolean evaluation=false;
		
		switch (robotPredicate.getPredicate()) {
		case WALLINFRONT:
			evaluation=  robotController.checkIfWallInFront();
			break;
		}
		
		return evaluation;

	}

	/**
	 * Saves the current state of the gameWorld.
	 * 
	 * @return a non inspectable snapshot with current state of the gameWorld
	 */
	public GameWorldSnapshot saveState() {
		return typeFactory.createSnapshot(elementController.getElements());
	}

	/**
	 * Set the state of the gameWorld to the given gameWorldState
	 * 
	 * @param state the state to which the gameWorld should be set.
	 * @throws IllegalArgumentException when the given state is not a state of this
	 *                                  gameWorld.
	 * @throws NullPointerException     when the given GameWorlSnapshot is null.
	 */
	public void restoreState(GameWorldSnapshot state) throws IllegalArgumentException {
		if (state == null) {
			throw new NullPointerException("The given GameWorldSnapshot can't be null");
		}
		if (!(state instanceof RobotGameWorldSnapshot)) {
			throw new IllegalArgumentException(
					"The given GameWorldSnapshot is not a valid snapshot for a RobotGameWorld.");
		}
		elementController.clearElements();
		for (Element element : ((RobotGameWorldSnapshot) state).getElements()) {
			if (element.getType() == ElementType.ROBOT) {
				robotController.addRobot(element.getCoordinate(), ((Robot) element).getOrientation());
			} else {
				elementController.addElement(element.getType(), element.getCoordinate());
			}
		}
	}

	/**
	 * Paint the gameWorld on the given graphics object.
	 * 
	 * @param graphics the graphics object on which the gameWorld should be painted.
	 * @throws NullPointerException when the given graphics object is null.
	 */
	public void paint(Graphics graphics) {
		if (graphics == null) {
			throw new NullPointerException("The given Graphics can't be null");

		}
		robotCanvas.paint(graphics);
	}

	/**
	 * Retrieve the type corresponding to the RobotGameWorld.
	 * 
	 * @return the type corresponding to the RobotGameWorld
	 */
	public GameWorldType getType() {
		return typeFactory.createType();
	}

}
