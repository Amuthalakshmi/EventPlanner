package com.anz.eventplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anz.eventplanner.model.Event;
import com.anz.eventplanner.model.EventOrganiser;
import com.anz.eventplanner.model.Participant;
import com.anz.eventplanner.service.ParticipantService;

@Controller
public class EventManagerController {		
	
	@Autowired
	UserController userController;
	
	@Autowired
	EventController eventController;

	@Autowired
	EventOrganiserController eventOrganiserController;
	
	@Autowired
	ParticipantService participantService;
	
	/**
	 * Welcome page for Event Managers
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/manager" }, method = RequestMethod.GET)
	public String eventManager(ModelMap model) {
		String LANId = userController.user.getLANId();
		
		model.addAttribute("isEventManager",userController.isEventManager(LANId));
		if (userController.isEventOrganiser(LANId)) {
			EventOrganiser eventOrganiser = eventOrganiserController.eventOrganiserService.findByLANId(LANId);
			model.addAttribute("isEventOrganiser", userController.isEventOrganiser(LANId));
			model.addAttribute("eventOrganiserId", eventOrganiser.getEventOrganiserId());
		}
		List<Participant> participation = participantService.findAllParticipantByLANId(LANId);
		model.addAttribute("participation", participation);
		
		List<Event> events = eventController.eventService.findAllEvent();
		
		model.addAttribute("events",events);
		
		return "eventManager";
	}
	
}
