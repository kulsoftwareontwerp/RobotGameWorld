/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.domainLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

import com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld;
import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory;

/**
 * WallTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class WallTest {
	private TypeFactory tf;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Mock
	private Coordinate coordinate;
	@Spy
	@InjectMocks
	private Wall wall;

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
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall#getType()}.
	 */
	@Test
	public void testGetType() {
		assertEquals(ElementType.WALL, wall.getType());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Wall g = new Wall(tf.createCoordinate(5, 3));
		assertTrue(g.equals(g));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsNull() {
		Wall g = new Wall(tf.createCoordinate(5, 3));
		assertFalse(g.equals(null));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtherClass() {
		Wall g = new Wall(tf.createCoordinate(5, 3));
		assertFalse(g.equals(ElementType.SAND));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsCoordinate() {
		Wall g = new Wall(tf.createCoordinate(5, 3));
		Wall g2 = new Wall(tf.createCoordinate(3, 2));
		assertFalse(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtherElementClass() {
		Wall g = new Wall(tf.createCoordinate(5, 3));
		Robot w = new Robot(tf.createCoordinate(3, 2));
		assertFalse(g.equals(w));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsType() {
		Wall g = new Wall(tf.createCoordinate(5, 3));
		Wall g2 = mock(Wall.class);
		when(g2.getType()).thenReturn(ElementType.ROBOT);
		when(g2.getCoordinate()).thenReturn(tf.createCoordinate(5, 3));
		assertFalse(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsClassVsType() {
		Wall g = new Wall(tf.createCoordinate(5, 3));
		Robot g2 = mock(Robot.class);
		when(g2.getType()).thenReturn(ElementType.WALL);
		when(g2.getCoordinate()).thenReturn(tf.createCoordinate(5, 3));
		assertFalse(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		Wall g = new Wall(tf.createCoordinate(5, 3));
		Wall g2 = new Wall(tf.createCoordinate(5, 3));

		assertTrue(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall#Wall(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testWall() {
		Wall w = new Wall(tf.createCoordinate(5, 3));
		assertNotNull(w);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		Wall g = new Wall(tf.createCoordinate(5, 1));
		Wall g2 = new Wall(tf.createCoordinate(5, 3));
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
		wall.setCoordinate(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#setCoordinate(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinate() {
		wall.setCoordinate(tf.createCoordinate(5, 3));
		assertEquals(tf.createCoordinate(5, 3), wall.getCoordinate());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#clone()}.
	 */
	@Test
	public void testClone() {
		Wall w = new Wall(tf.createCoordinate(5, 3));
		Wall clone = (Wall) w.clone();
		assertTrue(clone != w);
		assertTrue(clone.equals(w));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#clone()}.
	 */
	@Test(expected = RuntimeException.class)
	public void testCloneCloneNotSupported() {
		Wall w = new Wall(tf.createCoordinate(5, 3));
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
