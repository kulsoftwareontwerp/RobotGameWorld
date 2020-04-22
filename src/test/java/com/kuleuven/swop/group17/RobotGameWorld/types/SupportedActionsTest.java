/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.types;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * SupportedActionsTest
 *
 * @version 0.1
 * @author group17
 */
public class SupportedActionsTest {

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

	@Test
	public void testToString() {
		assertEquals("Turn Left", SupportedActions.TURNLEFT.toString());
		assertEquals("Turn Right", SupportedActions.TURNRIGHT.toString());
		assertEquals("Move Forward", SupportedActions.MOVEFORWARD.toString());
	}

}
