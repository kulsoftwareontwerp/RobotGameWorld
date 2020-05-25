package com.kuleuven.swop.group17.RobotGameWorld.applicationLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.NoSuchElementException;
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
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementRepository;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Goal;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Wall;
import com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotChangedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;
import com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory;

/**
 * ElementControllerTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotControllerTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Mock
	private GUIListener listener;
	@Spy
	private HashSet<GUIListener> guiListeners;
	@Mock
	private ElementRepository elementRepository;
	@Mock
	private EventFactory eventFactory;
	@Mock
	private Robot robot;

	@Captor
	private ArgumentCaptor<Coordinate> coordinateCaptor;
	@Captor
	private ArgumentCaptor<Orientation> orientationCaptor;
	@Captor
	private ArgumentCaptor<ElementType> elementTypeCaptor;
	@InjectMocks
	private RobotController controller;

	@Before
	public void setUp() throws Exception {
		TypeFactory tf = new TypeFactory();

		guiListeners.clear();
		guiListeners.add(listener);

		when(elementRepository.getElementsByType(ElementType.ROBOT)).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> robots = new HashSet<Element>();
				robots.add(robot);
				return robots;
			}

		});

		when(elementRepository.getGameAreaHeight()).thenReturn(5);
		when(elementRepository.getGameAreaWidth()).thenReturn(5);
		
		
		
		
		when(robot.getOrientation()).thenReturn(Orientation.UP);
		when(robot.getCoordinate()).thenReturn(tf.createCoordinate(1, 1));

		when(eventFactory.createRobotAddedEvent(coordinateCaptor.capture(), orientationCaptor.capture()))
				.thenReturn(mock(RobotAddedEvent.class));
		when(eventFactory.createRobotChangedEvent(coordinateCaptor.capture(), orientationCaptor.capture()))
				.thenReturn(mock(RobotChangedEvent.class));
		
		
		

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRobotController() {
		RobotController newController = new RobotController(elementRepository);
		try {
			Field f = RobotController.class.getDeclaredField("eventFactory");
			f.setAccessible(true);
			assertTrue("eventFactory was not initialised", f.get(newController) != null);
			f = RobotController.class.getDeclaredField("guiListeners");
			f.setAccessible(true);
			assertTrue("guiListeners was not initialised", f.get(newController) != null);
			f = RobotController.class.getDeclaredField("elementRepository");
			f.setAccessible(true);
			assertTrue("elementRepository was not initialised", f.get(newController) != null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			fail("One or more of the required fields were not declared.");
		}
	}

	@Test
	public void testRobotControllerNullElementRepository() {
		String excMessage = "The given elementRepository can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		new RobotController(null);
	}

	@Test
	public void testTurnLeftNoRobot() {
		String excMessage = "No robot has been added to the RobotGameWorld.";
		exceptionRule.expect(NoSuchElementException.class);
		exceptionRule.expectMessage(excMessage);

		when(elementRepository.getElementsByType(ElementType.ROBOT)).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> robots = new HashSet<Element>();
				return robots;
			}

		});

		controller.turnLeft();

		verify(robot, times(0)).getOrientation();
		verify(robot, times(0)).getCoordinate();
		verify(robot, times(0)).setOrientation(any());
		verify(robot, times(0)).setCoordinate(any());

	}

	@Test
	public void testTurnLeft() {
		controller.turnLeft();
		Orientation o = robot.getOrientation();
		verify(robot).setOrientation(orientationCaptor.capture());

		assertEquals(o.getLeft(), orientationCaptor.getValue());

	}

	@Test
	public void testTurnRightNoRobot() {
		String excMessage = "No robot has been added to the RobotGameWorld.";
		exceptionRule.expect(NoSuchElementException.class);
		exceptionRule.expectMessage(excMessage);

		when(elementRepository.getElementsByType(ElementType.ROBOT)).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> robots = new HashSet<Element>();
				return robots;
			}

		});

		controller.turnRight();

		verify(robot, times(0)).getOrientation();
		verify(robot, times(0)).getCoordinate();
		verify(robot, times(0)).setOrientation(any());
		verify(robot, times(0)).setCoordinate(any());

	}

	@Test
	public void testTurnRight() {
		controller.turnRight();
		Orientation o = robot.getOrientation();
		verify(robot).setOrientation(orientationCaptor.capture());

		assertEquals(o.getRight(), orientationCaptor.getValue());
	}

	@Test
	public void testMoveForwardNoRobot() {
		String excMessage = "No robot has been added to the RobotGameWorld.";
		exceptionRule.expect(NoSuchElementException.class);
		exceptionRule.expectMessage(excMessage);

		when(elementRepository.getElementsByType(ElementType.ROBOT)).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> robots = new HashSet<Element>();
				return robots;
			}

		});

		controller.moveForward();

		verify(robot, times(0)).getOrientation();
		verify(robot, times(0)).getCoordinate();
		verify(robot, times(0)).setOrientation(any());
		verify(robot, times(0)).setCoordinate(any());

	}

	private void moveForwardChecks(int expectedX, int expectedY, int times) {
		TypeFactory tf = new TypeFactory();
		Coordinate expectedC = tf.createCoordinate(expectedX, expectedY);

		controller.moveForward();

		verify(robot, atLeast(1)).getOrientation();
		verify(robot, atLeast(1)).getCoordinate();

		verify(robot, times(0)).setOrientation(any(Orientation.class));

		if (times > 0) {
			verify(robot, times(times)).setCoordinate(coordinateCaptor.capture());
			assertEquals(expectedC, coordinateCaptor.getValue());
		}
		else {
			verify(robot, times(times)).setCoordinate(any(Coordinate.class));
			
		}
	}
	
	@Test
	public void testMoveForwardWithBoundaryInFront() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(0, 0);
		when(robot.getOrientation()).thenReturn(Orientation.UP);
		when(robot.getCoordinate()).thenReturn(rc);

		
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		moveForwardChecks(rc.getX(), rc.getY(), 0);

	}
	
	@Test
	public void testBoundaryInFrontUp() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(0, -1);
		assertTrue(controller.checkIfCoordinateNotWithinGameBoundries(rc));

	}
	
	@Test
	public void testBoundaryInFrontLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(-1, 0);
		assertTrue(controller.checkIfCoordinateNotWithinGameBoundries(rc));

	}
	
	@Test
	public void testBoundaryInFrontDown() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(4, 5);
		assertTrue(controller.checkIfCoordinateNotWithinGameBoundries(rc));

	}
	
	@Test
	public void testBoundaryInFrontRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(5, 4);
		assertTrue(controller.checkIfCoordinateNotWithinGameBoundries(rc));
	}
	
	
	
	
	

	@Test
	public void testMoveForwardWithWallInFrontUp() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(robot.getOrientation()).thenReturn(Orientation.UP);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() - 1) {
					elements.add(mock(Wall.class));
				}
				return elements;
			}

		});

		moveForwardChecks(rc.getX(), rc.getY(), 0);

	}

	@Test
	public void testMoveForwardWithoutWallInFrontUp() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(robot.getOrientation()).thenReturn(Orientation.UP);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() - 1) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});

		moveForwardChecks(rc.getX(), rc.getY() - 1, 1);

	}

	@Test
	public void testMoveForwardWithWallInFrontLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(robot.getOrientation()).thenReturn(Orientation.LEFT);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() - 1 && c.getY() == rc.getY()) {
					elements.add(mock(Wall.class));
				}
				return elements;
			}

		});

		moveForwardChecks(rc.getX(), rc.getY(), 0);
	}

	@Test
	public void testMoveForwardWithoutWallInFrontLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(robot.getOrientation()).thenReturn(Orientation.LEFT);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() - 1 && c.getY() == rc.getY()) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});

		moveForwardChecks(rc.getX()-1, rc.getY() ,1);
	}

	@Test
	public void testMoveForwardWithWallInFrontDown() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(robot.getOrientation()).thenReturn(Orientation.DOWN);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() + 1) {
					elements.add(mock(Wall.class));
				}
				return elements;
			}

		});
		moveForwardChecks(rc.getX(), rc.getY(), 0);
	}

	@Test
	public void testMoveForwardWithoutWallInFrontDown() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(robot.getOrientation()).thenReturn(Orientation.DOWN);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() + 1) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});
		moveForwardChecks(rc.getX(), rc.getY() + 1, 1);
	}

	@Test
	public void testMoveForwardWithWallInFrontRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(robot.getOrientation()).thenReturn(Orientation.RIGHT);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() + 1 && c.getY() == rc.getY()) {
					elements.add(mock(Wall.class));
				}
				return elements;
			}

		});

		moveForwardChecks(rc.getX(), rc.getY(),0);
	}

	@Test
	public void testMoveForwardWithoutWallInFrontRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(robot.getOrientation()).thenReturn(Orientation.RIGHT);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() + 1 && c.getY() == rc.getY()) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});

		moveForwardChecks(rc.getX()+1, rc.getY(), 1);

	}

	@Test
	public void testAddRobot() {
		TypeFactory tf = new TypeFactory();
		Orientation orientation = Orientation.UP;
		Coordinate coordinate = tf.createCoordinate(1, 4);
		controller.addRobot(coordinate, orientation);

		verify(elementRepository).addElement(elementTypeCaptor.capture(), coordinateCaptor.capture());
		assertEquals(ElementType.ROBOT, elementTypeCaptor.getValue());
		assertEquals(coordinate, coordinateCaptor.getValue());

		verify(robot).setOrientation(orientationCaptor.capture());
		assertEquals(orientation, orientationCaptor.getValue());

		verify(eventFactory).createRobotAddedEvent(coordinateCaptor.capture(), orientationCaptor.capture());
		assertEquals(orientation, orientationCaptor.getValue());
		assertEquals(coordinate, coordinateCaptor.getValue());

		verify(listener).onRobotAddedEvent(any(RobotAddedEvent.class));
	}

	@Test
	public void testAddRobotNullOrientation() {
		String excMessage = "The given Orientation can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		TypeFactory tf = new TypeFactory();
		Orientation orientation = null;
		Coordinate coordinate = tf.createCoordinate(1, 4);
		controller.addRobot(coordinate, orientation);

		verify(elementRepository, times(0)).addElement(any(ElementType.class), any(Coordinate.class));

		verify(robot, times(0)).setOrientation(any(Orientation.class));

		verify(eventFactory, times(0)).createRobotAddedEvent(any(Coordinate.class), any(Orientation.class));

		verify(listener, times(0)).onRobotAddedEvent(any(RobotAddedEvent.class));

	}

	@Test
	public void testAddRobotNullCoordinate() {
		String excMessage = "The given Coordinate can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		Orientation orientation = Orientation.UP;
		Coordinate coordinate = null;
		controller.addRobot(coordinate, orientation);

		verify(elementRepository, times(0)).addElement(any(ElementType.class), any(Coordinate.class));

		verify(robot, times(0)).setOrientation(any(Orientation.class));

		verify(eventFactory, times(0)).createRobotAddedEvent(any(Coordinate.class), any(Orientation.class));

		verify(listener, times(0)).onRobotAddedEvent(any(RobotAddedEvent.class));

	}

	@Test
	public void testCheckIfWallInFrontNoRobot() {
		String excMessage = "No robot has been added to the RobotGameWorld.";
		exceptionRule.expect(NoSuchElementException.class);
		exceptionRule.expectMessage(excMessage);

		when(elementRepository.getElementsByType(ElementType.ROBOT)).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> robots = new HashSet<Element>();
				return robots;
			}

		});

		controller.checkIfWallInFront();

		verify(robot, times(0)).getOrientation();
		verify(robot, times(0)).getCoordinate();

	}

	@Test
	public void testCheckIfWallInFrontWithWallInFrontUp() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(robot.getOrientation()).thenReturn(Orientation.UP);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() - 1) {
					elements.add(mock(Wall.class));
				}
				return elements;
			}

		});

		checkIfWallInFrontChecks(true, 1);

	}

	@Test
	public void testCheckIfWallInFrontWithoutWallInFrontUp() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(robot.getOrientation()).thenReturn(Orientation.UP);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() - 1) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});

		checkIfWallInFrontChecks(false, 1);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		checkIfWallInFrontChecks(false, 2);

	}

	@Test
	public void testCheckIfWallInFrontWithWallInFrontLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(robot.getOrientation()).thenReturn(Orientation.LEFT);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() - 1 && c.getY() == rc.getY()) {
					elements.add(mock(Wall.class));
				}
				return elements;
			}

		});

		checkIfWallInFrontChecks(true, 1);

	}

	@Test
	public void testCheckIfWallInFrontWithoutWallInFrontLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(robot.getOrientation()).thenReturn(Orientation.LEFT);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() - 1 && c.getY() == rc.getY()) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});

		checkIfWallInFrontChecks(false, 1);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		checkIfWallInFrontChecks(false, 2);

	}

	@Test
	public void testCheckIfWallInFrontWithWallInFrontDown() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(robot.getOrientation()).thenReturn(Orientation.DOWN);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() + 1) {
					elements.add(mock(Wall.class));
				}
				return elements;
			}

		});
		checkIfWallInFrontChecks(true, 1);
	}

	@Test
	public void testCheckIfWallInFrontWithoutWallInFrontDown() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(robot.getOrientation()).thenReturn(Orientation.DOWN);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() + 1) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});
		checkIfWallInFrontChecks(false, 1);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		checkIfWallInFrontChecks(false, 2);
	}

	@Test
	public void testCheckIfWallInFrontWithWallInFrontRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(robot.getOrientation()).thenReturn(Orientation.RIGHT);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() + 1 && c.getY() == rc.getY()) {
					elements.add(mock(Wall.class));
				}
				return elements;
			}

		});

		checkIfWallInFrontChecks(true, 1);

	}

	@Test
	public void testCheckIfWallInFrontWithoutWallInFrontRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(robot.getOrientation()).thenReturn(Orientation.RIGHT);
		when(robot.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() + 1 && c.getY() == rc.getY()) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});

		checkIfWallInFrontChecks(false, 1);

		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		checkIfWallInFrontChecks(false, 2);

	}
	

	@Test
	public void testCheckIfWallInFrontWithBoundaryInFront() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(0, 0);
		when(robot.getOrientation()).thenReturn(Orientation.LEFT);
		when(robot.getCoordinate()).thenReturn(rc);
	
		checkIfWallInFrontChecks(true, 1);

	}

	private void checkIfWallInFrontChecks(Boolean valid, int times) {
		Boolean result = controller.checkIfWallInFront();

		verify(robot, times(times)).getOrientation();
		verify(robot, times(times)).getCoordinate();

		assertEquals(valid, result);
	}

	@Captor
	private ArgumentCaptor<GUIListener> listenerCaptor;

	@Test
	public void testRemoveListener() {
		GUIListener listenerToRemove = mock(GUIListener.class);
		guiListeners.add(listenerToRemove);
		assertEquals(2, guiListeners.size());

		controller.removeListener(listenerToRemove);
		verify(guiListeners).remove(listenerCaptor.capture());

		assertEquals(listenerToRemove, listenerCaptor.getValue());
		assertEquals(1, guiListeners.size());

	}

	@Test
	public void testRemoveListenerNullListener() {
		String excMessage = "The given GUIListener can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		GUIListener listenerToRemove = mock(GUIListener.class);
		guiListeners.add(listenerToRemove);
		assertEquals(2, guiListeners.size());

		controller.removeListener(null);
		verify(guiListeners, times(0)).remove(any(GUIListener.class));

		assertEquals(2, guiListeners.size());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddListener() {
		reset(guiListeners);

		GUIListener listenerToAdd = mock(GUIListener.class);
		assertEquals(1, guiListeners.size());

		controller.addListener(listenerToAdd);
		verify(guiListeners).add(listenerCaptor.capture());

		assertEquals(listenerToAdd, listenerCaptor.getValue());
		assertEquals(2, guiListeners.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddListenerNullListener() {
		reset(guiListeners);
		String excMessage = "The given GUIListener can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		GUIListener listenerToAdd = null;
		assertEquals(1, guiListeners.size());

		controller.addListener(listenerToAdd);
		verify(guiListeners, times(0)).add(any(GUIListener.class));

		assertEquals(1, guiListeners.size());
	}

}
