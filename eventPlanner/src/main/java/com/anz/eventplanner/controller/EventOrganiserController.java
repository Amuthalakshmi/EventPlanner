package com.anz.eventplanner.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anz.eventplanner.model.Event;
import com.anz.eventplanner.model.EventOrganiser;
import com.anz.eventplanner.model.Participant;
import com.anz.eventplanner.model.Task;
import com.anz.eventplanner.service.EventOrganiserService;
import com.anz.eventplanner.service.ParticipantService;
import com.anz.eventplanner.service.TaskService;

@Controller
public class EventOrganiserController {

	@Autowired
	EventOrganiserService eventOrganiserService;

	@Autowired
	TaskService taskService;

	@Autowired
	EventController eventController;

	@Autowired
	UserController userController;
	
	@Autowired
	ParticipantService participantService;

	/**
	 * Welcome page for Event Organisers
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/organiser{eventOrganiserId}" }, method = RequestMethod.GET)
	public String eventOrganiser(@PathVariable(value = "eventOrganiserId") int eventOrganiserId, ModelMap model) {
		String LANId = userController.user.getLANId();
		model.addAttribute("isEventManager", userController.isEventManager(LANId));
		if (userController.isEventOrganiser(LANId)) {
			EventOrganiser eventOrganiser = eventOrganiserService.findByLANId(LANId);
			model.addAttribute("isEventOrganiser", userController.isEventOrganiser(LANId));
			model.addAttribute("eventOrganiserId", eventOrganiser.getEventOrganiserId());
		}
		List<Participant> participation = participantService.findAllParticipantByLANId(LANId);
		model.addAttribute("participation", participation);

		EventOrganiser eventOrganiser = eventOrganiserService.findById(eventOrganiserId);
		model.addAttribute("eventOrganiser", eventOrganiser);
		return "eventOrganiser";
	}

	@RequestMapping(value = { "/organiser{eventOrganiserId}/plan/event{eventId}" }, method = RequestMethod.GET)
	public String planEvent(@PathVariable(value = "eventId") int eventId,
			@PathVariable(value = "eventOrganiserId") int eventOrganiserId, ModelMap model) {
		String LANId = userController.user.getLANId();
		model.addAttribute("isEventManager", userController.isEventManager(LANId));
		if (userController.isEventOrganiser(LANId)) {
			EventOrganiser eventOrganiser = eventOrganiserService.findByLANId(LANId);
			model.addAttribute("isEventOrganiser", userController.isEventOrganiser(LANId));
			model.addAttribute("eventOrganiserId", eventOrganiser.getEventOrganiserId());
		}
		List<Participant> participation = participantService.findAllParticipantByLANId(LANId);
		model.addAttribute("participation", participation);
		
		Event event = eventController.eventService.findById(eventId);
		model.addAttribute("event", event);
		Set<Task> associatedTasks = event.getAssociatedTasks();
		Set<Task> openTasks = new HashSet<Task>();
		Set<Task> closedTasks = new HashSet<Task>();
		Set<Task> startedTasks = new HashSet<Task>();

		for (Task task : associatedTasks) {
			switch (task.getTaskStatus()) {
			case "Open":
				openTasks.add(task);
				break;
			case "Started":
				startedTasks.add(task);
				break;
			case "Close":
				closedTasks.add(task);
				break;
			default:
				break;
			}
		}

		model.addAttribute("openTasks", openTasks);
		model.addAttribute("startedTasks", startedTasks);
		model.addAttribute("closedTasks", closedTasks);
		model.addAttribute("eoId", eventOrganiserId);
		return "planForEvent";
	}

}
