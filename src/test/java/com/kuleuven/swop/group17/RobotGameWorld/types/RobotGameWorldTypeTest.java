/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.types;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.Predicate;

/**
 * RobotGameWorldTypeTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotGameWorldTypeTest {

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
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldType#supportedActions()}.
	 */
	@Test
	public void testSupportedActions() {
		RobotGameWorldType type = new RobotGameWorldType();
		Set<Action> actions = type.supportedActions();
		List<SupportedActions> sActions =  Arrays.asList(SupportedActions.values());
		List<SupportedActions> rsActions = new ArrayList<SupportedActions>();
		
		for (Action a : actions) {
			RobotGameWorldAction rAction = (RobotGameWorldAction) a;
			SupportedActions sa = rAction.getAction();
			if (sActions.contains(sa)) {
				rsActions.add(sa);
			} else {
				fail("Action returned that's not in supportedActions");
			}
		}
		
		assertTrue("Not all supported Actions are returned.",sActions.size()==rsActions.size());

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldType#supportedPredicates()}.
	 */
	@Test
	public void testSupportedPredicates() {
		RobotGameWorldType type = new RobotGameWorldType();
		Set<Predicate> predicates = type.supportedPredicates();
		List<SupportedPredicates> sPredicates =  Arrays.asList(SupportedPredicates.values());
		List<SupportedPredicates> rsPredicates = new ArrayList<SupportedPredicates>();
		
		for (Predicate p : predicates) {
			RobotGameWorldPredicate rPredicate = (RobotGameWorldPredicate) p;
			SupportedPredicates sp = rPredicate.getPredicate();
			if (sPredicates.contains(sp)) {
				rsPredicates.add(sp);
			} else {
				fail("Predicate returned that's not in supportedPredicates");
			}
		}
		
		assertTrue("Not all supported predicates are returned.",sPredicates.size()==rsPredicates.size());
	}

}
