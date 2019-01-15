package com.project.HooverRobot.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RobotResponse extends RobotBaseClass{

	private final int dirtRemovalCount;

	public RobotResponse(
			@JsonProperty("initialPosition") int[] initPosition,
			@JsonProperty("dirtRemovalCount") int dirtRemovalCount) {
		super(initPosition);
		this.dirtRemovalCount = dirtRemovalCount;
	}

	public int getDirtRemovalCount() {
		return dirtRemovalCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RobotResponse)) return false;
		if (!super.equals(o)) return false;

		RobotResponse that = (RobotResponse) o;

		if (dirtRemovalCount != that.dirtRemovalCount) 
			return false;
		else
			return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + dirtRemovalCount;
		return result;
	}

	@Override
	public String toString() {
		return "RoboHooverResponse{" + "coords=" + Arrays.toString(getInitialPosition()) + "patches=" + dirtRemovalCount + '}';
	}

}
