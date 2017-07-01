package com.anz.eventplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anz.eventplanner.model.Event;
import com.anz.eventplanner.model.EventManager;
import com.anz.eventplanner.model.EventOrganiser;
import com.anz.eventplanner.model.Participant;
import com.anz.eventplanner.model.User;
import com.anz.eventplanner.service.EventManagerService;
import com.anz.eventplanner.service.EventOrganiserService;
import com.anz.eventplanner.service.EventService;
import com.anz.eventplanner.service.ParticipantService;

@Controller
public class UserController {
	
	@Autowired
	MessageSource messageSource;

	User user = new User();	

	@Autowired
	EventService eventService;

	@Autowired
	EventManagerService eventManagerService;

	@Autowired
	EventOrganiserService eventOrganiserService;

	@Autowired
	ParticipantService participantService;	
	
	public UserController() {		
		user.setLANId("USR3WLG");
	}
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String homepage(ModelMap model) {
		
		String LANId = user.getLANId();			

		model.addAttribute("isEventManager", isEventManager(LANId));
		
		if (isEventOrganiser(LANId)){			
			EventOrganiser eventOrganiser = eventOrganiserService.findByLANId(LANId);
			model.addAttribute("isEventOrganiser", isEventOrganiser(LANId));
			model.addAttribute("eventOrganiserId", eventOrganiser.getEventOrganiserId());
		}		
		List<Participant> participation = participantService.findAllParticipantByLANId(LANId);
		model.addAttribute("participation", participation);
		
		List<Event> events = eventService.findAllEventByStatus("Initiated");
		
		for(Participant p:participation){
			if(events.contains(p.getEvent())){
				events.remove(p.getEvent());
			}
		}
		
		model.addAttribute("events", events);
		return "home";
	}

	public boolean isEventManager(String LANId) {		
		EventManager eventManager = eventManagerService.findByLANId(LANId);
		if (eventManager == null) {
			return false;
		}
		return true;
	}
	
	public boolean isEventOrganiser(String LANId) {		
		EventOrganiser eventOrganiser = eventOrganiserService.findByLANId(LANId);
		if (eventOrganiser == null) {
			return false;
		}
		return true;
	}
}