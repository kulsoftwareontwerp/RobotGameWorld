/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.domainLayer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;
import com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory;

/**
 * RobotTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotTest {
	private TypeFactory tf;
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	@Mock
	private Coordinate coordinate;
	@Spy
	@InjectMocks
	private Robot robot;

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
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#getType()}.
	 */
	@Test
	public void testGetType() {
		assertEquals(ElementType.ROBOT, robot.getType());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Robot g = new Robot(tf.createCoordinate(5, 3));
		assertTrue(g.equals(g));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsNull() {
		Robot g = new Robot(tf.createCoordinate(5, 3));
		assertFalse(g.equals(null));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtherClass() {
		Robot g = new Robot(tf.createCoordinate(5, 3));
		assertFalse(g.equals(ElementType.SAND));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsCoordinate() {
		Robot g = new Robot(tf.createCoordinate(5, 3));
		Robot g2 = new Robot(tf.createCoordinate(3, 2));
		assertFalse(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtherElementClass() {
		Robot g = new Robot(tf.createCoordinate(5, 3));
		Wall w = new Wall(tf.createCoordinate(3, 2));
		assertFalse(g.equals(w));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsType() {
		Robot g = new Robot(tf.createCoordinate(5, 3));
		Robot g2 = mock(Robot.class);
		when(g2.getType()).thenReturn(ElementType.WALL);
		when(g2.getCoordinate()).thenReturn(tf.createCoordinate(5, 3));
		assertFalse(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsClassVsType() {
		Robot g = new Robot(tf.createCoordinate(5, 3));
		Wall g2 = mock(Wall.class);
		when(g2.getType()).thenReturn(ElementType.ROBOT);
		when(g2.getCoordinate()).thenReturn(tf.createCoordinate(5, 3));
		assertFalse(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsOrientation() {
		Robot g = new Robot(tf.createCoordinate(5, 3));
		g.setOrientation(Orientation.DOWN);
		Robot g2 = new Robot(tf.createCoordinate(5, 3));
		g2.setOrientation(Orientation.UP);
		assertFalse(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		Robot g = new Robot(tf.createCoordinate(5, 3));
		g.setOrientation(Orientation.DOWN);
		Robot g2 = new Robot(tf.createCoordinate(5, 3));
		g2.setOrientation(Orientation.DOWN);

		assertTrue(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#Robot(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testRobot() {
		Robot g = new Robot(tf.createCoordinate(5, 3));
		assertNotNull(g);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#getOrientation()}.
	 */
	@Test
	public void testGetOrientation() {
		Robot g = new Robot(tf.createCoordinate(5, 3));
		g.setOrientation(Orientation.UP);

		assertEquals(Orientation.UP, g.getOrientation());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#setOrientation(com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testSetOrientation() {
		Robot g = new Robot(tf.createCoordinate(5, 3));
		g.setOrientation(Orientation.UP);
		
		assertEquals(Orientation.UP, g.getOrientation());
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot#setOrientation(com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testSetOrientationNull() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("orientation can't be null");
		
		Robot g = new Robot(tf.createCoordinate(5, 3));
		g.setOrientation(null);
		
		
		assertEquals(Orientation.UP, g.getOrientation());
		
		
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		Robot g = new Robot(tf.createCoordinate(5, 1));
		Robot g2 = new Robot(tf.createCoordinate(5, 3));
		assertNotEquals(g.hashCode(), g2.hashCode());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#getCoordinate()}.
	 */
	@Test
	public void testGetCoordinate() {
		Wall g = new Wall(tf.createCoordinate(5, 3));
		assertEquals(tf.createCoordinate(5, 3), g.getCoordinate());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#setCoordinate(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinateNull() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Coordinate can't be null");
		robot.setCoordinate(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#setCoordinate(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinate() {
		robot.setCoordinate(tf.createCoordinate(5, 3));
		assertEquals(tf.createCoordinate(5, 3), robot.getCoordinate());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#clone()}.
	 */
	@Test(expected = RuntimeException.class)
	public void testCloneCloneNotSupported() {
		Robot w = new Robot(tf.createCoordinate(5, 3));
		try {
			Field f = Element.class.getDeclaredField("cloneSupported");
			f.setAccessible(true);
			f.set(w, false);
		} catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException e) {
			e.printStackTrace();
		}
		w.clone();

	}

}
