package com.kuleuven.swop.group17.RobotGameWorld.types;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CoordinateTest.class, RobotGameWorldActionTest.class, RobotGameWorldPredicateTest.class,
		RobotGameWorldSnapshotTest.class, RobotGameWorldTypeTest.class, TypeFactoryTest.class, ElementTypeTest.class,OrientationTest.class,SupportedActionsTest.class,SupportedPredicatesTest.class})
public class Types {

}
