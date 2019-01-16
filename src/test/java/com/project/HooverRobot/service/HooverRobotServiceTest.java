package com.project.HooverRobot.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.HooverRobot.exception.HooverException;
import com.project.HooverRobot.model.RobotConstants;
import com.project.HooverRobot.model.RobotRequest;
import com.project.HooverRobot.model.RobotResponse;

public class HooverRobotServiceTest {

	private RobotRequest robotRequest;
	private HooverRobotService robotService;

	@Before
	public void setUp() throws Exception {
		robotService = new HooverRobotService();
	}

	@After
	public void tearDown() throws Exception {
		robotRequest = null;
	}
	
	@Test
    public void cleanRoomWithNullRobotRequest() throws Exception {
        final RobotResponse robotResponse = robotService.cleanRoom(null);
        assertEquals(new RobotResponse(null, 0), robotResponse);
    }
	
	@Test
    public void cleanRoomWithIncorrectRoomSize() throws Exception {
		robotRequest = new RobotRequest(
                new int[] {7, 5},
                new int[] {1, 2},
                new int[][] {{1, 0},{2, 2},{2, 3}},
                "NNESEESWNWW");
        try {
            final RobotResponse robotResponse = robotService.cleanRoom(robotRequest);
        } catch (Exception e) {
            assertTrue(e instanceof HooverException);
            assertEquals(RobotConstants.ROOMINCORRECT, e.getMessage());
        }
    }
	
	@Test
    public void cleanRoomWithWithIncorrectInitialPosition() throws Exception {
		robotRequest = new RobotRequest(
                new int[] {5, 5},
                new int[] {7, 2},
                new int[][] {{1, 0},{2, 2},{2, 3}},
                "NNESEESWNWW");
        try {
            final RobotResponse robotResponse = robotService.cleanRoom(robotRequest);
        } catch (Exception e) {
            assertTrue(e instanceof HooverException);
            assertEquals(RobotConstants.INITPOSITIONINCORRECT, e.getMessage());
        }
    }
	
	@Test
    public void cleanRoomWithoutDirtPatches() throws Exception {
		robotRequest = new RobotRequest(
                new int[] {5, 5},
                new int[] {3, 3},
                new int[][] {{1, 0},{2, 2},{2, 3}},
                "NNESN");
        final RobotResponse robotResponse = robotService.cleanRoom(robotRequest);
        assertEquals(new RobotResponse(new int[] {4, 5}, 0), robotResponse);
    }

    @Test
    public void runCleaningWithSomeInvalidCommandsWith2StainsRemoved() throws Exception {
    	robotRequest = new RobotRequest(
                new int[] {5, 5},
                new int[] {1, 2},
                new int[][] {{1, 0},{2, 2},{2, 3}},
                "NNESEESWNWW");
        final RobotResponse robotResponse = robotService.cleanRoom(robotRequest);
        assertEquals(new RobotResponse(new int[] {1, 3}, 1), robotResponse);
    }

}
