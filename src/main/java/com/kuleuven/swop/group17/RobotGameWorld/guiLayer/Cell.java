package com.kuleuven.swop.group17.RobotGameWorld.guiLayer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;

/**
 * A Cell is the visual representation of an Element. No element corresponds to
 * a cell with ElementType sand.
 * 
 * @version 0.1
 * @author group17
 */
public class Cell {


	private ElementType type;
	private String resourcePath;
	private Coordinate coordinate;
	private Orientation orientation;

	/**
	 * Create a cell with the given ElementType,Orientation and Coordinate
	 * 
	 * @param type        The ElementType corresponding to the Cell.
	 * @param coordinate  The coordinate of the cell.
	 * @param orientation The orientation of the cell
	 */
	public Cell(Coordinate coordinate, Orientation orientation, ElementType type) {
		setCoordinate(coordinate);
		setType(type);
		setOrientation(orientation);
	}

	/**
	 * Set the Coordinate of this Cell.
	 * 
	 * @param coordinate The coordinate to set this cell to.
	 */
	public void setCoordinate(Coordinate coordinate) {
		if (coordinate == null) {
			throw new NullPointerException("coordinate can't be null.");
		}
		Coordinate copy = new Coordinate(coordinate.getX(), coordinate.getY());

		this.coordinate = coordinate;
	}

	/**
	 * Change the coordinate of this cell with the given offset.
	 * 
	 * @param offset The offset to adapt the coordinates with.
	 */
	public void setCoordinateOffset(Coordinate offset) {
		Coordinate copy = new Coordinate(offset.getX(), offset.getY());

		coordinate.setX(coordinate.getX() + copy.getX());
		coordinate.setY(coordinate.getY() + copy.getY());
	}

	/**
	 * Retrieve the coordinate for this cell
	 * 
	 * @return the coordinate for this cell.
	 */
	public Coordinate getCoordinate() {
		Coordinate copy = new Coordinate(coordinate.getX(), coordinate.getY());
		return copy;
	}

	/**
	 * Retrieve the type of this Cell
	 * 
	 * @return the type of this Cell.
	 */
	public ElementType getType() {
		return type;
	}

	/**
	 * Set the type of this cell
	 * 
	 * @param type The elementType to set the type of this cell to.
	 */
	public void setType(ElementType type) {
		if (type == null) {
			type = ElementType.SAND;
		}
		this.type = type;

		setResourcePath("images/" + getType().toOrientationString(getOrientation()) + ".png");

	}

	/**
	 * Retrieve the orientation associated with this Cell
	 * 
	 * @return the orientation associated with this Cell
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * Set the orientation associated with this Cell
	 * 
	 * @param orientation The new orientation to be associated with this Cell
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
		setResourcePath("images/" + getType().toOrientationString(getOrientation()) + ".png");
	}

	private String getResourcePath() {
		return resourcePath;
	}

	private void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;

	}

	/**
	 * Retrieve the image associated with this Cell
	 * 
	 * @return the image associated with this Cell
	 * @throws IOException if an error occurs during reading or when notable to
	 *                     create required ImageInputStream
	 */
	public BufferedImage getImage() throws IOException {
		BufferedImage image;
		InputStream in = getClass().getClassLoader().getResourceAsStream(getResourcePath());

		if (in == null) {
			throw new IllegalArgumentException("image for Cell is not found ");
		} else {
			try {
				image = ImageIO.read(in);
			} catch (IOException e) {
				System.err.println("Got an error while loading in image");
				throw e;
			}
		}

		return image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
