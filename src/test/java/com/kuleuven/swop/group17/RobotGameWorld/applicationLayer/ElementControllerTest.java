/**
 * 
 */
package com.kuleuven.swop.group17.RobotGameWorld.applicationLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Field;
import java.util.HashSet;

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
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementRepository;
import com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.ElementsClearedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;

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
	
	@InjectMocks
	private ElementController controller;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		guiListeners.add(listener);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#ElementController(com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementRepository)}.
	 */
	@Test
	public void testElementController() {
		ElementController newController = new ElementController(elementRepository);
		try {
			Field f = ElementController.class.getDeclaredField("guiListeners");
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
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#ElementController(com.kuleuven.swop.group17.RobotGameWorld.domainLayer.ElementRepository)}.
	 */
	@Test
	public void testElementControllerNullElementRepository() {
		String excMessage = "The given elementRepository can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		
		
		new ElementController(null);
	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#clearElements()}.
	 */
	@Test
	public void testClearElements() {


		controller.clearElements();
		
		verify(elementRepository).clearElements();
		verify(listener).onElementsClearedEvent(any(ElementsClearedEvent.class));
	}

	
	@Captor
	private ArgumentCaptor<ElementType> typeCaptor;
	@Captor
	private ArgumentCaptor<Coordinate> coordinateCaptor;
	@Captor
	private ArgumentCaptor<ElementAddedEvent> elementAddedEventCaptor;
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#addElement(com.kuleuven.swop.group17.RobotGameWorld.types.ElementType, com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testAddElement() {
		ElementType type = ElementType.WALL;
		Coordinate coordinate = new Coordinate(1,4);
		controller.addElement(type, coordinate);
		
		verify(elementRepository).addElement(typeCaptor.capture(), coordinateCaptor.capture());
		assertEquals(type, typeCaptor.getValue());
		assertEquals(coordinate, coordinateCaptor.getValue());
		verify(listener).onElementAddedEvent(elementAddedEventCaptor.capture());
		
		ElementAddedEvent event = elementAddedEventCaptor.getValue();
		//event.getCoordinate()
	
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#getElements()}.
	 */
	@Test
	public void testGetElements() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#removeListener(com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener)}.
	 */
	@Test
	public void testRemoveListener() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ElementController#addListener(com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener)}.
	 */
	@Test
	public void testAddListener() {
		fail("Not yet implemented");
	}

}
