package com.project.HooverRobot.model;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RoomTest {

	private Room room;
	private HooverRobot robot;
	private List<Character> instructionsList;
	private Set<Point> dirtPatchSet;

	@Before
	public void setUp() throws Exception {

		dirtPatchSet = new HashSet<>(Arrays.asList(new Point(2, 3), new Point(3, 2), new Point(3, 3)));
		room = new Room(new Point(5, 5), dirtPatchSet);
	}

	@After
	public void tearDown() throws Exception {
		room = null;
		robot = null;
		instructionsList = null;
		dirtPatchSet = null;
	}

	@Test
	public void testHooverInitialPosition() {
		robot = new HooverRobot(new Point(5, 5), room);
		assertEquals(new Point(5, 5), this.room.getHooverPosition());
	}

	@Test
	public void testMoveNorthWithNotValidValue() throws Exception {
		robot = new HooverRobot(new Point(3, 5), room);
		Point movedPosition = robot.moveNorth();
		assertEquals(new Point(3, 5), movedPosition);
	}

	@Test
	public void testMoveNorthWithValidValue() throws Exception {
		robot = new HooverRobot(new Point(3, 3), room);
		Point movedPosition = robot.moveNorth();
		assertEquals(new Point(3, 4), movedPosition);
	}

	@Test
	public void testMoveEastWithNotValidValue() throws Exception {
		robot = new HooverRobot(new Point(5, 5), room);
		Point movedPosition = robot.moveEast();
		assertEquals(new Point(5, 5), movedPosition);
	}

	@Test
	public void testMoveEastWithValidValue() throws Exception {
		robot = new HooverRobot(new Point(3, 3), room);
		Point movedPosition = robot.moveEast();
		assertEquals(new Point(4, 3), movedPosition);
	}

	@Test
	public void testMoveSouthWithNotValidValue() throws Exception {
		robot = new HooverRobot(new Point(3, 0), room);
		Point movedPosition = robot.moveSouth();
		assertEquals(new Point(3, 0), movedPosition);
	}

	@Test
	public void testMoveSouthWithValidValue() throws Exception {
		robot = new HooverRobot(new Point(3, 3), room);
		Point movedPosition = robot.moveSouth();
		assertEquals(new Point(3, 2), movedPosition);
	}

	@Test
	public void testMoveWestWithNotValidValue() throws Exception {
		robot = new HooverRobot(new Point(0, 3), room);
		Point movedPosition = robot.moveWest();
		assertEquals(new Point(0, 3), movedPosition);
	}

	@Test
	public void testMoveWestWithValidValue() throws Exception {
		robot = new HooverRobot(new Point(3, 3), room);
		Point movedPosition = robot.moveWest();
		assertEquals(new Point(2, 3), movedPosition);
	}

	@Test
	public void testHooverRobotWithSomeInvalidMovesAndNoDirtPatches() {
		robot = new HooverRobot(new Point(5, 5), room);
		assertEquals(3, room.getDirtPatchesSet().size());
		instructionsList = new LinkedList<>(Arrays.asList('N', 'N', 'S', 'S', 'W', 'N'));
		robot.executeInstructionList(instructionsList);
		// Final Position after set of instructions
		assertEquals(new Point(4, 4), this.room.getHooverPosition());
		// Size of the dirt patches set
		assertEquals(3, room.getDirtPatchesSet().size());
		// Number of dirt patches removed
		assertEquals(0, this.room.getPatchRemovalCount());
	}

	@Test
	public void executeCommandsWithSomeInvalidMovesWith2StainsSamePosition() {

		dirtPatchSet = new HashSet<>(Arrays.asList(new Point(1, 0), new Point(2, 2), new Point(2, 3)));
		room = new Room(new Point(5, 5), dirtPatchSet);

		robot = new HooverRobot(new Point(1, 2), room);
		assertEquals(3, room.getDirtPatchesSet().size());
		instructionsList = new LinkedList<>(Arrays.asList('N', 'N', 'E', 'S', 'E', 'E', 'S', 'W', 'N', 'W', 'W'));
		robot.executeInstructionList(instructionsList);
		// Final Position after set of instructions
		assertEquals(new Point(1, 3), this.room.getHooverPosition());
		// Size of the dirt patches set
		assertEquals(2, room.getDirtPatchesSet().size());
		// Number of dirt patches removed
		assertEquals(1, this.room.getPatchRemovalCount());
	}

}
