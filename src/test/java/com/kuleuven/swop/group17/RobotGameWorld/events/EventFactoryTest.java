/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;
import com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory;

/**
 * EventFactoryTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class EventFactoryTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Spy
	private EventFactory ef;
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
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory#EventFactory()}.
	 */
	@Test
	public void testEventFactory() {
		EventFactory ef = new EventFactory();
		assertNotNull(ef);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory#createRobotChangedEvent(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateRobotChangedEvent() {
		RobotChangedEvent e = ef.createRobotChangedEvent(tf.createCoordinate(0, 0), Orientation.DOWN);
		assertNotNull(e);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory#createRobotChangedEvent(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateRobotChangedEventNoCoordinate() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("A robot needs to have a coordinate");
		RobotChangedEvent e = ef.createRobotChangedEvent(null, Orientation.DOWN);
		assertEquals(null, e);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory#createRobotChangedEvent(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateRobotChangedEventNoOrientation() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("A robot needs to have an orientation");
		RobotChangedEvent e = ef.createRobotChangedEvent(tf.createCoordinate(0, 0), null);
		assertEquals(null, e);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory#createRobotAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateRobotAddedEvent() {
		RobotAddedEvent e = ef.createRobotAddedEvent(tf.createCoordinate(0, 0), Orientation.DOWN);
		assertNotNull(e);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory#createRobotAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateRobotAddedEventNoCoordinate() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("A robot needs to have a coordinate");
		RobotAddedEvent e = ef.createRobotAddedEvent(null, Orientation.DOWN);
		assertEquals(null, e);

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory#createRobotAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateRobotAddedEventNoOrientation() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("A robot needs to have an orientation");
		RobotAddedEvent e = ef.createRobotAddedEvent(tf.createCoordinate(0, 0), null);
		assertEquals(null, e);

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory#createElementsClearedEvent()}.
	 */
	@Test
	public void testCreateElementsClearedEvent() {
		ElementsClearedEvent e = ef.createElementsClearedEvent();
		assertNotNull(e);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory#createElementAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.ElementType)}.
	 */
	@Test
	public void testCreateElementAddedEvent() {
		ElementAddedEvent e = ef.createElementAddedEvent(tf.createCoordinate(0, 0), ElementType.ROBOT);
		assertNotNull(e);
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory#createElementAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.ElementType)}.
	 */
	@Test
	public void testCreateElementAddedEventNoCoordinate() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("An element needs to have a coordinate");
		ElementAddedEvent e = ef.createElementAddedEvent(null, ElementType.ROBOT);
		assertEquals(null, e);
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory#createElementAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.ElementType)}.
	 */
	@Test
	public void testCreateElementAddedEventNoType() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("An element needs to have a type");
		ElementAddedEvent e = ef.createElementAddedEvent(tf.createCoordinate(0, 0), null);
		assertEquals(null, e);
	}

}
