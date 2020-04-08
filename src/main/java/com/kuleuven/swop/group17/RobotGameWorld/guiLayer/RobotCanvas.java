package com.kuleuven.swop.group17.RobotGameWorld.guiLayer;

import java.awt.Graphics;
import java.util.Collection;
import java.util.HashSet;

import com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.ElementsClearedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotChangeEvent;
import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;

public class RobotCanvas  implements GUIListener {
private Collection<Cell> cells;
private CellFactory factory;
	
	public RobotCanvas() {
		cells = new HashSet<Cell>();
		factory = new CellFactory();
		initCells();
	}
	
	private void addCell(Cell cell) {
		cells.add(cell);
	}
	
	private void moveRobot(Coordinate coordinate, Orientation orientation) {
		try {
			Cell previousCell = getCells().stream().filter(e -> e.getType() == ElementType.ROBOT).findFirst().get();
			previousCell.setType(null);
			Cell robot = factory.createCell(ElementType.ROBOT, coordinate, orientation);
			getCells().add(robot);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void initCells() {

		// Upper Fill Cells
		for (int x = 0; x <= 4; x++) {
			for (int y = 0; y <= 3; y++) {
				cells.add(factory.createCell(ElementType.WALL, new Coordinate(x, y)));
			}
		}
		
		//Intermediate Fill Cells (REAL CELLS)
		for (int x = 0; x <= 4; x++) {
			for (int y = 4; y <= 7; y++) {
				
				cells.add(factory.createCell(ElementType.SAND, new Coordinate(x,y)));
			}
		}
		
		// Lower Fill Cells
		for (int x = 0; x <= 4; x++) {
			for (int y = 8; y <= 11; y++) {
				cells.add(factory.createCell(ElementType.WALL, new Coordinate(x,y)));			}
		}
;

	}


	private Collection<Cell> getCells() {
		return cells;
	}

	
	
	/**
	 * Paint the RobotGameArea on the given graphics
	 * @param g the graphics on which the RobotGameArea should be drawn.
	 */
	public void paint(Graphics g) {
		g.drawLine(0, 0, 0, 600);
		g.drawLine(0, 200, 250, 200);
		g.drawLine(0, 400, 250, 400);
		try {
			for (Cell cell : cells) {

				g.drawImage(cell.getImage(), cell.getCoordinate().getX(), cell.getCoordinate().getY(), null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void clearCells() {
		cells.clear();
	}

	
	@Override
	public void onRobotChangeEvent(RobotChangeEvent event) {
		// look for robot, set that cell to SAND
		moveRobot(event.getCoordinate(),event.getOrientation());
	}

	@Override
	public void onRobotAddedEvent(RobotAddedEvent event) {
		addCell(factory.createCell(ElementType.ROBOT, event.getCoordinate(),event.getOrientation()));

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