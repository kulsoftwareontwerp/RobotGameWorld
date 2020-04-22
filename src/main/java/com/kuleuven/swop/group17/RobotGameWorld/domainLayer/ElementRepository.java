package com.kuleuven.swop.group17.RobotGameWorld.domainLayer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;

/**
 * The ElementRepository manages all elements in the RobotGameWorld.
 * 
 * @version 0.1
 * @author group17
 */
public class ElementRepository {

	private Collection<Element> elements;
	private ElementFactory factory;
	private final int gameAreaHeight = 4;
	private final int gameAreaWidth = 5;

	/**
	 * Create a new ElementRepository
	 */
	ElementRepository() {
		initElementRepository(new ElementFactory(), new HashSet<Element>());
	}

	@SuppressWarnings("unused")
	private ElementRepository(ElementFactory factory, Collection<Element> elements) {
		initElementRepository(factory, elements);
	}

	private void initElementRepository(ElementFactory factory, Collection<Element> elements) {
		this.factory = factory;
		this.elements = elements;
	}

	/**
	 * Retrieve a set of elements by its coordinates
	 * 
	 * @param coordinate The coordinate of the elements to retrieve.
	 * @throws NullPointerException when the given coordinate is null
	 * @return a set with all elements on the given coordinate.
	 */
	public Set<Element> getElements(Coordinate coordinate) {
		if (coordinate == null) {
			throw new NullPointerException("Can't retrieve an element with a Coordinate of NULL");
		}
		HashSet<Element> resultSet = new HashSet<Element>();

		Iterator<Element> iterator = getElements().iterator();
		while (iterator.hasNext()) {
			Element element = iterator.next();
			if (element.getCoordinate().equals(coordinate)) {
				resultSet.add(element);
			}
		}

		return resultSet;
	}

	/**
	 * Add an element to the gameWorld.
	 * 
	 * @param type       The type of the element.
	 * @param coordinate The coordinate of the element.
	 * @throws IllegalArgumentException thrown when type is null or sand.
	 * @throws IllegalArgumentException thrown when coordinate is null.
	 */
	public void addElement(ElementType type, Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException("Coordinate can't be null");
		}
		if (type == null || type == ElementType.SAND) {
			throw new IllegalArgumentException("Illegal elementType to create: " + type);
		}
		Element el = factory.createElement(type, coordinate);
		elements.add(el);
	}

	/**
	 * Retrieve all elements in the gameWorld.
	 * 
	 * @return all elements in the gameWorld.
	 */
	public Set<Element> getElements() {
		return new HashSet<Element>(elements);
	}

	/**
	 * Retrieve the height of the game area.
	 * 
	 * @return the height of the game area
	 */
	public int getGameAreaHeight() {
		return gameAreaHeight;
	}

	/**
	 * Retrieve the width of the game area.
	 * 
	 * @return the width of the game area
	 */
	public int getGameAreaWidth() {
		return gameAreaWidth;
	}

	/**
	 * Retrieve elements based on its type.
	 * 
	 * @param type the ElementType of the elements to retrieve.
	 * @throws NullPointerException when the given elementType is null
	 * @return a set with all elements of the given type.
	 */
	public Set<Element> getElementsByType(ElementType type) {
		if (type == null) {
			throw new NullPointerException("Can't retrieve an element with a type of NULL");
		}
		return getElements().stream().filter(s -> s.getType() == type).collect(Collectors.toSet());

	}

	/**
	 * Remove all elements in the gameWorld
	 */
	public void clearElements() {
		elements.clear();
	}

}