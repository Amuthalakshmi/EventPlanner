package com.anz.eventplanner.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anz.eventplanner.model.Child;
import com.anz.eventplanner.model.Event;
import com.anz.eventplanner.model.Participant;
import com.anz.eventplanner.service.ChildService;
import com.anz.eventplanner.service.ParticipantService;

@Controller
@RequestMapping("/participant")
public class ParticipantController {
	@Autowired
	EventController eventController;

	@Autowired
	ChildService childService;

	@Autowired
	ParticipantService participantService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/event{eventId}/register" }, method = RequestMethod.GET)
	public String registration(@PathVariable(value = "eventId") int eventId, ModelMap model) {

		Event event = eventController.eventService.findById(eventId);

		if (event != null) {
			model.addAttribute("event", event);

			switch (event.getEventStatus()) {
			case "Initiated":
				model.addAttribute("showForm", true);

				Participant participant = new Participant();
				Child child = new Child();
				participant.getChildren().add(child);
				model.addAttribute("participant", participant);

				break;

			case "Completed":
				model.addAttribute("showForm", false);
				model.addAttribute("errormsg", "Event - Completed");
				break;

			case "Cancelled":
				model.addAttribute("showForm", false);
				model.addAttribute("errormsg", "Event - Cancelled");
				break;

			default:
				model.addAttribute("showForm", false);
				break;
			}
		} else {
			model.addAttribute("showForm", false);
			model.addAttribute("errormsg", "Not a Valid Event");
		}

		model.addAttribute("edit", false);
		return "registrationBringKidsToWork";
	}

	@RequestMapping(value = { "/event{eventId}/register" }, method = RequestMethod.POST)
	public String saveRegistration(@PathVariable(value = "eventId") int eventId, @Valid Participant participant,
			BindingResult participantResult, ModelMap model) {

		if (participantResult.hasErrors()) {
			return "registrationBringKidsToWork";
		}
		
		participant.setEvent(eventController.eventService.findById(eventId));

		for (Child child : participant.getChildren()) {
			child.setParent(participant);
		}

		participantService.saveParticipant(participant);

		return "registrationBringKidsToWork";
	}

	@RequestMapping(value = { "/list-registration" }, method = RequestMethod.GET)
	public String listevents() {
		return null;
	}

	/**
	 * This method will provide a medium to update existing registration 
	 * @param participantId
	 * @param eventId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/{participantId}" }, method = RequestMethod.GET)
	public String editRegistration(@PathVariable(value = "participantId") int participantId, ModelMap model) {
		Participant participant = participantService.findById(participantId);
		model.addAttribute("participant", participant);

		model.addAttribute("showForm", true);
		model.addAttribute("edit", true);
		return "registrationBringKidsToWork";
	}

	/**
	 * 
	 * @param participantId
	 * @param eventId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/{participantId}" }, method = RequestMethod.POST)
	public String updateRegistration(@PathVariable(value = "participantId") int participantId, @ModelAttribute Participant participant,
			BindingResult participantResult, ModelMap model) {

		if (participantResult.hasErrors()) {
			return "registrationBringKidsToWork";
		}

		participantService.updateParticipant(participant);

		return "redirect:/participant/{participantId}";

	}

}
