/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.types;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ElementTypeTest
 *
 * @version 0.1
 * @author group17
 */
public class ElementTypeTest {

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
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.types.ElementType#toOrientationString(com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testToOrientationString() {
		for (ElementType et : ElementType.values()) {
			for (Orientation o : Orientation.values()) {
				if (et == ElementType.ROBOT) {
					assertEquals(et.toString() + o.toString(), et.toOrientationString(o));
				} else {
					assertEquals(et.toString(), et.toOrientationString(o));
				}
			}
		}
		
		assertEquals(ElementType.ROBOT.toString() + Orientation.UP.toString(), ElementType.ROBOT.toOrientationString(null));		
	}

}
