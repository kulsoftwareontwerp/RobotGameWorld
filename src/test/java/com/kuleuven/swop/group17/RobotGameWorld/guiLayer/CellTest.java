/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.guiLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.lenient;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;

import java.awt.Image;
import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;
import com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory;

/**
 * CellTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class CellTest {
	private TypeFactory tf;
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	private Cell cell;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tf = new TypeFactory();
		cell = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.ROBOT);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		Cell g = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.WALL);
		Cell g2 = new Cell(tf.createCoordinate(5, 3), Orientation.UP, ElementType.WALL);
		assertNotEquals(g.hashCode(), g2.hashCode());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#setCoordinate(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinate() {
		cell.setCoordinate(tf.createCoordinate(1, 9));
		assertEquals(tf.createCoordinate(1, 9), cell.getCoordinate());

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#setCoordinate(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinateNoCoordinate() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("coordinate can't be null");
		cell.setCoordinate(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#setOrientation(com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testSetOrientationNoOrientation() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("orientation can't be null");
		cell.setOrientation(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#setType(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType)}.
	 */
	@Test
	public void testSetTypeNoType() {
		Cell c = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.GOAL);

		c.setType(null);
		assertEquals(ElementType.SAND, c.getType());

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#Cell(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation, com.kuleuven.swop.group17.RobotGameWorld.types.ElementType)}.
	 */
	@Test
	public void testCell() {
		Cell c = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.WALL);
		try {
			Field f;
			f = Cell.class.getDeclaredField("coordinate");
			f.setAccessible(true);
			assertEquals(tf.createCoordinate(5, 1), f.get(c));
			f = Cell.class.getDeclaredField("orientation");
			f.setAccessible(true);
			assertEquals(Orientation.UP, f.get(c));
			f = Cell.class.getDeclaredField("type");
			f.setAccessible(true);
			assertEquals(ElementType.WALL, f.get(c));
			f = Cell.class.getDeclaredField("image");
			f.setAccessible(true);
			assertNotNull(f.get(c));
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#Cell(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation, com.kuleuven.swop.group17.RobotGameWorld.types.ElementType)}.
	 */
	@Test
	public void testCellNoCoordinate() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("coordinate can't be null");
		new Cell(null, Orientation.UP, ElementType.WALL);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#Cell(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation, com.kuleuven.swop.group17.RobotGameWorld.types.ElementType)}.
	 */
	@Test
	public void testCellNoOrientation() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("orientation can't be null");
		new Cell(tf.createCoordinate(5, 1), null, ElementType.WALL);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#Cell(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation, com.kuleuven.swop.group17.RobotGameWorld.types.ElementType)}.
	 */
	@Test
	public void testCellNoType() {
		Cell c = new Cell(tf.createCoordinate(5, 1), Orientation.UP, null);
		try {
			Field f;
			f = Cell.class.getDeclaredField("coordinate");
			f.setAccessible(true);
			assertEquals(tf.createCoordinate(5, 1), f.get(c));
			f = Cell.class.getDeclaredField("orientation");
			f.setAccessible(true);
			assertEquals(Orientation.UP, f.get(c));
			f = Cell.class.getDeclaredField("type");
			f.setAccessible(true);
			assertEquals(ElementType.SAND, f.get(c));
			f = Cell.class.getDeclaredField("image");
			f.setAccessible(true);
			assertNotNull(f.get(c));
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#setCoordinateOffset(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinateOffset() {
		Coordinate offset = tf.createCoordinate(1, 4);
		Coordinate current = cell.getCoordinate();
		Coordinate newCoordinateWithOffset = current.setX(current.getX() + offset.getX());
		newCoordinateWithOffset = newCoordinateWithOffset.setY(newCoordinateWithOffset.getY() + offset.getY());
		cell.setCoordinateOffset(offset);
		assertEquals(newCoordinateWithOffset, cell.getCoordinate());

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#setCoordinateOffset(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinateOffsetNoCoordinate() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("offset can't be null");
		cell.setCoordinateOffset(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#getCoordinate()}.
	 */
	@Test
	public void testGetCoordinate() {
		Coordinate c = cell.getCoordinate();
		assertEquals(tf.createCoordinate(5, 1), c);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#getType()}.
	 */
	@Test
	public void testGetType() {
		assertEquals(ElementType.ROBOT, cell.getType());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#setType(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType)}.
	 */
	@Test
	public void testSetType() {
		Cell cell = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.ROBOT);
		String resourcePathBefore = "";
		String resourcePathAfter = "";
		try {
			Field f = Cell.class.getDeclaredField("resourcePath");
			f.setAccessible(true);
			resourcePathBefore = (String) f.get(cell);
		} catch (Exception e) {
			System.err.println(e);
		}
		cell.setType(ElementType.WALL);
		assertEquals(ElementType.WALL, cell.getType());
		try {
			Field f = Cell.class.getDeclaredField("resourcePath");
			f.setAccessible(true);
			resourcePathAfter = (String) f.get(cell);
		} catch (Exception e) {
			System.err.println(e);
		}

		assertNotEquals(resourcePathBefore, resourcePathAfter);

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#getOrientation()}.
	 */
	@Test
	public void testGetOrientation() {
		assertEquals(Orientation.UP, cell.getOrientation());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#setOrientation(com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testSetOrientationOfRobot() {
		Cell cell = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.ROBOT);
		String resourcePathBefore = "";
		String resourcePathAfter = "";
		try {
			Field f = Cell.class.getDeclaredField("resourcePath");
			f.setAccessible(true);
			resourcePathBefore = (String) f.get(cell);
		} catch (Exception e) {
			System.err.println(e);
		}
		cell.setOrientation(Orientation.DOWN);
		assertEquals(Orientation.DOWN, cell.getOrientation());
		try {
			Field f = Cell.class.getDeclaredField("resourcePath");
			f.setAccessible(true);
			resourcePathAfter = (String) f.get(cell);
		} catch (Exception e) {
			System.err.println(e);
		}
		assertNotEquals(resourcePathBefore, resourcePathAfter);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#setOrientation(com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testSetOrientationOfNonRobot() {
		Cell cell = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.WALL);
		String resourcePathBefore = "";
		String resourcePathAfter = "";
		try {
			Field f = Cell.class.getDeclaredField("resourcePath");
			f.setAccessible(true);
			resourcePathBefore = (String) f.get(cell);
		} catch (Exception e) {
			System.err.println(e);
		}
		cell.setOrientation(Orientation.DOWN);
		assertEquals(Orientation.DOWN, cell.getOrientation());
		try {
			Field f = Cell.class.getDeclaredField("resourcePath");
			f.setAccessible(true);
			resourcePathAfter = (String) f.get(cell);
		} catch (Exception e) {
			System.err.println(e);
		}
		assertEquals(resourcePathBefore, resourcePathAfter);
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#setOrientation(com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateImageWrongResourcePath() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("image for Cell is not found");
		ElementType type = mock(ElementType.class);
		lenient().when(type.toOrientationString(any(Orientation.class))).thenReturn("thisthingisnotavailable.qmlkdfqmslkfd/qzlijaàz");
		Cell cell = new Cell(tf.createCoordinate(5, 1), Orientation.UP, type);
		
		cell.setOrientation(Orientation.DOWN);

	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#setOrientation(com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test(expected=RuntimeException.class)
	public void testCreateImageIOException() {
		Cell cell = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.GOAL);
		
		try {
			Field f = Cell.class.getDeclaredField("triggerIOException");
			f.setAccessible(true);
			f.set(cell, true);
		}catch(Exception e) {
			System.err.println(e);
		}
		
		cell.setOrientation(Orientation.DOWN);

	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#getImage()}.
	 */
	@Test
	public void testGetImage() {
		Image i = cell.getImage();
		assertNotNull(i);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		Cell c = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.GOAL);
		Cell c2 = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.GOAL);
		
		assertEquals(c, c2);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Cell c = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.GOAL);
		
		assertEquals(c, c);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObjectNull() {
		Cell c = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.GOAL);
		
		assertNotEquals(c, null);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsWrongClass() {
		Cell c = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.GOAL);
		
		assertNotEquals(c, ElementType.SAND);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsWrongCoordinate() {
		Cell c = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.GOAL);
		Cell c2 = new Cell(tf.createCoordinate(2, 1), Orientation.UP, ElementType.GOAL);
		
		assertNotEquals(c, c2);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.Cell#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsType() {
		Cell c = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.GOAL);
		Cell c2 = new Cell(tf.createCoordinate(5, 1), Orientation.UP, ElementType.WALL);
		
		assertNotEquals(c, c2);
	}

}
