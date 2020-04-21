package com.kuleuven.swop.group17.RobotGameWorld.events;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ElementAddedEventTest.class, ElementsClearedEventTest.class, EventFactoryTest.class,
		RobotAddedEventTest.class, RobotChangedEventTest.class })
public class Events {

}
