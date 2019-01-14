package com.project.HooverRobot.utils;

import org.junit.After;
import org.junit.Before;

public class HooverUtilsTest {
	
	private HooverUtils robotUtils;

	@Before
    public void setUp() {
		robotUtils = new HooverUtils();
    }

    @After
    public void tearDown() {
    	robotUtils = null;
    }
    
    
}
