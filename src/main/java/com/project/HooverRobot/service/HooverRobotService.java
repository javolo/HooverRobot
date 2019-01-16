package com.project.HooverRobot.service;

import java.awt.Point;
import java.util.List;
import java.util.Set;

import com.project.HooverRobot.exception.HooverException;
import com.project.HooverRobot.model.HooverRobot;
import com.project.HooverRobot.model.RobotRequest;
import com.project.HooverRobot.model.RobotResponse;
import com.project.HooverRobot.model.Room;
import com.project.HooverRobot.utils.HooverUtils;

public class HooverRobotService {
	
	public RobotResponse cleanRoom(RobotRequest hooverRobotRequest) throws HooverException {
		
        if(hooverRobotRequest != null) {
        	
            // Validate the different inputs of the request
        	HooverUtils robotUtils = new HooverUtils();
        	robotUtils.validateRoomSize(hooverRobotRequest.getRoomSize());
        	robotUtils.validateInitialPosition(hooverRobotRequest.getInitialPosition(), hooverRobotRequest.getRoomSize());
        	Set<Point> dirtPatchSet = robotUtils.validatePatchesInput(hooverRobotRequest.getPatches(), hooverRobotRequest.getRoomSize());
        	List<Character> instructionsList = robotUtils.validateInstructionsList(hooverRobotRequest.getInstructions());

        	// If it gets to this point means that all the validation went ok.
            // Room size initialisation
        	Point roomSize  = new Point(hooverRobotRequest.getRoomSize()[0], hooverRobotRequest.getRoomSize()[1]);
        	// Room initialisation
            Room room = new Room(roomSize, dirtPatchSet);
            // Initial Position initialisation
            Point initialPosition = new Point(hooverRobotRequest.getInitialPosition()[0], hooverRobotRequest.getInitialPosition()[1]);
            
            // Robot Hoover object to run the instructions passed as parameter
            HooverRobot hoover = new HooverRobot(initialPosition, room);
            // Execute clean instructions
            hoover.executeInstructionList(instructionsList);
            
            // We create the object to display the results
            final Point finalHooverPosition = hoover.hooverPosition();
            return new RobotResponse(new int[] {finalHooverPosition.x, finalHooverPosition.y}, room.getPatchRemovalCount());
        }
        return new RobotResponse(null, 0);
    }

}
