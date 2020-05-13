package com.kuleuven.swop.group17.RobotGameWorld.guiLayer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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

	private static Map<String, BufferedImage> cachedImages;

	private boolean triggerIOException;

	/**
	 * Create a cell with the given ElementType,Orientation and Coordinate
	 * 
	 * @param type        The ElementType corresponding to the Cell.
	 * @param coordinate  The coordinate of the cell.
	 * @param orientation The orientation of the cell
	 * @throws IllegalArgumentException when coordinate is null.
	 * @throws IllegalArgumentException when orientation is null.
	 */
	public Cell(Coordinate coordinate, Orientation orientation, ElementType type) {
		triggerIOException = false;
		if (cachedImages == null) {
			cachedImages = new HashMap<String, BufferedImage>();
		}
		setCoordinate(coordinate);
		setType(type);
		setOrientation(orientation);

	}

	/**
	 * Set the Coordinate of this Cell.
	 * 
	 * @param coordinate The coordinate to set this cell to.
	 * @throws IllegalArgumentException when coordinate is null.
	 */
	public void setCoordinate(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException("coordinate can't be null.");
		}
		this.coordinate = coordinate;
	}

	/**
	 * Change the coordinate of this cell with the given offset.
	 * 
	 * @param offset The offset to adapt the coordinates with.
	 * @throws IllegalArgumentException when the given offset is null.
	 */
	public void setCoordinateOffset(Coordinate offset) {
		if (offset == null) {
			throw new IllegalArgumentException("offset can't be null.");
		}
		coordinate = coordinate.setX(coordinate.getX() + offset.getX());
		coordinate = coordinate.setY(coordinate.getY() + offset.getY());
	}

	/**
	 * Retrieve the coordinate for this cell
	 * 
	 * @return the coordinate for this cell.
	 */
	public Coordinate getCoordinate() {
		return coordinate;
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
		createImage();
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
	 * @throws IllegalArgumentException when orientation is null.
	 */
	public void setOrientation(Orientation orientation) {
		if (orientation == null) {
			throw new IllegalArgumentException("orientation can't be null.");
		}
		this.orientation = orientation;
		setResourcePath("images/" + getType().toOrientationString(getOrientation()) + ".png");
		createImage();
	}

	private String getResourcePath() {
		return resourcePath;
	}

	private void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;

	}

	private void createImage() {
		if (!cachedImages.containsKey(getResourcePath())) {

			BufferedImage image;
			InputStream in = getClass().getClassLoader().getResourceAsStream(getResourcePath());

			if (in == null) {
				throw new IllegalArgumentException("image for Cell is not found");
			} else {
				try {
					if (triggerIOException) {
						throw new IOException();
					}
					image = ImageIO.read(in);
				} catch (IOException e) {
					System.err.println("Got an error while loading in image");
					throw new RuntimeException(e);
				}
			}
			cachedImages.put(getResourcePath(),image);
		}
	}

	/**
	 * Retrieve the image associated with this Cell
	 * 
	 * @return the image associated with this Cell
	 */
	public BufferedImage getImage() {
//		return image;
		return cachedImages.get(getResourcePath());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coordinate.hashCode();
		result = prime * result + type.hashCode();
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
		if (!coordinate.equals(other.coordinate))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
