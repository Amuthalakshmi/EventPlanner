package com.anz.eventplanner.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anz.eventplanner.model.Event;
import com.anz.eventplanner.model.EventManager;
import com.anz.eventplanner.service.EventManagerService;
import com.anz.eventplanner.service.EventService;

@Controller
@RequestMapping("/")
public class EventPlannerController {

	@Autowired
	EventService eventService;

	@Autowired
	EventManagerService eventManagerService;

	@Autowired
	MessageSource messageSource;

	/**
	 * Welcome page for Admin
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/", "/admin" }, method = RequestMethod.GET)
	public String admin(ModelMap model) {
		return "admin";
	}

	/**
	 * Welcome page for Event Managers
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/manager" }, method = RequestMethod.GET)
	public String eventManager(ModelMap model) {
		return "eventManager";
	}

	/**
	 * This method lists all Event Managers
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/listEventManagers" }, method = RequestMethod.GET)
	public String listAllEventManagers(ModelMap model) {
		List<EventManager> eventManagers = eventManagerService.findAllEventManager();
		model.addAttribute("eventManagers", eventManagers);
		return "listEventManagers";
	}

	/**
	 * This method provide the form to add a new Event Manager
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/newEventManager" }, method = RequestMethod.GET)
	public String newEventManager(ModelMap model) {
		EventManager eventManager = new EventManager();
		model.addAttribute("eventManager", eventManager);
		model.addAttribute("edit", false);
		return "addEventManager";
	}

	/**
	 * This method will be called on New Event Manager addition form submission,
	 * handling POST request for saving event managers details in database. TO
	 * DO: validate user Input
	 * 
	 * @param eventManager
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/newEventManager" }, method = RequestMethod.POST)
	public String saveEventManager(@Valid EventManager eventManager, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "addEventManager";
		}
		eventManagerService.saveEventManager(eventManager);		
		redirectAttributes.addFlashAttribute("success", eventManager.getUserName() + " added as event manager");
		return "redirect:/listEventManagers";
	}

	/**
	 * This method will provide a medium to update existing Event Managers
	 * 
	 * @param employeeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/edit-{eventManagerId}-eventManager" }, method = RequestMethod.GET)
	public String editEventManager(@PathVariable(value = "eventManagerId") int eventManagerId, ModelMap model) {
		EventManager eventManager = eventManagerService.findById(eventManagerId);
		model.addAttribute("eventManager", eventManager);
		model.addAttribute("edit", true);
		return "addEventManager";
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
	@RequestMapping(value = { "/edit-{empRefId}-eventManager" }, method = RequestMethod.POST)
	public String updateEventManager(@Valid EventManager eventManager, BindingResult result, ModelMap model,
			@PathVariable(value = "empRefId") int empRefId, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "addEventManager";
		}

		eventManagerService.updateEventManager(eventManager);
		
		redirectAttributes.addFlashAttribute("success", eventManager.getUserName() + " updated successfully");
		return "redirect:/listEventManagers";
		
	}

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
	public String saveEvent(@Valid Event event, BindingResult result, ModelMap model,RedirectAttributes redirectAttributes) {
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
	@RequestMapping(value = { "/edit-{eventId}-event" }, method = RequestMethod.GET)
	public String editEvent(@PathVariable int eventId, ModelMap model) {
		Event event = eventService.findById(eventId);
		model.addAttribute("event", event);

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
	@RequestMapping(value = { "/edit-{eventId}-event" }, method = RequestMethod.POST)
	public String updateEvent(@Valid Event event, BindingResult result, ModelMap model, @PathVariable int eventId,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "addEvent";
		}

		eventService.updateEvent(event);
		
		redirectAttributes.addFlashAttribute("success",
				"Event: " + event.getEventName() + ", Location:" + event.getEventLocation() + " updated successfully");
		return "redirect:/listEvents";

	}

}
