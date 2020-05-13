package com.kuleuven.swop.group17.RobotGameWorld.guiLayer;

import java.awt.Graphics;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.ElementsClearedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotChangedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;
import com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory;

/**
 * 
 * RobotCanvas
 *
 * @version 0.1
 * @author group17
 */
public class RobotCanvas implements GUIListener {
	private Map<Coordinate, Cell> cells;
	private CellFactory factory;
	private final int OFFSET_GAMEAREA_CELLS = 4;
	private static final int CELL_SIZE = 50;
	private TypeFactory typeFactory;
	private Map<Coordinate, ElementType> previousType;

	RobotCanvas() {
		createRobotCanvas(new HashMap<Coordinate, Cell>(), new CellFactory(), new TypeFactory());
	}

	@SuppressWarnings("unused")
	private RobotCanvas(Map<Coordinate, Cell> cells, CellFactory factory, TypeFactory typeFactory) {
		createRobotCanvas(cells, factory, typeFactory);
	}

	private void createRobotCanvas(Map<Coordinate, Cell> cells, CellFactory factory, TypeFactory typeFactory) {
		this.cells = cells;
		this.factory = factory;
		this.typeFactory = typeFactory;
		this.previousType=new HashMap<Coordinate, ElementType>();
		initCells();
	}

	private void initCells() {

		// Upper Fill Cells
		for (int x = 0; x <= 4; x++) {
			for (int y = 0; y <= 3; y++) {
				Coordinate coordinate = typeFactory.createCoordinate(x, y);
				cells.put(coordinate, factory.createCell(ElementType.WALL, coordinate));
			}
		}

		// Intermediate Fill Cells (REAL CELLS)
		for (int x = 0; x <= 4; x++) {
			for (int y = 4; y <= 7; y++) {
				Coordinate coordinate = typeFactory.createCoordinate(x, y);
				cells.put(coordinate, factory.createCell(ElementType.SAND, coordinate));
			}
		}

		// Lower Fill Cells
		for (int x = 0; x <= 4; x++) {
			for (int y = 8; y <= 11; y++) {
				Coordinate coordinate = typeFactory.createCoordinate(x, y);
				cells.put(coordinate, factory.createCell(ElementType.WALL, coordinate));
			}
		}
		;

	}

	private void addCell(Cell cell) {
		cell.setCoordinateOffset(typeFactory.createCoordinate(0, OFFSET_GAMEAREA_CELLS));
		
		Cell currentCell= cells.get(cell.getCoordinate());
		if(currentCell!=null &&  currentCell.getType()==ElementType.GOAL) {
			previousType.put(currentCell.getCoordinate(), currentCell.getType());
		}
		cells.put(cell.getCoordinate(), cell);
	}


	// look for robot, set that cell to the elementType that was previously present.
	private void moveRobot(Coordinate coordinate, Orientation orientation) {
		Optional<Cell> previousCell = getCells().stream().filter(e -> e.getType() == ElementType.ROBOT).findFirst();
		if (previousCell.isPresent()) {
			previousCell.get().setType(this.previousType.get(previousCell.get().getCoordinate()));
			Cell robot = factory.createCell(ElementType.ROBOT, coordinate, orientation);
			addCell(robot);
		}
	}

	private Collection<Cell> getCells() {
		return cells.values();
	}

	/**
	 * Paint the RobotGameArea on the given graphics
	 * 
	 * @param g the graphics on which the RobotGameArea should be drawn.
	 * @throws IllegalArgumentException when the given graphics object is null.
	 * 
	 */
	public void paint(Graphics g) {
		if (g == null) {
			throw new IllegalArgumentException("Graphics object can't be null");
		}
		g.drawLine(0, 0, 0, g.getClipBounds().height);
		g.drawLine(0, 200, g.getClipBounds().width, 200);
		g.drawLine(0, 400, g.getClipBounds().width, 400);
		for (Cell cell : getCells()) {
			g.drawImage(cell.getImage(), cell.getCoordinate().getX() * CELL_SIZE,
					cell.getCoordinate().getY() * CELL_SIZE, null);
		}
	}

	private void clearCells() {
		cells.clear();
		initCells();
	}

	@Override
	public void onRobotChangeEvent(RobotChangedEvent event) {
		if (event == null) {
			throw new IllegalArgumentException("event can't be null");
		}
		moveRobot(event.getCoordinate(), event.getOrientation());
	}

	@Override
	public void onRobotAddedEvent(RobotAddedEvent event) {
		if (event == null) {
			throw new IllegalArgumentException("event can't be null");
		}
		addCell(factory.createCell(ElementType.ROBOT, event.getCoordinate(), event.getOrientation()));

	}

	@Override
	public void onElementAddedEvent(ElementAddedEvent event) {
		if (event == null) {
			throw new IllegalArgumentException("event can't be null");
		}
		addCell(factory.createCell(event.getType(), event.getCoordinate()));
	}

	@Override
	public void onElementsClearedEvent(ElementsClearedEvent event) {
		if (event == null) {
			throw new IllegalArgumentException("event can't be null");
		}
		clearCells();

	}

}