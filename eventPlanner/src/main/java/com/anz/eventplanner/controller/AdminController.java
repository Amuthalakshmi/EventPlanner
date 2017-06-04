package com.anz.eventplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anz.eventplanner.model.EventManager;
import com.anz.eventplanner.model.EventOrganiser;
import com.anz.eventplanner.model.Participant;
import com.anz.eventplanner.service.EventManagerService;
import com.anz.eventplanner.service.EventOrganiserService;
import com.anz.eventplanner.service.ParticipantService;

@Controller
public class AdminController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserController userController;
	
	@Autowired
	EventManagerService eventManagerService;
	
	@Autowired
	EventOrganiserService eventOrganiserService;

	@Autowired
	ParticipantService participantService;
	
	/**
	 * Welcome page for Admin
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String admin(ModelMap model) {

		String LANId = userController.user.getLANId();

		model.addAttribute("isEventManager", userController.isEventManager(LANId));
		if (userController.isEventOrganiser(LANId)) {
			EventOrganiser eventOrganiser = eventOrganiserService.findByLANId(LANId);
			model.addAttribute("isEventOrganiser", userController.isEventOrganiser(LANId));
			model.addAttribute("eventOrganiserId", eventOrganiser.getEventOrganiserId());
		}
		List<Participant> participation = participantService.findAllParticipantByLANId(LANId);
		model.addAttribute("participation", participation);
		
		List<EventManager> eventManagers = eventManagerService.findAllEventManager();		
		model.addAttribute("eventManagers", eventManagers);
		
		List<EventOrganiser> eventOrganisers = eventOrganiserService.findAllEventOrganiser();
		model.addAttribute("eventOrganisers", eventOrganisers);

		return "admin";
	}

}
