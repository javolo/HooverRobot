package com.project.HooverRobot.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RobotBaseClass {

	private final int[] initialPosition;

	protected RobotBaseClass(@JsonProperty("initialPosition") int[] initPosition) {
		this.initialPosition = initPosition;
	}

	public int[] getCoords() {
		return initialPosition;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RobotBaseClass)) return false;

        RobotBaseClass that = (RobotBaseClass) o;

        return Arrays.equals(initialPosition, that.initialPosition);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(initialPosition);
    }

    @Override
    public String toString() {
        return "RoboHooverBase{" + "coords=" + Arrays.toString(initialPosition) + '}';
    }

}
