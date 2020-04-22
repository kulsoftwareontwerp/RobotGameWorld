/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.guiLayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * GuiFactoryTest
 *
 * @version 0.1
 * @author group17
 */
public class GuiFactoryTest {

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

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.GuiFactory#GuiFactory()}.
	 */
	@Test
	public void testGuiFactory() {
		GuiFactory gf = new GuiFactory();
		assertNotNull(gf);
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.GuiFactory#createRobotCanvas()}.
	 */
	@Test
	public void testCreateRobotCanvas() {
		GuiFactory gf = new GuiFactory();
		RobotCanvas canvas = gf.createRobotCanvas();
		assertNotNull(canvas);
	}

}
