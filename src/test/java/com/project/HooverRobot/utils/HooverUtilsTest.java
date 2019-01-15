package com.project.HooverRobot.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.HooverRobot.exception.HooverException;
import com.project.HooverRobot.model.RobotRequest;

public class HooverUtilsTest {

	private HooverUtils robotUtils;
	private RobotRequest robotRequest;

	private static final String ROOMINCORRECT = "The room size used is incorrect or not valid. Please check your room size parameter.";
	private static final String INITPOSITIONINCORRECT = "The initial position is incorrect or not valid. Please check your initial position parameter.";
	private static final String INSTRUCTIONSNULL = "The instructions list passed as parameter is null. Please input a valid combination.";
	private static final String INSTRUCTIONSINCORRECT = "The instruction list contains invalid characters. Please input one of this four: N, S, E, W.";


	@Before
	public void setUp() {
		robotUtils = new HooverUtils();
	}

	@After
	public void tearDown() {
		robotUtils = null;
		robotRequest = null;
	}

	@Test()
	public void validateNullRoomSize() {
		robotRequest = new RobotRequest(null, null, null, null);
		try {
			robotUtils.validateRoomSize(robotRequest.getRoomSize());
		} catch (Exception e) {
			assertTrue(e instanceof HooverException);
			assertEquals(ROOMINCORRECT, e.getMessage());
		}
	}

	@Test
	public void validateRoomSizeWithCorrectValues() throws Exception {
		robotRequest = new RobotRequest(new int[] {2, 2}, null, null, null);
		try {
			robotUtils.validateRoomSize(robotRequest.getRoomSize());
		} catch (Exception e) {
			assertTrue(false);
		}
		assertTrue(true);
	}

	@Test
	public void validateRoomSizeWithOneElement() throws Exception {
		robotRequest = new RobotRequest(new int[] {3}, null, null, null);
		try {
			robotUtils.validateRoomSize(robotRequest.getRoomSize());
		} catch (Exception e) {
			assertTrue(e instanceof HooverException);
			assertEquals(ROOMINCORRECT, e.getMessage());
		}
	}

	@Test
	public void validateRoomSizeWithThreeElements() throws Exception {
		robotRequest = new RobotRequest(new int[] {3,3,3}, null, null, null);
		try {
			robotUtils.validateRoomSize(robotRequest.getRoomSize());
		} catch (Exception e) {
			assertTrue(e instanceof HooverException);
			assertEquals(ROOMINCORRECT, e.getMessage());
		}
	}

	@Test
	public void validateRoomSizeWithIncorrectCoordinate() throws Exception {
		robotRequest = new RobotRequest(new int[] {-1, 2}, null, null, null);
		try {
			robotUtils.validateRoomSize(robotRequest.getRoomSize());
		} catch (Exception e) {
			assertTrue(e instanceof HooverException);
			assertEquals(ROOMINCORRECT, e.getMessage());
		}
	}

	@Test
	public void validateRoomSizeWithNotEqualValues() throws Exception {
		robotRequest = new RobotRequest(new int[] {3, 2}, null, null, null);
		try {
			robotUtils.validateRoomSize(robotRequest.getRoomSize());
		} catch (Exception e) {
			assertTrue(e instanceof HooverException);
			assertEquals(ROOMINCORRECT, e.getMessage());
		}
	}

	@Test
	public void validateNullInitialPosition() throws Exception {
		robotRequest = new RobotRequest(new int[] {5, 5}, null, null, null);
		try {
			robotUtils.validateInitialPosition(robotRequest.getInitialPosition(), robotRequest.getRoomSize());
		} catch (Exception e) {
			assertTrue(e instanceof HooverException);
			assertEquals(INITPOSITIONINCORRECT, e.getMessage());
		}
	}

	@Test
	public void validateCorrectInitialPosition() throws Exception {
		robotRequest = new RobotRequest(new int[] {5, 5}, new int[] {3, 3}, null, null);
		try {
			robotUtils.validateInitialPosition(robotRequest.getInitialPosition(), robotRequest.getRoomSize());
		} catch (Exception e) {
			assertTrue(false);
		}
		assertTrue(true);
	}

	@Test
	public void validateInitialPositionWhenOutOfBounds() throws Exception {
		robotRequest = new RobotRequest(new int[] {5, 5}, new int[] {7, 5}, null, null);
		try {
			robotUtils.validateInitialPosition(robotRequest.getInitialPosition(), robotRequest.getRoomSize());
		} catch (Exception e) {
			assertTrue(e instanceof HooverException);
			assertEquals(INITPOSITIONINCORRECT, e.getMessage());
		}
	}

	@Test
	public void validateWithNullInstructions() throws Exception {
		robotRequest = new RobotRequest(new int[] {5, 5}, new int[] {3, 3}, null, null);
		try {
			robotUtils.validateInstructionsList(robotRequest.getInstructions());
		} catch (Exception e) {
			assertTrue(e instanceof HooverException);
			assertEquals(INSTRUCTIONSNULL, e.getMessage());
		}
	}

	@Test
	public void validateCorrectInstructions() throws Exception {
		robotRequest = new RobotRequest(new int[] {5, 5}, new int[] {3, 3}, null, "NNESWWNWSN");
		try {
			robotUtils.validateInstructionsList(robotRequest.getInstructions());
		} catch (Exception e) {
			assertTrue(false);
		}
		assertTrue(true);
	}

	@Test
	public void validateInstructionsWithIncorrectCharacters() throws Exception {
		robotRequest = new RobotRequest(new int[] {5, 5}, new int[] {3, 3}, null, "NSTSWRJMNSHU");
		try {
			robotUtils.validateInstructionsList(robotRequest.getInstructions());
		} catch (Exception e) {
			assertTrue(e instanceof HooverException);
			assertEquals(INSTRUCTIONSINCORRECT, e.getMessage());
		}
	}

	@Test
	public void validateInstructionsWithNumbers() throws Exception {
		robotRequest = new RobotRequest(new int[] {5, 5}, new int[] {3, 3}, null, "N13SU8");
		try {
			robotUtils.validateInstructionsList(robotRequest.getInstructions());
		} catch (Exception e) {
			assertTrue(e instanceof HooverException);
			assertEquals(INSTRUCTIONSINCORRECT, e.getMessage());
		}
	}

	@Test
	public void validateInstructionsWithSpaces() throws Exception {
		robotRequest = new RobotRequest(null, null, null, "NS SSW NEWS EWN");
		try {
			robotUtils.validateInstructionsList(robotRequest.getInstructions());
		} catch (Exception e) {
			assertTrue(false);
		}
		assertTrue(true);
	}

	@Test
	public void validateInstructionsWithSpecialCharacters() throws Exception {
		robotRequest = new RobotRequest(null, null, null, "NSN+(");
		try {
			robotUtils.validateInstructionsList(robotRequest.getInstructions());
		} catch (Exception e) {
			assertTrue(e instanceof HooverException);
			assertEquals(INSTRUCTIONSINCORRECT, e.getMessage());
		}
	}

}
