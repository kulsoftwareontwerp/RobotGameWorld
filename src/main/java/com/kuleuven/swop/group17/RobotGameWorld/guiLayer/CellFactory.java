package com.kuleuven.swop.group17.RobotGameWorld.guiLayer;

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
	public CellFactory() {
	}
	
	private final int OFFSET_GAMEAREA_CELLS = 4;

	/**
	 * Create a cell with the given ElementType,Orientation and Coordinate
	 * 
	 * @param type			The ElementType corresponding to the Cell.
	 * @param coordinate	The coordinate of the cell.
	 * @param orientation 	The orientation of the cell
	 * @return The cell corresponding to the given parameters.
	 * @throws NullPointerException  when coordinate is null.
	 */
	public Cell createCell(ElementType type, Coordinate coordinate,Orientation orientation) {
		if(coordinate == null) {
			throw new NullPointerException("coordinate can't be null.");
		}
		coordinate.setY(coordinate.getY()+OFFSET_GAMEAREA_CELLS);
		
		Cell cell;	
		


		cell = new Cell(coordinate, orientation,type);
		

		return cell;
	}
	
	/**
	 * Create a cell with the given ElementType and Coordinate
	 * 
	 * @param type			The ElementType corresponding to the Cell.
	 * @param coordinate	The coordinate of the cell.
	 * @return The cell corresponding to the given parameters.
	 * @throws NullPointerException  when coordinate is null.
	 */
	public Cell createCell(ElementType type, Coordinate coordinate) {
		

		return createCell(type,coordinate,null);
	}
}
