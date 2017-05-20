package com.anz.eventplanner.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anz.eventplanner.model.Event;
import com.anz.eventplanner.model.EventOrganiser;
import com.anz.eventplanner.model.OrganiserAndEvent;
import com.anz.eventplanner.service.EventService;

@Controller
public class EventController {

	@Autowired
	EventService eventService;

	@Autowired
	EventOrganiserController eventOrganiserController;

	@Autowired
	MessageSource messageSource;

	/**
	 * This method lists all events
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/listEvents" }, method = RequestMethod.GET)
	public String listAllEvents(ModelMap model) {
		List<Event> events = eventService.findAllEvent();
		model.addAttribute("events", events);
		return "listEvents";
	}

	/**
	 * This method provide the form to add a new Event
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/newEvent" }, method = RequestMethod.GET)
	public String newEvent(ModelMap model) {
		Event event = new Event();

		Map<String, String> locations = new HashMap<String, String>();
		locations.put("WLG", "Wellington");
		locations.put("AUK", "Auckland");

		model.put("locations", locations);
		model.addAttribute("event", event);
		model.addAttribute("edit", false);

		return "addEvent";
	}

	/**
	 * This method will be called on Event Set Up form submission, handling POST
	 * request for saving event details in database. TO DO: validate user Input
	 * 
	 * @param event
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/newEvent" }, method = RequestMethod.POST)
	public String saveEvent(@Valid Event event, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "addEvent";
		}
		eventService.saveEvent(event);
		redirectAttributes.addFlashAttribute("success",
				"Event: " + event.getEventName() + "(" + event.getEventPlannedDate() + ") saved successfully");
		return "redirect:/listEvents";

	}

	/***
	 * This method will provide the medium to update an existing event.
	 * 
	 * @param eventId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/event-{eventId}" }, method = RequestMethod.GET)
	public String editEvent(@PathVariable int eventId, ModelMap model) {
		Event event = eventService.findById(eventId);
		model.addAttribute("event", event);

		if (event.getEventStatus() == null) {
			model.addAttribute("canDelete", true);
			model.addAttribute("canChangeStatus", true);
		}
		if (event.getEventStatus() != null && event.getEventStatus().equalsIgnoreCase("Initiated")) {
			model.addAttribute("canChangeStatus", true);
		}

		Map<String, String> locations = new HashMap<String, String>();
		locations.put("WLG", "Wellington");
		locations.put("AUK", "Auckland");

		model.put("locations", locations);

		List<EventOrganiser> eventOrganisers = eventOrganiserController.eventOrganiserService
				.findAllOrganisersByCategoryAndLocation("All Event", event.getEventLocation());
		List<OrganiserAndEvent> organiserAndEvents = eventOrganiserController.organiserAndEventService
				.findAllOrganiserAndEventByEventId(eventId);
		for (int i = 0; i < organiserAndEvents.size(); i++) {
			int eventOrganiserId = organiserAndEvents.get(i).getEventOrganiserId();
			EventOrganiser eventOrganiser = eventOrganiserController.eventOrganiserService.findById(eventOrganiserId);
			eventOrganisers.add(eventOrganiser);
		}

		model.addAttribute("eventOrganisers", eventOrganisers);

		model.addAttribute("edit", true);
		return "addEvent";
	}

	/***
	 * This method will be called on form submission, handling POST request for
	 * updating event in database. To do - validation
	 * 
	 * @param event
	 * @param result
	 * @param model
	 * @param eventId
	 * @return
	 */
	@RequestMapping(value = { "/event-{eventId}" }, method = RequestMethod.POST)
	public String updateEvent(@Valid Event event, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "addEvent";
		}

		eventService.updateEvent(event);

		return "redirect://event-{eventId}";
	}

	/***
	 * This method will be called on form submission, handling POST request for
	 * updating eventStatus in database. To do - validation
	 * 
	 * @param event
	 * @param result
	 * @param model
	 * @param eventId
	 * @return
	 */
	@RequestMapping(value = { "/event-{eventId}" }, method = RequestMethod.POST, params = "eventStatus")
	public String updateEventStatus(@Valid Event event, BindingResult result, ModelMap model, @PathVariable int eventId,
			@RequestParam(value = "eventStatus", required = false) String eventStatus) {

		if (result.hasErrors()) {
			return "addEvent";
		}

		event = eventService.findById(eventId);

		if (eventStatus.equals("start")) {
			event.setEventStatus("Initiated");
		} else if (eventStatus.equals("finish")) {
			event.setEventStatus("Completed");
		}
		if (eventStatus.equals("drop")) {
			event.setEventStatus("Cancelled");
		}

		eventService.updateEventStatus(event);
		return "redirect://event-{eventId}";
	}

	@RequestMapping(value = { "/delete-{eventId}-event" }, method = RequestMethod.GET)
	public String deleteEvent(@PathVariable int eventId) {
		eventService.deleteEventById(eventId);
		return "redirect:/listEvents";
	}

	@RequestMapping(value = { "/event{eventId}/addOrganisers" }, method = RequestMethod.GET)
	public String toAddEventOrganiser(@PathVariable(value = "eventId") int eventId, ModelMap model) {
		Event event = eventService.findById(eventId);
		model.addAttribute("event", event);
		
		List<EventOrganiser> allEventSpecificOrganisers = eventOrganiserController.eventOrganiserService
				.findAllOrganisersByCategoryAndLocation("Event Specific", event.getEventLocation());

		List<OrganiserAndEvent> addedEventSpecificOrganisers = eventOrganiserController.organiserAndEventService
				.findAllOrganiserAndEventByEventId(eventId);
		
		List<EventOrganiser> eventSpecificOrganisersToAdd = new ArrayList<EventOrganiser>();		
		
		for(int i=0; i<allEventSpecificOrganisers.size(); i++){
			boolean addedEventOrganiser = false;
			for(int j=0; j<addedEventSpecificOrganisers.size(); j++){
				if(allEventSpecificOrganisers.get(i).getEventOrganiserId() == addedEventSpecificOrganisers.get(j).getEventOrganiserId()){
					addedEventOrganiser = true;
				}
			}
			if (addedEventOrganiser == false){
				eventSpecificOrganisersToAdd.add(allEventSpecificOrganisers.get(i));
			}
		}		

		model.addAttribute("eventSpecificOrganisers", eventSpecificOrganisersToAdd);		
		return "addOrganisersToEvent";
	}

	@RequestMapping(value = { "/event{eventId}/add{eventOrganiserId}" }, method = RequestMethod.GET)
	public String addEventOrganiser(@PathVariable(value = "eventId") int eventId,
			@PathVariable(value = "eventOrganiserId") int eventOrganiserId, ModelMap model) {
		OrganiserAndEvent organiserAndEvent = new OrganiserAndEvent();
		organiserAndEvent.setEventId(eventId);
		organiserAndEvent.setEventOrganiserId(eventOrganiserId);
		eventOrganiserController.organiserAndEventService.saveOrganiserAndEvent(organiserAndEvent);
		return "redirect:/event{eventId}/addOrganisers";
	}

}
