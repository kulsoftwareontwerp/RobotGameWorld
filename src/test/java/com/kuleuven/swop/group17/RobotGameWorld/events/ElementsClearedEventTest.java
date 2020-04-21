/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.events;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ElementsClearedEventTest
 *
 * @version 0.1
 * @author group17
 */
public class ElementsClearedEventTest {

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
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.events.ElementsClearedEvent#ElementsClearedEvent()}.
	 */
	@Test
	public void testElementsClearedEvent() {
		ElementsClearedEvent e= new ElementsClearedEvent();
		assertNotNull(e);
	}

}
