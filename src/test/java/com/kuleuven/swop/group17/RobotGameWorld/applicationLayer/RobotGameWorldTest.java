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
import java.io.IOException;
import java.lang.reflect.Field;
import java.rmi.UnexpectedException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldType;
import com.kuleuven.swop.group17.GameWorldApi.Predicate;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.DomainFactory;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementFactory;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.RobotGameWorld.guiLayer.GuiFactory;
import com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas;
import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;
import com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldAction;
import com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldPredicate;
import com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldSnapshot;
import com.kuleuven.swop.group17.RobotGameWorld.types.RobotGameWorldType;
import com.kuleuven.swop.group17.RobotGameWorld.types.SupportedActions;
import com.kuleuven.swop.group17.RobotGameWorld.types.SupportedPredicates;
import com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory;

/**
 * RobotGameWorldTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.Silent.class)
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
	private TypeFactory typeFactory;
	
	@Mock
	private GuiFactory guiFactory;


	
	@Mock
	private RobotGameWorldSnapshot snapshot;
	
	@Mock 
	private Graphics mockGraphics;
	
	@Mock
	private RobotGameWorldType type;
	@Mock
	private RobotGameWorldAction action;
	
	@Mock
	private RobotGameWorldPredicate predicate;
	
	
	
	@Captor
	private ArgumentCaptor<Graphics> graphics;

	@Captor
	private ArgumentCaptor<SupportedActions> supportedAction;	
	@Captor
	private ArgumentCaptor<SupportedPredicates> supportedPredicate;	
	
	@Captor
	private ArgumentCaptor<Set<Element>> elements;

	@InjectMocks
	private RobotGameWorld world;


	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//Stub TypeFactory
		when(typeFactory.createAction(supportedAction.capture())).then(new Answer<RobotGameWorldAction>() {
			@Override
			public RobotGameWorldAction answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				when(action.getAction()).thenReturn((SupportedActions) args[0]);
				return action;
			}
			
		});
		when(typeFactory.createPredicate(supportedPredicate.capture())).then(new Answer<RobotGameWorldPredicate>() {
			@Override
			public RobotGameWorldPredicate answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				when(predicate.getPredicate()).thenReturn((SupportedPredicates) args[0]);
				return predicate;
			}
			
		});
		when(typeFactory.createCoordinate(any(int.class), any(int.class))).then(new Answer<Coordinate>() {

			@Override
			public Coordinate answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				Coordinate c = mock(Coordinate.class);
				when(c.getX()).thenReturn((int)args[0]);
				when(c.getY()).thenReturn((int)args[1]);
				
				return c;
			}
			
		});


		when(typeFactory.createType()).thenReturn(type);
		
		
		
		
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
			Field f = RobotGameWorld.class.getDeclaredField("typeFactory");
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
		verify(robotController).addRobot(any(), any(Orientation.class));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#performAction(com.kuleuven.swop.group17.GameWorldApi.Action)}.
	 */
	@Test
	public void testPerformAction() {
		world.performAction(typeFactory.createAction(SupportedActions.MOVEFORWARD));
		verify(robotController).moveForward();
		world.performAction(typeFactory.createAction(SupportedActions.TURNLEFT));
		verify(robotController).turnLeft();
		world.performAction(typeFactory.createAction(SupportedActions.TURNRIGHT));
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
	@Test(expected = RuntimeException.class)
	public void testPerformActionUnexpectedException() {
		doThrow(new NoSuchElementException("I did not see this comming.")).when(robotController).moveForward();

		world.performAction(typeFactory.createAction(SupportedActions.MOVEFORWARD));
		Mockito.verifyNoInteractions(robotController);
	}
	
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#performAction(com.kuleuven.swop.group17.GameWorldApi.Action)}.
	 */
	@Test
	public void testPerformActionNullAction() {
		String excMessage = "The given action can't be null";
		exceptionRule.expect(NullPointerException.class);
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
		world.evaluate(typeFactory.createPredicate(SupportedPredicates.WALLINFRONT));
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
	@Test(expected = RuntimeException.class)
	public void testEvaluateUnexpectedException() {
		doThrow(new NoSuchElementException("I did not see this comming.")).when(robotController).checkIfWallInFront();

		world.evaluate(typeFactory.createPredicate(SupportedPredicates.WALLINFRONT));
		Mockito.verifyNoInteractions(robotController);
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
		Set<Element> state = constructTestState();

		when(typeFactory.createSnapshot(elements.capture())).thenAnswer(new Answer<RobotGameWorldSnapshot>() {
			@SuppressWarnings("unchecked")
			public RobotGameWorldSnapshot answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				when(snapshot.getElements()).thenReturn((Set<Element>) args[0]);
				return snapshot;
			}
		});
		
		when(elementController.getElements()).thenReturn(state);

		when(typeFactory.createSnapshot(elements.capture())).thenAnswer(new Answer<RobotGameWorldSnapshot>() {
			@SuppressWarnings("unchecked")
			public RobotGameWorldSnapshot answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				when(snapshot.getElements()).thenReturn((Set<Element>) args[0]);
				return snapshot;
			}
		});

		RobotGameWorldSnapshot snap = (RobotGameWorldSnapshot) world.saveState();

		verify(elementController).getElements();
		verify(typeFactory).createSnapshot(any());
		assertEquals(elements.getValue(),state);
		assertEquals(state, snap.getElements());

	}
	
	private Set<Element> constructTestState(){
		DomainFactory f = new DomainFactory();
		ElementFactory ef = f.createElementFactory();
		
		Set<Element> state = new HashSet<Element>();
		Robot r =(Robot) ef.createElement(ElementType.ROBOT, typeFactory.createCoordinate(0, 0));
		r.setOrientation(Orientation.DOWN);
		state.add(r);
		state.add(ef.createElement(ElementType.GOAL,typeFactory.createCoordinate(2, 2)));
		state.add(ef.createElement(ElementType.WALL,typeFactory.createCoordinate(2, 3)));
		state.add(ef.createElement(ElementType.WALL,typeFactory.createCoordinate(3, 2)));
		state.add(ef.createElement(ElementType.WALL,typeFactory.createCoordinate(2, 1)));
		state.add(ef.createElement(ElementType.WALL,typeFactory.createCoordinate(1, 2)));
		return state;
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#restoreState(com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot)}.
	 */
	@Test
	public void testRestoreState() {
		// the mocks robotController and elementController are reset.
		// this means that all previously called invocations and
		// all previously made stubs on these mocks are forgotten.
		// In this case it's used so the count of the number of times a method was called 
		// only keeps in account the statements here.
		reset(robotController);
		reset(elementController);
		
		Set<Element> state = constructTestState();
		
		when(snapshot.getElements()).thenReturn(state);
		
		world.restoreState(snapshot);

		
		
		verify(robotController, times(1)).addRobot(any(Coordinate.class), any(Orientation.class));
		verify(elementController, times(5)).addElement(any(ElementType.class), any(Coordinate.class));
		
	}


	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#restoreState(com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot)}.
	 */
	@Test
	public void testRestoreStateNullState() {
		// the mocks robotController and elementController are reset.
		// this means that all previously called invocations and
		// all previously made stubs on these mocks are forgotten.
		// In this case it's used so the count of the number of times a method was called 
		// only keeps in account the statements here.
		reset(robotController);
		reset(elementController);	

		String excMessage = "The given GameWorldSnapshot can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
			
		world.restoreState(null);

		
		
		verifyNoInteractions(robotController);
		verifyNoInteractions(elementController);
		
	}
	
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#restoreState(com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot)}.
	 */
	@Test
	public void testRestoreStateInvalidState() {
		// the mocks robotController and elementController are reset.
		// this means that all previously called invocations and
		// all previously made stubs on these mocks are forgotten.
		// In this case it's used so the count of the number of times a method was called 
		// only keeps in account the statements here.
		reset(robotController);
		reset(elementController);	

		String excMessage = "The given GameWorldSnapshot is not a valid snapshot for a RobotGameWorld.";
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage(excMessage);
			
		GameWorldSnapshot state = new GameWorldSnapshot() {
		};
		
		world.restoreState(state);

		
		
		verifyNoInteractions(robotController);
		verifyNoInteractions(elementController);
		
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
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#paint(java.awt.Graphics)}.
	 */
	@Test(expected = RuntimeException.class)
	public void testPaintUnexpectedException() {
		doThrow(new NoSuchElementException("I did not see this comming.")).when(robotCanvas).paint(any(Graphics.class));
		world.paint(mockGraphics);
		Mockito.verifyNoInteractions(robotCanvas);
	}
	
	
	
	
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.RobotGameWorld#getType()}.
	 */
	@Test
	public void testGetType() {
		
		
		GameWorldType testType = world.getType();
		
		
		verify(typeFactory).createType();
		assertEquals(type, testType);
	}

}
