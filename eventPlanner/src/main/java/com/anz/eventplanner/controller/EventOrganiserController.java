package com.anz.eventplanner.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anz.eventplanner.model.Event;
import com.anz.eventplanner.model.EventOrganiser;
import com.anz.eventplanner.model.EventSpecificOrganiser;
import com.anz.eventplanner.service.EventOrganiserService;
import com.anz.eventplanner.service.EventSpecificOrganiserService;

@Controller
public class EventOrganiserController {

	@Autowired
	EventOrganiserService eventOrganiserService;

	EventSpecificOrganiserService eventSpecificOrganiserService;

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

		List<Event> eventsSpecificToOrganiser = null;

		if (eventOrganiser.getCategory() != null) {
			if (eventOrganiser.getCategory().equalsIgnoreCase("All Event")) {
				eventsSpecificToOrganiser = eventController.eventService.findAllEventByStatusAndLocation("Initiated", eventOrganiser.getLocation());				
			} else {
				eventsSpecificToOrganiser = new ArrayList<Event>();
				List<EventSpecificOrganiser> eventSpecificOrganisers = eventSpecificOrganiserService
						.findAllEventsSpecificToOrganiser(eventOrganiserId);

				for (int i = 0; i < eventSpecificOrganisers.size(); i++) {
					int eventId = eventSpecificOrganisers.get(i).getEventId();
					Event event = eventController.eventService.findById(eventId);
					eventsSpecificToOrganiser.add(event);					
				}
			}
		}		

		model.addAttribute("eventOrganiser", eventOrganiser);
		model.addAttribute("eventsSpecificToOrganiser", eventsSpecificToOrganiser);
		return "eventOrganiser";
	}

}
