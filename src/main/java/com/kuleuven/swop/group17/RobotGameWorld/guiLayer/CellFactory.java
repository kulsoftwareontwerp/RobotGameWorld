package com.kuleuven.swop.group17.RobotGameWorld.guiLayer;

import java.io.IOException;

import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;

/**
 * The CellFactory is responsible for creating cells in the GUI.
 * 
 * @version 0.1
 * @author group17
 */
public class CellFactory {
	CellFactory() {
	}

	/**
	 * Create a cell with the given ElementType,Orientation and Coordinate
	 * 
	 * @param type        The ElementType corresponding to the Cell.
	 * @param coordinate  The coordinate of the cell.
	 * @param orientation The orientation of the cell
	 * @return The cell corresponding to the given parameters.
	 * @throws IllegalArgumentException when coordinate is null.
	 * @throws IllegalArgumentException when orientation is null.
	 */
	public Cell createCell(ElementType type, Coordinate coordinate, Orientation orientation) {
		if (coordinate == null) {
			throw new IllegalArgumentException("coordinate can't be null.");
		}
		if (orientation == null) {
			throw new IllegalArgumentException("orientation can't be null.");
		}

		return new Cell(coordinate, orientation, type);
	}

	/**
	 * Create a cell with the given ElementType and Coordinate
	 * 
	 * @param type       The ElementType corresponding to the Cell.
	 * @param coordinate The coordinate of the cell.
	 * @return The cell corresponding to the given parameters.
	 * @throws IllegalArgumentException when coordinate is null.
	 */
	public Cell createCell(ElementType type, Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException("coordinate can't be null.");
		}
		return createCell(type, coordinate, Orientation.UP);
	}
}
