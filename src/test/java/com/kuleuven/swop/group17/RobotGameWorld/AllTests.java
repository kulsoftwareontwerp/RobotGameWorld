package com.kuleuven.swop.group17.RobotGameWorld;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.kuleuven.swop.group17.RobotGameWorld.applicationLayer.ApplicationLayer;
import com.kuleuven.swop.group17.RobotGameWorld.types.Types;

@RunWith(Suite.class)
@SuiteClasses({Types.class,ApplicationLayer.class})
public class AllTests {

}
