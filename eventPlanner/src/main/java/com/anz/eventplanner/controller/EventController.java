package com.anz.eventplanner.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

		return "redirect:/event-{eventId}";
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
	public String updateEventStatus(@Valid Event event, BindingResult result, ModelMap model,
			@PathVariable(value = "eventId") int eventId,
			@RequestParam(value = "eventStatus", required = false) String eventStatus) {

		if (result.hasErrors()) {
			return "addEvent";
		}

		event = eventService.findById(eventId);

		if (eventStatus.equals("start")) {
			event.setEventStatus("Initiated");
			List<EventOrganiser> organisersAllEventList = eventOrganiserController.eventOrganiserService
					.findAllOrganisersByCategoryAndLocation("All Event", event.getEventLocation());
			for (EventOrganiser eventOrganiser : organisersAllEventList) {
				event.addAssociatedOrganisers(eventOrganiser);
			}
			eventService.updateEvent(event);
		} else if (eventStatus.equals("finish")) {
			event.setEventStatus("Completed");
		}
		if (eventStatus.equals("drop")) {
			event.setEventStatus("Cancelled");
		}

		eventService.updateEventStatus(event);
		return "redirect:/event-{eventId}";
	}

	@RequestMapping(value = { "/delete-{eventId}-event" }, method = RequestMethod.GET)
	public String deleteEvent(@PathVariable int eventId) {
		eventService.deleteEventById(eventId);
		return "redirect:/listEvents";
	}

	@RequestMapping(value = { "/event{eventId}/addOrganisers" }, method = RequestMethod.GET)
	public String toAddEventOrganiser(@PathVariable(value = "eventId") int eventId, ModelMap model) {
		Event event = eventService.findById(eventId);

		List<EventOrganiser> eventSpecificOrganisers = eventOrganiserController.eventOrganiserService
				.findAllOrganisersByCategoryAndLocation("Event Specific", event.getEventLocation());
		Iterator<EventOrganiser> iterator = eventSpecificOrganisers.iterator();

		while (iterator.hasNext()) {
			EventOrganiser eventOrganiser = iterator.next();
			Set<Event> associatedEvents = eventOrganiser.getAssociatedEvents();
			if (associatedEvents != null & !associatedEvents.isEmpty()) {
				for (Event e : associatedEvents) {
					if (e.equals(event)) {
						iterator.remove();
					}
				}
			}
		}

		model.addAttribute("event", event);
		model.addAttribute("eventSpecificOrganisers", eventSpecificOrganisers);
		return "addOrganisersToEvent";
	}

	@RequestMapping(value = { "/event{eventId}/add{eventOrganiserId}" }, method = RequestMethod.GET)
	public String addEventOrganiser(@PathVariable(value = "eventId") int eventId,
			@PathVariable(value = "eventOrganiserId") int eventOrganiserId, ModelMap model) {
		EventOrganiser eventOrganiser = eventOrganiserController.eventOrganiserService.findById(eventOrganiserId);
		Event event = eventService.findById(eventId);
		event.getAssociatedOrganisers().add(eventOrganiser);
		eventService.updateEvent(event);
		return "redirect:/event{eventId}/addOrganisers";
	}

	@RequestMapping(value = { "/event{eventId}/removeOrganiser{eventOrganiserId}" }, method = RequestMethod.GET)
	public String removeEventOrganiser(@PathVariable(value = "eventId") int eventId,
			@PathVariable(value = "eventOrganiserId") int eventOrganiserId, ModelMap model) {
		EventOrganiser eventOrganiser = eventOrganiserController.eventOrganiserService.findById(eventOrganiserId);
		Event event = eventService.findById(eventId);
		Set<EventOrganiser> associatedOrganisers = event.getAssociatedOrganisers();

		Iterator<EventOrganiser> iterator = associatedOrganisers.iterator();
		while (iterator.hasNext()) {
			EventOrganiser eo = iterator.next();
			if (eo.equals(eventOrganiser)) {
				associatedOrganisers.remove(eventOrganiser);
				break;
			}
		}

		eventService.updateEvent(event);
		return "redirect:/event-{eventId}";
	}

}
