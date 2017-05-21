package com.anz.eventplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anz.eventplanner.model.Event;
import com.anz.eventplanner.model.EventOrganiser;
import com.anz.eventplanner.service.EventOrganiserService;
import com.anz.eventplanner.service.TaskService;

@Controller
public class EventOrganiserController {

	@Autowired
	EventOrganiserService eventOrganiserService;
	
	@Autowired
	TaskService taskService;	
	

	@Autowired
	EventController eventController;

	/**
	 * Welcome page for Event Organisers
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/organiser{eventOrganiserId}" }, method = RequestMethod.GET)
	public String eventOrganiser(@PathVariable(value = "eventOrganiserId") int eventOrganiserId, ModelMap model) {
		EventOrganiser eventOrganiser = eventOrganiserService.findById(eventOrganiserId);
		model.addAttribute("eventOrganiser", eventOrganiser);
		return "eventOrganiser";
	}
	
	@RequestMapping(value={"/organiser{eventOrganiserId}/plan/event{eventId}"},method = RequestMethod.GET)
	public String planEvent(@PathVariable(value = "eventId") int eventId, @PathVariable(value = "eventOrganiserId") int eventOrganiserId, ModelMap model){
		Event event = eventController.eventService.findById(eventId);		
		model.addAttribute("event",event);		 
		
		return "planForEvent";
	}

}
