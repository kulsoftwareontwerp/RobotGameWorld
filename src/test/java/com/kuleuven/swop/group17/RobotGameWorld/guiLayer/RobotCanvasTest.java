/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.guiLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.ElementsClearedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotChangedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;
import com.kuleuven.swop.group17.RobotGameWorld.types.TypeFactory;


/**
 * RobotCanvasTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotCanvasTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	@Spy
	private TypeFactory typeFactory;
	@Spy
	private CellFactory factory;

	@Spy
	private HashMap<Coordinate, Cell> cells;

	@Spy
	@InjectMocks
	private RobotCanvas canvas;

	@Captor
	private ArgumentCaptor<ElementType> type;
	@Captor
	private ArgumentCaptor<Coordinate> coordinate;
	@Captor
	private ArgumentCaptor<Orientation> orientation;

	private EventFactory eventFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		eventFactory = new EventFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas#RobotCanvas()}.
	 */
	@Test
	public void testRobotCanvas() {
		RobotCanvas canvas = new RobotCanvas();
		Field f;
		try {
			f = RobotCanvas.class.getDeclaredField("cells");
			f.setAccessible(true);
			assertNotNull(f.get(canvas));
			f = RobotCanvas.class.getDeclaredField("factory");
			f.setAccessible(true);
			assertNotNull(f.get(canvas));
			f = RobotCanvas.class.getDeclaredField("typeFactory");
			f.setAccessible(true);
			assertNotNull(f.get(canvas));

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas#paint(java.awt.Graphics)}.
	 */
	@Test
	public void testPaint() {
		Graphics g = spy(Graphics.class);

		when(g.getClipBounds()).thenReturn(new Rectangle(500, 600));
		canvas.paint(g);
		verify(g, atLeastOnce()).drawLine(any(Integer.class), any(Integer.class), any(Integer.class),
				any(Integer.class));
		verify(g, atLeastOnce()).drawImage(any(Image.class), any(Integer.class), any(Integer.class), any());

	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas#paint(java.awt.Graphics)}.
	 */
	@Test
	public void testPaintNullGraphics() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Graphics object can't be null");
		canvas.paint(null);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas#onRobotChangeEvent(com.kuleuven.swop.group17.RobotGameWorld.events.RobotChangedEvent)}.
	 */
	@Test
	public void testOnRobotChangeEventNoRobot() {
		reset(factory);
		RobotChangedEvent e = eventFactory.createRobotChangedEvent(typeFactory.createCoordinate(1, 0), Orientation.UP);
		canvas.onRobotChangeEvent(e);
		verify(factory,times(0)).createCell(eq(ElementType.ROBOT), coordinate.capture(), orientation.capture());
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas#onRobotChangeEvent(com.kuleuven.swop.group17.RobotGameWorld.events.RobotChangedEvent)}.
	 */
	@Test
	public void testOnRobotChangeEvent() {
		Cell spyRobot = spy(factory.createCell(ElementType.ROBOT, typeFactory.createCoordinate(1, 0), Orientation.UP));
		cells.put(spyRobot.getCoordinate(), spyRobot);
		reset(factory);
		RobotChangedEvent e = eventFactory.createRobotChangedEvent(typeFactory.createCoordinate(0, 0), Orientation.LEFT);
		canvas.onRobotChangeEvent(e);
		verify(factory).createCell(eq(ElementType.ROBOT), coordinate.capture(), orientation.capture());
		assertEquals(e.getCoordinate(), coordinate.getValue());
		assertEquals(e.getOrientation(), orientation.getValue());
		assertEquals(ElementType.SAND,spyRobot.getType());
		
		Optional<Cell> robotCell = cells.values().stream().filter(c -> c.getType() == ElementType.ROBOT).findFirst();
		assertTrue(robotCell.isPresent());
		
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas#onRobotChangeEvent(com.kuleuven.swop.group17.RobotGameWorld.events.RobotChangedEvent)}.
	 */
	@Test
	public void testOnRobotChangeEventNull() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("event can't be null");
		canvas.onRobotChangeEvent(null);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas#onRobotAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent)}.
	 */
	@Test
	public void testOnRobotAddedEvent() {
		RobotAddedEvent e = eventFactory.createRobotAddedEvent(typeFactory.createCoordinate(0, 0), Orientation.UP);
		canvas.onRobotAddedEvent(e);
		verify(factory,atLeastOnce()).createCell(type.capture(), coordinate.capture(), orientation.capture());
		assertEquals(e.getCoordinate(), coordinate.getValue());
		assertEquals(ElementType.ROBOT, type.getValue());
		assertEquals(e.getOrientation(), orientation.getValue());

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas#onRobotAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent)}.
	 */
	@Test
	public void testOnRobotAddedEventNull() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("event can't be null");
		canvas.onRobotAddedEvent(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas#onElementAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent)}.
	 */
	@Test
	public void testOnElementAddedEvent() {
		ElementAddedEvent e = eventFactory.createElementAddedEvent(typeFactory.createCoordinate(0, 0),
				ElementType.WALL);
		canvas.onElementAddedEvent(e);
		verify(factory,atLeastOnce()).createCell(type.capture(), coordinate.capture());
		assertEquals(e.getCoordinate(), coordinate.getValue());
		assertEquals(e.getType(), type.getValue());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas#onElementAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent)}.
	 */
	@Test
	public void testOnElementAddedEventNull() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("event can't be null");
		canvas.onElementAddedEvent(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas#onElementsClearedEvent(com.kuleuven.swop.group17.RobotGameWorld.events.ElementsClearedEvent)}.
	 */
	@Test
	public void testOnElementsClearedEvent() {
		canvas.onElementsClearedEvent(mock(ElementsClearedEvent.class));
		verify(cells).clear();
		verify(cells, atLeastOnce()).put(any(Coordinate.class), any(Cell.class));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.guiLayer.RobotCanvas#onElementsClearedEvent(com.kuleuven.swop.group17.RobotGameWorld.events.ElementsClearedEvent)}.
	 */
	@Test
	public void testOnElementsClearedEventNull() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("event can't be null");
		canvas.onElementsClearedEvent(null);
	}
}
