/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.applicationLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.DomainFactory;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementFactory;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementRepository;
import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Robot;
import com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.ElementsClearedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.EventFactory;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener;
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
public class ElementControllerTest {

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
	@Captor
	private ArgumentCaptor<Coordinate> coordinateCaptor;
	@Captor
	private ArgumentCaptor<ElementType> elementTypeCaptor;

	@InjectMocks
	private ElementController controller;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		guiListeners.clear();
		guiListeners.add(listener);

		when(eventFactory.createElementsClearedEvent()).thenReturn(mock(ElementsClearedEvent.class));
		when(eventFactory.createElementAddedEvent(coordinateCaptor.capture(), elementTypeCaptor.capture()))
				.thenAnswer(new Answer<ElementAddedEvent>() {

					@Override
					public ElementAddedEvent answer(InvocationOnMock invocation) throws Throwable {
						ElementAddedEvent e = mock(ElementAddedEvent.class);

						return e;
					}

				});

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#ElementController(com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementRepository)}.
	 */
	@Test
	public void testElementController() {
		ElementController newController = new ElementController(elementRepository);
		try {
			Field f = ElementController.class.getDeclaredField("eventFactory");
			f.setAccessible(true);
			assertTrue("eventFactory was not initialised", f.get(newController) != null);
			f = ElementController.class.getDeclaredField("guiListeners");
			f.setAccessible(true);
			assertTrue("guiListeners was not initialised", f.get(newController) != null);
			f = ElementController.class.getDeclaredField("elementRepository");
			f.setAccessible(true);
			assertTrue("elementRepository was not initialised", f.get(newController) != null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			fail("One or more of the required fields were not declared.");
		}
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#ElementController(com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementRepository)}.
	 */
	@Test
	public void testElementControllerNullElementRepository() {
		String excMessage = "The given elementRepository can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		new ElementController(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#clearElements()}.
	 */
	@Test
	public void testClearElements() {

		controller.clearElements();

		verify(elementRepository).clearElements();

		verify(eventFactory).createElementsClearedEvent();
		verify(listener).onElementsClearedEvent(any(ElementsClearedEvent.class));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#addElement(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testAddElement() {
		TypeFactory tf = new TypeFactory();
		ElementType type = ElementType.WALL;
		Coordinate coordinate = tf.createCoordinate(1, 4);
		controller.addElement(type, coordinate);

		verify(elementRepository).addElement(elementTypeCaptor.capture(), coordinateCaptor.capture());
		assertEquals(type, elementTypeCaptor.getValue());
		assertEquals(coordinate, coordinateCaptor.getValue());

		verify(eventFactory).createElementAddedEvent(coordinateCaptor.capture(), elementTypeCaptor.capture());
		assertEquals(type, elementTypeCaptor.getValue());
		assertEquals(coordinate, coordinateCaptor.getValue());
		verify(listener).onElementAddedEvent(any(ElementAddedEvent.class));

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#addElement(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testAddElementNullElementType() {
		String excMessage = "The given ElementType can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		TypeFactory tf = new TypeFactory();
		Coordinate coordinate = tf.createCoordinate(1, 4);
		controller.addElement(null, coordinate);

		verify(elementRepository, times(0)).addElement(any(ElementType.class), any(Coordinate.class));

		verify(eventFactory, times(0)).createElementAddedEvent(any(Coordinate.class), any(ElementType.class));
		verify(listener, times(0)).onElementAddedEvent(any(ElementAddedEvent.class));

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#addElement(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testAddElementNullCoordinate() {
		String excMessage = "The given Coordinate can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		controller.addElement(ElementType.WALL, null);

		verify(elementRepository, times(0)).addElement(any(ElementType.class), any(Coordinate.class));

		verify(eventFactory, times(0)).createElementAddedEvent(any(Coordinate.class), any(ElementType.class));
		verify(listener, times(0)).onElementAddedEvent(any(ElementAddedEvent.class));

	}

	private Set<Element> constructTestState() {
		TypeFactory tf = new TypeFactory();
		DomainFactory f = new DomainFactory();
		ElementFactory ef = f.createElementFactory();

		Set<Element> state = new HashSet<Element>();
		Robot r = (Robot) ef.createElement(ElementType.ROBOT, tf.createCoordinate(0, 0));
		r.setOrientation(Orientation.DOWN);
		state.add(r);
		state.add(ef.createElement(ElementType.GOAL, tf.createCoordinate(2, 2)));
		state.add(ef.createElement(ElementType.WALL, tf.createCoordinate(2, 3)));
		state.add(ef.createElement(ElementType.WALL, tf.createCoordinate(3, 2)));
		state.add(ef.createElement(ElementType.WALL, tf.createCoordinate(2, 1)));
		state.add(ef.createElement(ElementType.WALL, tf.createCoordinate(1, 2)));
		return state;
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#getElements()}.
	 */
	@Test
	public void testGetElements() {
		Set<Element> state = constructTestState();
		when(elementRepository.getElements()).thenReturn(state);

		Set<Element> returnState = controller.getElements();

		verify(elementRepository).getElements();
		assertEquals(state, returnState);
	}

	@Captor
	private ArgumentCaptor<GUIListener> listenerCaptor;

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#removeListener(com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener)}.
	 */
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

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#removeListener(com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener)}.
	 */
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

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#addListener(com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener)}.
	 */
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

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#addListener(com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener)}.
	 */
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
		verify(guiListeners,times(0)).add(any(GUIListener.class));


		assertEquals(1, guiListeners.size());
	}

}
