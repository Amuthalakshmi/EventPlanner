package com.anz.eventplanner.controller;

import java.util.List;

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
import com.anz.eventplanner.model.EventOrganiser;
import com.anz.eventplanner.service.EventOrganiserService;

@Controller
public class AdminControllerForEventOrganiser {

	@Autowired
	EventOrganiserService eventOrganiserService;	
	
	@Autowired
	EventController eventController;

	@Autowired
	MessageSource messageSource;

	/**
	 * This method lists all Event Organisers
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/listEventOrganisers" }, method = RequestMethod.GET)
	public String listAllEventOrganisers(ModelMap model) {
		List<EventOrganiser> eventOrganisers = eventOrganiserService.findAllEventOrganiser();
		model.addAttribute("eventOrganisers", eventOrganisers);
		return "listEventOrganisers";
	}

	/**
	 * This method provide the form to add a new Event Organiser
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/newEventOrganiser" }, method = RequestMethod.GET)
	public String newEventOrganiser(ModelMap model) {
		EventOrganiser eventOrganiser = new EventOrganiser();		
		List<Event> events = eventController.eventService.findAllEventByStatus("Initiated");		
		model.addAttribute("eventOrganiser", eventOrganiser);
		if (events != null){
			model.addAttribute("events", events);	
		}		
		model.addAttribute("edit", false);
		return "addEventOrganiser";
	}

	/**
	 * This method will be called on New Event Organiser addition form
	 * submission, handling POST request for saving event organisers details in
	 * database. TO DO: validate user Input
	 * 
	 * @param eventOrganiser
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = { "/newEventOrganiser" }, method = RequestMethod.POST)
	public String saveEventOrganiser(@Valid EventOrganiser eventOrganiser, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "addEventOrganiser";
		}
		eventOrganiserService.saveEventOrganiser(eventOrganiser);
		redirectAttributes.addFlashAttribute("success",
				eventOrganiser.getOrganiserName() + " added as event organiser");
		return "redirect:/listEventOrganisers";
	}

	/**
	 * This method will provide a medium to update existing Event Organiser
	 * 
	 * @param eventOrganiserId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/edit-{eventOrganiserId}-eventOrganiser" }, method = RequestMethod.GET)
	public String editEventOrganiser(@PathVariable(value = "eventOrganiserId") int eventOrganiserId, ModelMap model) {
		EventOrganiser eventOrganiser = eventOrganiserService.findById(eventOrganiserId);
		model.addAttribute("eventOrganiser", eventOrganiser);
		model.addAttribute("edit", true);
		return "addEventOrganiser";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating event in database. To do - validation
	 * 
	 * @param eventOrganiser
	 * @param result
	 * @param model
	 * @param eventOrganiserId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = { "/edit-{eventOrganiserId}-eventOrganiser" }, method = RequestMethod.POST)
	public String updateEventOrganiser(@Valid EventOrganiser eventOrganiser, BindingResult result, ModelMap model,
			@PathVariable(value = "eventOrganiserId") int eventOrganiserId, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "addEventOrganiser";
		}

		eventOrganiserService.updateEventOrganiser(eventOrganiser);

		redirectAttributes.addFlashAttribute("success", eventOrganiser.getOrganiserName() + " updated successfully");
		return "redirect:/listEventOrganisers";
	}

	@RequestMapping(value = { "/delete-{eventOrganiserId}-eventOrganiser" }, method = RequestMethod.GET)
	public String deleteEventManager(@PathVariable(value = "eventOrganiserId") int eventOrganiserId,
			RedirectAttributes redirectAttributes) {
		EventOrganiser eventOrganiser = eventOrganiserService.findById(eventOrganiserId);
		eventOrganiserService.deleteEventOrganiserById(eventOrganiserId);
		redirectAttributes.addFlashAttribute("success", eventOrganiser.getOrganiserName() + "  removed");
		return "redirect:/listEventOrganisers";
	}

}
