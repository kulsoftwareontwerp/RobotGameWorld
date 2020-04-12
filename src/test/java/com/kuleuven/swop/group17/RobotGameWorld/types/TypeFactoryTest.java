/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.types;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element;

/**
 * TypeFactoryTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class TypeFactoryTest {
	private TypeFactory tf;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.tf = new TypeFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory#createSnapshot(java.util.Set)}.
	 */
	@Test
	public void testCreateSnapshot() {
		RobotGameWorldSnapshot snapshot = tf.createSnapshot(new HashSet<Element>());
		assertNotNull(snapshot);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory#createType()}.
	 */
	@Test
	public void testCreateType() {
		RobotGameWorldType type = tf.createType();
		assertNotNull(type);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory#createAction(com.kuleuven.swop.group17.RobotGameWorld.types.SupportedActions)}.
	 */
	@Test
	public void testCreateAction() {
		RobotGameWorldAction action = tf.createAction(mock(SupportedActions.class));
		assertNotNull(action);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory#createPredicate(com.kuleuven.swop.group17.RobotGameWorld.types.SupportedPredicates)}.
	 */
	@Test
	public void testCreatePredicate() {
		RobotGameWorldPredicate predicate = tf.createPredicate(mock(SupportedPredicates.class));
		assertNotNull(predicate);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory#createCoordinate(int, int)}.
	 */
	@Test
	public void testCreateCoordinate() {
		Coordinate coordinate = tf.createCoordinate(0,0);
		assertNotNull(coordinate);
	
	}

}
