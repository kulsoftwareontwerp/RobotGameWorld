package com.kuleuven.swop.group17.RobotGameWorld.guiLayer;

import java.awt.Graphics;
import java.util.Collection;
import java.util.HashMap;

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
	private HashMap<Coordinate, Cell> cells;
	private CellFactory factory;
	private final int OFFSET_GAMEAREA_CELLS = 4;
	private static final int CELL_SIZE = 50;
	private TypeFactory typeFactory;

	RobotCanvas() {
		cells = new HashMap<Coordinate, Cell>();
		factory = new CellFactory();
		typeFactory = new TypeFactory();
		initCells();
	}

	private void addCell(Cell cell) {
		cell.setCoordinateOffset(typeFactory.createCoordinate(0, OFFSET_GAMEAREA_CELLS));

		cells.put(cell.getCoordinate(), cell);
	}

	// look for robot, set that cell to SAND
	private void moveRobot(Coordinate coordinate, Orientation orientation) {
		try {
			Cell previousCell = getCells().stream().filter(e -> e.getType() == ElementType.ROBOT).findFirst().get();
			previousCell.setType(null);
			Cell robot = factory.createCell(ElementType.ROBOT, coordinate, orientation);
			addCell(robot);

		} catch (Exception e) {
			e.printStackTrace();
		}
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

	private Collection<Cell> getCells() {
		return cells.values();
	}

	/**
	 * Paint the RobotGameArea on the given graphics
	 * 
	 * @param g the graphics on which the RobotGameArea should be drawn.
	 */
	public void paint(Graphics g) {
		g.drawLine(0, 0, 0, g.getClipBounds().height);
		g.drawLine(0, 200, g.getClipBounds().width, 200);
		g.drawLine(0, 400, g.getClipBounds().width, 400);
		try {
			for (Cell cell : getCells()) {

				g.drawImage(cell.getImage(), cell.getCoordinate().getX() * CELL_SIZE,
						cell.getCoordinate().getY() * CELL_SIZE, null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void clearCells() {
		cells.clear();
		initCells();
	}

	@Override
	public void onRobotChangeEvent(RobotChangedEvent event) {
		moveRobot(event.getCoordinate(), event.getOrientation());
	}

	@Override
	public void onRobotAddedEvent(RobotAddedEvent event) {
		addCell(factory.createCell(ElementType.ROBOT, event.getCoordinate(), event.getOrientation()));

	}

	@Override
	public void onElementAddedEvent(ElementAddedEvent event) {
		addCell(factory.createCell(event.getType(), event.getCoordinate()));
	}

	@Override
	public void onElementsClearedEvent(ElementsClearedEvent event) {
		clearCells();

	}

}