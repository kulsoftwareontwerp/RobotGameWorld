/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.applicationLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.Predicate;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas;
import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;
import com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldAction;
import com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldPredicate;
import com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldSnapshot;
import com.kuleuven.swop.group17.RobotGameWorld.types.SupportedActions;
import com.kuleuven.swop.group17.RobotGameWorld.types.SupportedPredicates;

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

	@Mock
	private RobotGameWorldSnapshot snapshot;
	@Mock
	private DependencyFactory factory;
	
	@Mock 
	private Graphics mockGraphics;
	@Captor
	private ArgumentCaptor<Graphics> graphics;

	@Captor
	private ArgumentCaptor<Set<Element>> elements;

	@InjectMocks
	private RobotGameWorld world;

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
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#RobotGameWorld()}.
	 */
	@Test
	public void testRobotGameWorldFieldsInitialised() {
		// The constructor of the RobotGameWorld is responsible for creating the
		// RobotController, the ElementController, the elementRepository and the
		// RobotCanvas.

		RobotGameWorld newWorld = new RobotGameWorld();
		try {
			Field f = DependencyFactory.class.getDeclaredField("snapshotFactory");
			f.setAccessible(true);
			assertTrue("RobotGameWorldSnapshotFactory was not initialised", f.get(newWorld) != null);
			f = RobotGameWorld.class.getDeclaredField("robotController");
			f.setAccessible(true);
			assertTrue("RobotController was not initialised", f.get(newWorld) != null);
			f = RobotGameWorld.class.getDeclaredField("elementController");
			f.setAccessible(true);
			assertTrue("ElementController was not initialised", f.get(newWorld) != null);
			f = RobotGameWorld.class.getDeclaredField("robotCanvas");
			f.setAccessible(true);
			assertTrue("RobotCanvas was not initialised", f.get(newWorld) != null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			fail("One or more of the required fields were not declared.");
		}
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#RobotGameWorld()}.
	 */
	@Test
	public void testRobotGameWorldListenersAdded() {
		// The constructor should also add the correct listeners to the needed
		// controllers.
		// At last The RobotGameWorld itself is also initialized by adding elements and
		// a robot.
		verify(elementController).addListener(any(GUIListener.class));
		verify(robotController).addListener(any(GUIListener.class));

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#RobotGameWorld()}.
	 */
	@Test
	public void testRobotGameWorldInitialized() {
		// At last The RobotGameWorld itself is also initialized by adding elements and
		// a robot. A robotGameWorld should at least add a robot, otherwise it won't be
		// able to do any meaningful actions.
		verify(robotController).addRobot(any(Coordinate.class), any(Orientation.class));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#performAction(com.kuleuven.swop.group17.GameWorldApi.Action)}.
	 */
	@Test
	public void testPerformAction() {
		world.performAction(new RobotGameWorldAction(SupportedActions.MOVEFORWARD));
		verify(robotController).moveForward();
		world.performAction(new RobotGameWorldAction(SupportedActions.TURNLEFT));
		verify(robotController).turnLeft();
		world.performAction(new RobotGameWorldAction(SupportedActions.TURNRIGHT));
		verify(robotController).turnRight();
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#performAction(com.kuleuven.swop.group17.GameWorldApi.Action)}.
	 */
	@Test
	public void testPerformActionInvalidAction() {
		String excMessage = "The given action is not a supported action for a RobotGameWorld.";
		exceptionRule.expect(UnsupportedOperationException.class);
		exceptionRule.expectMessage(excMessage);
		Action action = new Action() {
		};
		world.performAction(action);
		Mockito.verifyNoInteractions(robotController);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#performAction(com.kuleuven.swop.group17.GameWorldApi.Action)}.
	 */
	@Test
	public void testPerformActionNullAction() {
		String excMessage = "The given action can't be null";
		exceptionRule.expect(UnsupportedOperationException.class);
		exceptionRule.expectMessage(excMessage);
		world.performAction(null);
		verifyNoInteractions(robotController);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#evaluate(com.kuleuven.swop.group17.GameWorldApi.Predicate)}.
	 */
	@Test
	public void testEvaluate() {
		world.evaluate(new RobotGameWorldPredicate(SupportedPredicates.WALLINFRONT));
		verify(robotController).checkIfWallInFront();
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#evaluate(com.kuleuven.swop.group17.GameWorldApi.Predicate)}.
	 */
	@Test
	public void testEvaluateInvalidPredicate() {
		String excMessage = "The given predicate is not a supported predicate for a RobotGameWorld.";
		exceptionRule.expect(UnsupportedOperationException.class);
		exceptionRule.expectMessage(excMessage);
		Predicate predicate = new Predicate() {
		};
		world.evaluate(predicate);
		verifyNoInteractions(robotController);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#evaluate(com.kuleuven.swop.group17.GameWorldApi.Predicate)}.
	 */
	@Test
	public void testEvaluateNullPredicate() {
		String excMessage = "The given predicate can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		world.evaluate(null);
		verifyNoInteractions(robotController);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#saveState()}.
	 */
	@Test
	public void testSaveState() {
		HashSet<Element> state = new HashSet<Element>();
		state.add(new Robot(new Coordinate(0, 0)));
		when(elementController.getElements()).thenReturn(state);

		when(factory.createSnapshot(elements.capture())).thenAnswer(new Answer<RobotGameWorldSnapshot>() {
			@SuppressWarnings("unchecked")
			public RobotGameWorldSnapshot answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				when(snapshot.getElements()).thenReturn((Set<Element>) args[0]);
				return snapshot;
			}
		});

		RobotGameWorldSnapshot snap = (RobotGameWorldSnapshot) world.saveState();

		verify(elementController).getElements();
		verify(factory).createSnapshot(any());
		assertEquals(elements.getValue(),state);
		assertEquals(state, snap.getElements());

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#restoreState(com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot)}.
	 */
	@Test
	public void testRestoreState() {
		Set<Element> state = new HashSet<Element>();
		state.add(new Robot(new Coordinate(0, 0)));
		state.add(new Wall(new Coordinate(2, 2)));
		state.add(new Wall(new Coordinate(2, 3)));
		state.add(new Wall(new Coordinate(3, 2)));
		state.add(new Wall(new Coordinate(2, 1)));
		state.add(new Wall(new Coordinate(1, 2)));
		
		when(snapshot.getElements()).thenReturn(state);

		
		world.restoreState(snapshot);

		verify(robotController, times(1)).addRobot(any(Coordinate.class), any(Orientation.class));
		verify(elementController, times(5)).addElement(any(ElementType.class), any(Coordinate.class));
		
	}


	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#paint(java.awt.Graphics)}.
	 */
	@Test
	public void testPaint() {

		world.paint(mockGraphics);

		verify(robotCanvas).paint(graphics.capture());
		assertEquals(mockGraphics, graphics.getValue());
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#paint(java.awt.Graphics)}.
	 */
	@Test
	public void testPaintNullGraphics() {
		String excMessage = "The given Graphics can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		world.paint(null);

		
		verifyNoInteractions(robotCanvas);
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
