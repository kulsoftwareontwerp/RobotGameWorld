/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;
import com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory;

/**
 * RobotAddedEventTest
 *
 * @version 0.1
 * @author group17
 */
public class RobotAddedEventTest {
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
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent#RobotAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testRobotAddedEvent() {
		RobotAddedEvent e = new RobotAddedEvent(tf.createCoordinate(0, 0), Orientation.DOWN);
		assertNotNull(e);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent#getCoordinate()}.
	 */
	@Test
	public void testGetCoordinate() {
		RobotAddedEvent e = new RobotAddedEvent(tf.createCoordinate(0, 0), Orientation.DOWN);
		assertEquals(tf.createCoordinate(0, 0), e.getCoordinate());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent#getOrientation()}.
	 */
	@Test
	public void testGetOrientation() {
		RobotAddedEvent e = new RobotAddedEvent(tf.createCoordinate(0, 0), Orientation.DOWN);
		assertEquals(Orientation.DOWN, e.getOrientation());
	}

}
