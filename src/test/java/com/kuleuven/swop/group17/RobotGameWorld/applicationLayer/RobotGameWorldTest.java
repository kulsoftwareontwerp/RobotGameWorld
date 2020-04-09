/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.applicationLayer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas;

/**
 * RobotGameWorldTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotGameWorldTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Mock
	private RobotController robotController;

	@Mock
	private ElementController elementController;

	@Mock
	private RobotCanvas robotCanvas;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@InjectMocks
	private RobotGameWorld world;

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#RobotGameWorld()}.
	 */
	@Test
	public void testRobotGameWorld() {
		// The constructor of the RobotGameWorld is responsible for creating the
		// RobotController, the ElementController, the elementRepository and the
		// RobotCanvas.
		// It should also add the correct listeners to the needed controllers.
		// At last The RobotGameWorld itself is also initialized by adding elements and
		// a robot.

		RobotGameWorld newWorld = new RobotGameWorld();
		for(Field f : RobotGameWorld.class.getDeclaredFields()) {
			f.setAccessible(true);
			try {
				System.out.println(f.getName()+ " " +f.get(newWorld));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			Field f =  RobotGameWorld.class.getDeclaredField("robotController");
			f.setAccessible(true);
			assertTrue("RobotController was not initialised", f.get(newWorld) != null);
			f =  RobotGameWorld.class.getDeclaredField("elementController");
			f.setAccessible(true);
			assertTrue("ElementController was not initialised", f.get(newWorld) != null);
//			f =  RobotGameWorld.class.getDeclaredField("elementRepository");
//			f.setAccessible(true);
//			assertTrue("ElementRepository was not initialised", f.get(newWorld) != null);
			f =  RobotGameWorld.class.getDeclaredField("robotCanvas");
			f.setAccessible(true);
			assertTrue("RobotCanvas was not initialised", f.get(newWorld) != null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			fail("One or more of the required fields were not declared.");
		}

//		RobotGameWorld world = new RobotGameWorld();
//		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#performAction(com.kuleuven.swop.group17.GameWorldApi.Action)}.
	 */
	@Test
	public void testPerformAction() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#evaluate(com.kuleuven.swop.group17.GameWorldApi.Predicate)}.
	 */
	@Test
	public void testEvaluate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#saveState()}.
	 */
	@Test
	public void testSaveState() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#restoreState(com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot)}.
	 */
	@Test
	public void testRestoreState() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#paint(java.awt.Graphics)}.
	 */
	@Test
	public void testPaint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#getType()}.
	 */
	@Test
	public void testGetType() {
		fail("Not yet implemented");
	}

}
