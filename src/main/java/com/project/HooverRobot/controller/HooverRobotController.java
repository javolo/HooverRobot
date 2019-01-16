package com.project.HooverRobot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.HooverRobot.model.RobotRequest;
import com.project.HooverRobot.model.RobotResponse;
import com.project.HooverRobot.service.HooverRobotService;

@RestController
@RequestMapping("/hooverRobot")
public class HooverRobotController {

	private final Logger LOG = LoggerFactory.getLogger(HooverRobotController.class);

	private HooverRobotService robotHooverService;

	@RequestMapping(value = "/cleanRoom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public HttpEntity<RobotResponse> cleanRoomWithHooverRobot(@RequestBody RobotRequest robotHooverRequest) {
		LOG.info("Call to clean the room...");
		RobotResponse hooverControllerResponse;
		try {
			hooverControllerResponse = robotHooverService.cleanRoom(robotHooverRequest);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			hooverControllerResponse = new RobotResponse(null, 0);
			return new ResponseEntity<>(hooverControllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(hooverControllerResponse, HttpStatus.OK);
	}
}
