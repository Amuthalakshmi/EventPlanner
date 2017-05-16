package com.anz.eventplanner.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anz.eventplanner.model.Child;
import com.anz.eventplanner.model.ChildListWrapper;
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
				model.addAttribute("participant", participant);

				ChildListWrapper childListWrapper = new ChildListWrapper();
				childListWrapper.addChild(new Child());

				model.addAttribute("childListWrapper", childListWrapper);
				
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
	public String saveRegistration(@Valid Event event, BindingResult eventResult, @Valid Participant participant,
			BindingResult participantResult, @Valid ChildListWrapper childListWrapper,
			BindingResult childListWrapperResult, ModelMap model, RedirectAttributes redirectAttributes) {
		 
		if (participantResult.hasErrors() || childListWrapperResult.hasErrors()) {
			return "registrationBringKidsToWork";
		}

		participant.setEventId(event.getEventId());
		participantService.saveParticipant(participant);

		for (int i = 0; i < childListWrapper.getChildList().size(); i++) {
			Child child = childListWrapper.getChildList().get(i);
			child.setParentParticipantId(participant.getParticipantId());
			childService.saveChild(child);
		}

		return "registrationBringKidsToWork";
	}

	@RequestMapping(value = { "/list-registration" }, method = RequestMethod.GET)
	public String listevents() {
		return null;
	}

	/**
	 * This method will provide a medium to update existing Event Managers
	 * 
	 * @param employeeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/event{eventId}/{participantId}" }, method = RequestMethod.GET)
	public String editRegistration(@PathVariable(value = "participantId") int participantId,
			@PathVariable(value = "eventId") int eventId, ModelMap model) {
		Event event = eventController.eventService.findById(eventId);
		model.addAttribute("event", event);

		model.addAttribute("showForm", true);

		Participant participant = participantService.findById(participantId);
		model.addAttribute("participant", participant);

		ChildListWrapper childListWrapper = new ChildListWrapper();
		List<Child> children = childService.findByParentParticipantId(participantId);
		childListWrapper.setChildList(children);
		model.addAttribute("childListWrapper", childListWrapper);

		System.out.println("Edit" + childListWrapper);
		System.out.println("Edit" + participant);

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
	@RequestMapping(value = { "/event{eventId}/{participantId}" }, method = RequestMethod.POST)
	public String updateRegistration(@PathVariable(value = "participantId") int participantId,
			@PathVariable(value = "eventId") int eventId, @PathVariable(value = "participantId") int parentParticipantId, @ModelAttribute Participant participant,
			BindingResult participantResult, @ModelAttribute("childListWrapper") ChildListWrapper childListWrapper,
			BindingResult childListWrapperResult, ModelMap model) {
		
		System.out.println("Update" + childListWrapper);
		System.out.println("Update" + participant);

		List<Child> children = childListWrapper.getChildList();	

		if (participantResult.hasErrors() || childListWrapperResult.hasErrors()) {
			return "registrationBringKidsToWork";
		}

		participantService.updateParticipant(participant);

		for (int i = 0; i < children.size(); i++) {
			childService.updateChild(children.get(i));
		}

		return "redirect:/participant/event{eventId}/{participantId}";

	}

}
