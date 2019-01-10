package com.project.HooverRobot.model;

import java.awt.Point;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HooverRobot {
	
	private final Logger LOG = LoggerFactory.getLogger(HooverRobot.class);
	
	private final Point initialHooverPosition;
	private final Room roomToClean;
	
	public HooverRobot(Point initPosition, Room roomToClean) {
        this.initialHooverPosition = initPosition;
        this.roomToClean = roomToClean;
        initHooverPosition(initialHooverPosition);
    }
	
	public Point getInitialPosition() {
        return initialHooverPosition;
    }

    public Point hooverPosition() {
        return this.roomToClean.getHooverPosition();
    }

	private void initHooverPosition(Point initPosition) {
        this.roomToClean.initHooverPosition(initPosition);
    }

    private void moveHooverPosition(Point nextPosition) {
        this.roomToClean.moveHooverPosition(nextPosition);
    }
    
    /**
     * 
     * @param instructions to be executed by the Hoover Robot
     */
    public void executeInstructionList(List<Character> instructions) {
    	LOG.info("Executing instructions: " + instructions.toString());
    	instructions.stream().forEach(inst -> {
            switch(Character.toUpperCase(inst)) {
                case 'N':
                	moveHooverPosition(moveNorth());
                    break;
                case 'E':
                	moveHooverPosition(moveEast());
                    break;
                case 'S':
                	moveHooverPosition(moveSouth());
                    break;
                case 'W':
                	moveHooverPosition(moveWest());
                    break;
                default:
                	LOG.error("Instruction not recognised: " + inst);
            }
            
            if(roomToClean.checkPointInDirtPatchSet()) {
                roomToClean.incrementCountAndRemoveStainFromSet();
            }
        });
    }
    
    /**
     * 
     * @return the point of the robot after moving north
     */
    public Point moveNorth() {
        Point currentPosition = hooverPosition();
        int upperRoomEdge = this.roomToClean.getRoomSize().y;
        if(currentPosition.y < upperRoomEdge) {
            return new Point(currentPosition.x, currentPosition.y+1);
        }
        return currentPosition;
    }
    
    /**
     * 
     * @return the point of the robot after moving south
     */
    public Point moveSouth() {
        Point currentPosition = hooverPosition();
        if(currentPosition.y > 0) {
            return new Point(currentPosition.x, currentPosition.y-1);
        }
        return currentPosition;
    }

    /**
     * 
     * @return the point of the robot after moving west
     */
    public Point moveWest() {
        Point currentPosition = hooverPosition();
        if(currentPosition.x > 0) {
            return new Point(currentPosition.x-1, currentPosition.y);
        }
        return currentPosition;
    }

    /**
     * 
     * @return te point of the robot after moving east
     */
    public Point moveEast() {
        Point currentPosition = hooverPosition();
        int easternRoomEdge = this.roomToClean.getRoomSize().x;
        if(currentPosition.x < easternRoomEdge) {
            return new Point(currentPosition.x+1, currentPosition.y);
        }
        return currentPosition;
    }

}
