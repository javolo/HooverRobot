package com.project.HooverRobot.utils;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.HooverRobot.exception.HooverException;
import com.project.HooverRobot.model.RobotConstants;

public class HooverUtils {

	private final Logger LOG = LoggerFactory.getLogger(HooverUtils.class);

	public void validateRoomSize(int[] roomSize) throws HooverException {
		
		// Room Size is not null.
		// Room Size has two elements.
		// Both elements of room size array has to be equals.
		// Both elements of room size array has to be greater than 0, if not we won´t have a room.
		if(roomSize == null || roomSize.length != 2 || roomSize[0] != roomSize[1] || roomSize[0] < 1 || roomSize[1] < 1) {
			throw new HooverException(RobotConstants.ROOMINCORRECT);
		}
		LOG.debug("Validating room size: [" + roomSize[0] + ", " + roomSize[1] + "]");
		LOG.debug("Room size input is correct!");
	}

	public void validateInitialPosition(int[] initPosition, int[] roomSize) throws HooverException {
		
		// Initial position is not null.
		// Initial position has two elements.
		// Both elements of room size array has to be less than room size.
		// Both elements of room size array has to be greater than 0.
		if(initPosition == null|| initPosition.length != 2 || initPosition[0] > roomSize[0] || initPosition[1] > roomSize[1] || initPosition[0] < 0 || initPosition[1] < 0) {
			throw new HooverException(RobotConstants.INITPOSITIONINCORRECT);
		}
		LOG.debug("Validating Initial Position: [" + initPosition[0] + ", " + initPosition[1] + "]");
		LOG.debug("Initial position input is correct!");
	}

	// Filter the list of dirt patches that fall under the room size coordinates
	public Set<Point> validatePatchesInput(int[][] patches, int[] roomSize) throws HooverException {
		LOG.debug("Validating input Patches");
		if(patches != null) {
			int currentX = 0;
			int currentY = 0;
			Set<Point> patchesSet = new HashSet<>();
			for(int i=0; i<patches.length; i++) {
				if(patches[i].length==2) {
					currentX = patches[i][0];
					currentY = patches[i][1];
					// Checking if the dirt patch coordinates fall under the room size coordinates
					if(currentX < roomSize[0] && currentY < roomSize[1]) {
						patchesSet.add(new Point(currentX, currentY));
					}
				}
			}
			LOG.debug("Patches set input validation passed!");
			return patchesSet;
		}
		throw new HooverException("The list of dirt patches is incorrect or not valid. Please enter a valid list of points.");
	}

	// Function that validates if the list of instructions passed as parameter are correct
	public List<Character> validateInstructionsList(String instructions) throws HooverException {
		LOG.debug("Validating instruction list: " + instructions);
		if(instructions != null) {
			List<Character> list;
			// We replace all special characters, blank spaces with empty string.
			instructions = instructions.replaceAll("\\s+","");
			// The pattern is any N, S, E, W and any number of occurrences of them.
			Pattern pt = Pattern.compile("[^NESW]+");
			Matcher match = pt.matcher(instructions);
			if(!match.matches()) {
				list = new LinkedList<>();
				final char[] chars = instructions.toUpperCase().toCharArray();
				for(int i=0; i<chars.length;i++) {
					list.add(chars[i]);
				}
			} else {
				throw new HooverException(RobotConstants.INSTRUCTIONSINCORRECT);
			}
			LOG.debug("Instruction list is correct!");
			return list;
		} else {
			throw new HooverException(RobotConstants.INSTRUCTIONSNULL);
		}
	}

}
