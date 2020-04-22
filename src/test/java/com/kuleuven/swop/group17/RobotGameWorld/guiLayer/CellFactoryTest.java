/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.guiLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

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
 * CellFactoryTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class CellFactoryTest {
	private TypeFactory tf;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Spy
	private CellFactory factory;
	
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
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.CellFactory#CellFactory()}.
	 */
	@Test
	public void testCellFactory() {
		CellFactory cf = new CellFactory();
		assertNotNull(cf);
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.CellFactory#createCell(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateCellElementTypeCoordinateOrientationNoCoordinate() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("coordinate can't be null");
	    factory.createCell(ElementType.GOAL, null, Orientation.UP);
	
	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.CellFactory#createCell(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateCellElementTypeCoordinateOrientationNoOrientation() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("orientation can't be null");
	    factory.createCell(ElementType.GOAL, tf.createCoordinate(0, 0), null);

	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.CellFactory#createCell(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateCellElementTypeCoordinateOrientation() {
	    Cell c = factory.createCell(ElementType.ROBOT, tf.createCoordinate(0, 0), Orientation.UP);
	    
	    assertEquals(ElementType.ROBOT, c.getType());
	    assertEquals(Orientation.UP, c.getOrientation());
	    assertEquals(tf.createCoordinate(0, 0), c.getCoordinate());
	    

	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.CellFactory#createCell(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testCreateCellElementTypeCoordinate() {
	    Cell c = factory.createCell(ElementType.ROBOT, tf.createCoordinate(0, 0));
	    
	    assertEquals(ElementType.ROBOT, c.getType());
	    assertEquals(tf.createCoordinate(0, 0), c.getCoordinate());
	    }
	
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.CellFactory#createCell(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testCreateCellElementTypeCoordinateNoCoordinate() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("coordinate can't be null");
	    factory.createCell(ElementType.GOAL, null);
	    }
	


}
