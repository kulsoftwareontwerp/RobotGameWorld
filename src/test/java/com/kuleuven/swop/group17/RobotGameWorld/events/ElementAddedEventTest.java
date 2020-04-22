/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.events;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;
import com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory;

/**
 * ElementAddedEventTest
 *
 * @version 0.1
 * @author group17
 */
public class ElementAddedEventTest {
	private TypeFactory tf;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tf = new TypeFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent#ElementAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.ElementType)}.
	 */
	@Test
	public void testElementAddedEvent() {
		ElementAddedEvent e = new ElementAddedEvent(tf.createCoordinate(0, 0), ElementType.WALL);
		assertNotNull(e);	
		}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent#getCoordinate()}.
	 */
	@Test
	public void testGetCoordinate() {
		ElementAddedEvent e = new ElementAddedEvent(tf.createCoordinate(0, 0), ElementType.WALL);
		assertEquals(tf.createCoordinate(0, 0), e.getCoordinate());
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent#getType()}.
	 */
	@Test
	public void testGetType() {
		ElementAddedEvent e = new ElementAddedEvent(tf.createCoordinate(0, 0), ElementType.WALL);
		assertEquals(ElementType.WALL, e.getType());
	}

}
