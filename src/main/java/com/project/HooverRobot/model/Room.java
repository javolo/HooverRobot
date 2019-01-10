package com.project.HooverRobot.model;

import java.awt.Point;
import java.util.Optional;
import java.util.Set;

public class Room {

	private final Point roomSize;
	private final Set<Point> dirtPatchesSet;
	private Optional<Point> hooverPosition;
	private int patchRemovalCount = 0;

	public Room(Point roomSizeIn, Set<Point> dirtPatchesSetIn) {
		this.roomSize = roomSizeIn;
		this.dirtPatchesSet = dirtPatchesSetIn;
		this.hooverPosition = Optional.empty();
	}

	public void initHooverPosition(Point initialPosition) {
		hooverPosition = Optional.of(initialPosition);
	}

	public void moveHooverPosition(Point newPosition) {
		hooverPosition = Optional.of(newPosition);
	}


	public void incrementCountAndRemoveStainFromSet() {
		patchRemovalCount += 1;
		dirtPatchesSet.remove(hooverPosition.get());
	}

	/**
	 * 
	 * @return the roomSize
	 */
	public Point getRoomSize() {
		return roomSize;
	}

	/**
	 * @return the dirtPatchesSet
	 */
	public Set<Point> getDirtPatchesSet() {
		return dirtPatchesSet;
	}
	
	/**
	 * 
	 * @return the initial hoover position in the room
	 */
	public Point getHooverPosition() {
        return hooverPosition.orElseThrow(IllegalStateException::new);
    }

	/**
	 * 
	 * @return the number of patches cleaned
	 */
	public int getPatchRemovalCount() {
		return patchRemovalCount;
	}
	
	/**
	 * 
	 * @return true if the point covered has a dirt patch
	 */
	public boolean checkPointInDirtPatchSet() {

      return dirtPatchesSet.stream().filter(stain -> stain.equals(hooverPosition.get())).findFirst().isPresent();
  }

}
