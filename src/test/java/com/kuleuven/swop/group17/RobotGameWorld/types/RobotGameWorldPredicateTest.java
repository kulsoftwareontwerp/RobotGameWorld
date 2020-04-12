/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.types;

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
import org.mockito.junit.MockitoJUnitRunner;

/**
 * RobotGameWorldPredicateTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotGameWorldPredicateTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

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
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldPredicate#RobotGameWorldPredicate(com.kuleuven.swop.group17.RobotGameWorld.types.SupportedPredicates)}.
	 */
	@Test
	public void testRobotGameWorldPredicate() {
		RobotGameWorldPredicate predicate = new RobotGameWorldPredicate(SupportedPredicates.WALLINFRONT);
		try {
			Field f = RobotGameWorldPredicate.class.getDeclaredField("predicate");
			f.setAccessible(true);
			assertTrue("predicate was not initialised",
					(SupportedPredicates) f.get(predicate) == SupportedPredicates.WALLINFRONT);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			fail("One or more of the required fields were not declared.");
		}
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldPredicate#RobotGameWorldPredicate(com.kuleuven.swop.group17.RobotGameWorld.types.SupportedPredicates)}.
	 */
	@Test
	public void testRobotGameWorldPredicateNullPredicate() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("The given predicate can't be null");
		RobotGameWorldPredicate predicate = new RobotGameWorldPredicate(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldPredicate#getPredicate()}.
	 */
	@Test
	public void testGetPredicate() {
		RobotGameWorldPredicate predicate = new RobotGameWorldPredicate(SupportedPredicates.WALLINFRONT);
		assertEquals(SupportedPredicates.WALLINFRONT,predicate.getPredicate());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldPredicate#toString()}.
	 */
	@Test
	public void testToString() {
		SupportedPredicates sp = mock(SupportedPredicates.class);
		when(sp.toString()).thenReturn("predicate test");
		RobotGameWorldPredicate predicate = new RobotGameWorldPredicate(sp);

		assertEquals("predicate test", predicate.toString());	}

}
