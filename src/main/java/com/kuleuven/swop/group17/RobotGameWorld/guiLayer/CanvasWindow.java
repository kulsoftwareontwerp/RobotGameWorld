package com.kuleuven.swop.group17.RobotGameWorld.guiLayer;

import java.awt.Graphics;

import com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.ElementsClearedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent;
import com.kuleuven.swop.group17.RobotGameWorld.events.RobotChangeEvent;
import com.kuleuven.swop.group17.RobotGameWorld.types.ElementType;
import com.kuleuven.swop.group17.RobotGameWorld.types.Orientation;



public class CanvasWindow extends CanvasResource implements GUIListener {

	private GameArea gameArea;






	// methods of CanvasResource that need to be overridden:

	public CanvasWindow(String title) {
		super(title);

		super.width = 1000;
		this.gameArea = new GameArea();

	}

	@Override
	protected void paint(Graphics g) {
	
		gameArea.draw(g);

	}

	
	@Override
	public void onRobotChangeEvent(RobotChangeEvent event) {
		// look for robot, set that cell to SAND
		gameArea.moveRobot(event.getCoordinate().getX(), event.getCoordinate().getY(),
				event.getOrientation());
		super.repaint();
	}

	@Override
	public void onRobotAddedEvent(RobotAddedEvent event) {
		Orientation orientation = event.getOrientation();

		ElementType type = ElementType.ROBOT;

		gameArea.addCell(new Cell(event.getCoordinate().getX(), event.getCoordinate().getY(),
				type.toString().toLowerCase() + orientation.toString()));

		super.repaint();
	}

	@Override
	public void onElementAddedEvent(ElementAddedEvent event) {
		ElementType type = event.getType();

		if (type == null) {
			gameArea.addCell(new Cell(event.getCoordinate().getX(), event.getCoordinate().getY(), "sand"));
		} else {
			gameArea.addCell(new Cell(event.getCoordinate().getX(), event.getCoordinate().getY(), type.toString().toLowerCase()));
		}

		super.repaint();
	}

	@Override
	public void onElementsClearedEvent(ElementsClearedEvent event) {
		gameArea.clearCells();
		
	}

	

}