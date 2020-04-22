/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.domainLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory;

/**
 * ElementFactoryTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class ElementFactoryTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	@Spy
	private ElementFactory factory;
	
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
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementFactory#ElementFactory()}.
	 */
	@Test
	public void testElementFactory() {
		ElementFactory ef = new ElementFactory();
		assertNotNull(ef);
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementFactory#createElement(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testCreateElementGoal() {
		Element g = factory.createElement(ElementType.GOAL, tf.createCoordinate(4, 3));
		assertTrue(g instanceof Goal);
		assertEquals(tf.createCoordinate(4, 3), g.getCoordinate());
		assertEquals(ElementType.GOAL, g.getType());
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementFactory#createElement(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testCreateElementRobot() {
		Element g = factory.createElement(ElementType.ROBOT, tf.createCoordinate(4, 3));
		assertTrue(g instanceof Robot);
		assertEquals(tf.createCoordinate(4, 3), g.getCoordinate());
		assertEquals(ElementType.ROBOT, g.getType());
	}
	
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementFactory#createElement(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testCreateElementWall() {
		Element g = factory.createElement(ElementType.WALL, tf.createCoordinate(4, 3));
		assertTrue(g instanceof Wall);
		assertEquals(tf.createCoordinate(4, 3), g.getCoordinate());
		assertEquals(ElementType.WALL, g.getType());
	}
	
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementFactory#createElement(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testCreateElementNoCoordinate() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Coordinate can't be null");
		
		Element g = factory.createElement(ElementType.WALL, null);
		assertEquals(null, g);
	}
	
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementFactory#createElement(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testCreateElementNoType() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("ElementType can't be null");
		
		Element g = factory.createElement(null, tf.createCoordinate(4, 3));
		assertEquals(null, g);
	}
	
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementFactory#createElement(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testCreateElementNoSandType() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Illegal elementType to create:");
		
		Element g = factory.createElement(ElementType.SAND, tf.createCoordinate(4, 3));
		assertEquals(null, g);
	}
}
