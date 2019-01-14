package com.project.HooverRobot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RobotRequest extends RobotBaseClass {

	private final int[] roomSize;
    private final int[][] dirtPatches;
    private final String instructions;

    public RobotRequest(
            @JsonProperty("roomSize") int[] roomSize,
            @JsonProperty("initialPosition") int[] coords,
            @JsonProperty("dirtPatches") int[][] patches,
            @JsonProperty("instructions") String instructions) {
        super(coords);
        this.roomSize = roomSize;
        this.dirtPatches = patches;
        this.instructions = instructions;
    }

    public int[] getRoomSize() {
        return roomSize;
    }

    public int[][] getPatches() {
        return dirtPatches;
    }

    public String getInstructions() {
        return instructions;
    }
}
