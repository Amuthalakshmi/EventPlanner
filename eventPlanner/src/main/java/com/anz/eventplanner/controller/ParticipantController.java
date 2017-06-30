package com.anz.eventplanner.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.anz.eventplanner.model.EventOrganiser;
import com.anz.eventplanner.model.Participant;
import com.anz.eventplanner.service.ChildService;
import com.anz.eventplanner.service.ParticipantService;

@Controller
public class ParticipantController {
	@Autowired
	EventController eventController;

	@Autowired
	EventOrganiserController eventOrganiserController;

	@Autowired
	UserController userController;

	@Autowired
	ChildService childService;

	@Autowired
	ParticipantService participantService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public String registration(ModelMap model) {
		String LANId = userController.user.getLANId();
		model.addAttribute("isEventManager", userController.isEventManager(LANId));
		if (userController.isEventOrganiser(LANId)) {
			EventOrganiser eventOrganiser = eventOrganiserController.eventOrganiserService.findByLANId(LANId);
			model.addAttribute("isEventOrganiser", userController.isEventOrganiser(LANId));
			model.addAttribute("eventOrganiserId", eventOrganiser.getEventOrganiserId());
		}

		List<Participant> participation = participantService.findAllParticipantByLANId(LANId);
		model.addAttribute("registeredEvents", participation.size());

		List<Participant> confirmedRegistrations = new ArrayList<Participant>();
		List<Participant> waitListedRegisrations = new ArrayList<Participant>();
		List<Participant> cancelledRegistrations = new ArrayList<Participant>();

		for (Participant p : participation) {
			switch (p.getRegistrationStatus()) {
			case "Confirmed":
				confirmedRegistrations.add(p);
				break;
			case "Wait-list":
				waitListedRegisrations.add(p);
				break;
			case "Cancelled":
				cancelledRegistrations.add(p);
				break;
			default:
				break;
			}
		}

		model.addAttribute("confirmedRegistrations", confirmedRegistrations);
		model.addAttribute("waitListedRegisrations", waitListedRegisrations);
		model.addAttribute("cancelledRegistrations", cancelledRegistrations);

		return "registration";
	}

	@RequestMapping(value = { "/event{eventId}/register" }, method = RequestMethod.GET)
	public String registration(@PathVariable(value = "eventId") int eventId, ModelMap model) {
		String LANId = userController.user.getLANId();
		model.addAttribute("isEventManager", userController.isEventManager(LANId));
		if (userController.isEventOrganiser(LANId)) {
			EventOrganiser eventOrganiser = eventOrganiserController.eventOrganiserService.findByLANId(LANId);
			model.addAttribute("isEventOrganiser", userController.isEventOrganiser(LANId));
			model.addAttribute("eventOrganiserId", eventOrganiser.getEventOrganiserId());
		}
		List<Participant> participation = participantService.findAllParticipantByLANId(LANId);
		model.addAttribute("participation", participation);

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

		Event event = eventController.eventService.findById(eventId);
		participant.setEvent(event);

		event.setRegisteredParticipants(event.getRegisteredParticipants() + participant.getNumberOfChildren());
		eventController.eventService.updateRegisteredParticipants(event);

		if (event.getRegisteredParticipants() <= event.getMaxParticipants()) {
			participant.setRegistrationStatus("Confirmed");
		} else if (event.getRegisteredParticipants() > event.getMaxParticipants()) {
			participant.setRegistrationStatus("Wait-list");
		}

		for (Child child : participant.getChildren()) {
			child.setParent(participant);
			;
		}

		participantService.saveParticipant(participant);
		model.addAttribute("participantId", participant.getParticipantId());

		return "redirect:/{participantId}";
	}

	/**
	 * This method will provide a medium to update existing registration
	 * 
	 * @param participantId
	 * @param eventId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/{participantId}" }, method = RequestMethod.GET)
	public String editRegistration(@PathVariable(value = "participantId") int participantId, ModelMap model) {
		String LANId = userController.user.getLANId();
		model.addAttribute("isEventManager", userController.isEventManager(LANId));
		if (userController.isEventOrganiser(LANId)) {
			EventOrganiser eventOrganiser = eventOrganiserController.eventOrganiserService.findByLANId(LANId);
			model.addAttribute("isEventOrganiser", userController.isEventOrganiser(LANId));
			model.addAttribute("eventOrganiserId", eventOrganiser.getEventOrganiserId());
		}
		List<Participant> participation = participantService.findAllParticipantByLANId(LANId);
		model.addAttribute("participation", participation);

		Participant participant = participantService.findById(participantId);

		List<Child> uniqueChildren = new ArrayList<Child>();
		for (Child child : participant.getChildren()) {
			if (!uniqueChildren.contains(child)) {
				uniqueChildren.add(child);
			}
		}

		participant.setChildren(uniqueChildren);

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
	public String updateRegistration(@PathVariable(value = "participantId") int participantId,
			@ModelAttribute Participant participant, BindingResult participantResult, ModelMap model) {

		if (participantResult.hasErrors()) {
			return "registrationBringKidsToWork";
		}

		participantService.updateParticipant(participant);

		return "redirect:/{participantId}";
	}

	@RequestMapping(value = { "/{participantId}/cancel" }, method = RequestMethod.GET)
	public String cancelRegistration(@PathVariable(value = "participantId") int participantId,
			@ModelAttribute Participant participant, BindingResult participantResult, ModelMap model) {

		if (participantResult.hasErrors()) {
			return "registrationBringKidsToWork";
		}

		participant.setRegistrationStatus("Cancelled");
		participantService.updateRegistrationStatus(participant);

		return "redirect:/{participantId}";
	}

}
