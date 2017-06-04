package com.anz.eventplanner.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.anz.eventplanner.model.Event;
import com.anz.eventplanner.model.EventOrganiser;
import com.anz.eventplanner.model.Participant;
import com.anz.eventplanner.model.Task;
import com.anz.eventplanner.service.ParticipantService;
import com.anz.eventplanner.service.TaskService;

@Controller
public class TaskController {

	@Autowired
	EventController eventController;

	@Autowired
	EventOrganiserController eventOrganiserController;

	@Autowired
	TaskService taskService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserController userController;
	
	@Autowired
	ParticipantService participantService;

	@RequestMapping(value = { "/eo-{eventOrganiserId}/event-{eventId}/newTask" }, method = RequestMethod.GET)
	public String newTask(@PathVariable(value = "eventId") int eventId,
			@PathVariable(value = "eventOrganiserId") int eventOrganiserId, ModelMap model) {
		String LANId = userController.user.getLANId();
		model.addAttribute("isEventManager", userController.isEventManager(LANId));
		if (userController.isEventOrganiser(LANId)) {
			EventOrganiser eventOrganiser = eventOrganiserController.eventOrganiserService.findByLANId(LANId);
			model.addAttribute("isEventOrganiser", userController.isEventOrganiser(LANId));
			model.addAttribute("eventOrganiserId", eventOrganiser.getEventOrganiserId());
		}
		List<Participant> participation = participantService.findAllParticipantByLANId(LANId);
		model.addAttribute("participation", participation);
		
		Task task = new Task();
		Event event = eventController.eventService.findById(eventId);
		model.addAttribute("event", event);
		model.addAttribute("task", task);
		model.addAttribute("edit", false);
		return "addTask";
	}

	@RequestMapping(value = { "/eo-{eventOrganiserId}/event-{eventId}/newTask" }, method = RequestMethod.POST)
	public String saveEvent(@PathVariable(value = "eventId") int eventId,
			@PathVariable(value = "eventOrganiserId") int eventOrganiserId, @Valid Task task, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "addTask";
		}

		Event event = eventController.eventService.findById(eventId);
		task.setTaskStatus("Open");
		task.setEvent(event);
		taskService.saveTask(task);

		return "redirect:/organiser{eventOrganiserId}/plan/event{eventId}";
	}

	@RequestMapping(value = { "/eo-{eventOrganiserId}/task-{taskId}" }, method = RequestMethod.GET)
	public String editTaskDetails(@PathVariable(value = "eventOrganiserId") int eventOrganiserId,
			@PathVariable(value = "taskId") int taskId, ModelMap model) {
		String LANId = userController.user.getLANId();
		model.addAttribute("isEventManager", userController.isEventManager(LANId));
		if (userController.isEventOrganiser(LANId)) {
			EventOrganiser eventOrganiser = eventOrganiserController.eventOrganiserService.findByLANId(LANId);
			model.addAttribute("isEventOrganiser", userController.isEventOrganiser(LANId));
			model.addAttribute("eventOrganiserId", eventOrganiser.getEventOrganiserId());
		}
		List<Participant> participation = participantService.findAllParticipantByLANId(LANId);
		model.addAttribute("participation", participation);
		
		Task task = taskService.findById(taskId);
		Event event = task.getEvent();
		model.addAttribute("task", task);
		model.addAttribute("event", event);
		model.addAttribute("eo", eventOrganiserId);
		model.addAttribute("edit", true);
		return "addTask";
	}

	@RequestMapping(value = { "/eo-{eventOrganiserId}/task-{taskId}" }, method = RequestMethod.POST)
	public String updateTaskDetails(@PathVariable(value = "eventOrganiserId") int eventOrganiserId,
			@PathVariable(value = "taskId") int taskId, @Valid Task task, BindingResult result, ModelMap model) {		
		
		if (result.hasErrors()) {
			return "addTask";
		}

		Event event = taskService.findById(taskId).getEvent();
		task.setEvent(event);
		taskService.updateTask(task);
		model.addAttribute("eventId", task.getEvent().getEventId());

		return "redirect:/organiser{eventOrganiserId}/plan/event{eventId}";
	}

	@RequestMapping(value = { "/eo-{eventOrganiserId}/delete-task{taskId}" }, method = RequestMethod.GET)
	public String deleteTask(@PathVariable(value = "eventOrganiserId") int eventOrganiserId,
			@PathVariable(value = "taskId") int taskId, ModelMap model) {

		Event event = taskService.findById(taskId).getEvent();
		model.addAttribute("eventId", event.getEventId());

		taskService.deleteTaskById(taskId);
		return "redirect:/organiser{eventOrganiserId}/plan/event{eventId}";
	}

	@RequestMapping(value = { "/eo-{eventOrganiserId}/update/task-{taskId}" }, method = RequestMethod.GET)
	public String editTask(@PathVariable(value = "eventOrganiserId") int eventOrganiserId,
			@PathVariable(value = "taskId") int taskId, ModelMap model) {
		String LANId = userController.user.getLANId();
		model.addAttribute("isEventManager", userController.isEventManager(LANId));
		if (userController.isEventOrganiser(LANId)) {
			EventOrganiser eventOrganiser = eventOrganiserController.eventOrganiserService.findByLANId(LANId);
			model.addAttribute("isEventOrganiser", userController.isEventOrganiser(LANId));
			model.addAttribute("eventOrganiserId", eventOrganiser.getEventOrganiserId());
		}
		List<Participant> participation = participantService.findAllParticipantByLANId(LANId);
		model.addAttribute("participation", participation);
		
		Task task = taskService.findById(taskId);
		Event event = task.getEvent();

		if (!task.getTaskStatus().equalsIgnoreCase("Open") && task.getTaskBlog() != null) {
			String taskBlog = (task.getTaskBlog().replaceAll("\n", "<br />"));
			task.setTaskBlog("");
			model.addAttribute("taskBlog", taskBlog);
		}

		model.addAttribute("task", task);
		model.addAttribute("event", event);
		model.addAttribute("eo", eventOrganiserId);
		model.addAttribute("edit", true);

		return "updateTask";
	}

	@RequestMapping(value = { "/eo-{eventOrganiserId}/update/task-{taskId}" }, method = RequestMethod.POST)
	public String updateTask(@PathVariable(value = "eventOrganiserId") int eventOrganiserId,
			@PathVariable(value = "taskId") int taskId, @Valid Task task, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "updateTask";
		}

		Event event = taskService.findById(taskId).getEvent();
		task.setEvent(event);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy/ HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String updatedDateAndTime = dateFormat.format(cal.getTime());

		EventOrganiser eo = eventOrganiserController.eventOrganiserService.findById(eventOrganiserId);
		String ownerOfUpdate = eo.getOrganiserName() + " " + updatedDateAndTime;

		task.setTaskBlog(ownerOfUpdate + ":\n" + task.getTaskBlog() + "\n");

		taskService.updateTaskBlog(task);
		model.addAttribute("eventId", task.getEvent().getEventId());

		return "redirect:/organiser{eventOrganiserId}/plan/event{eventId}";
	}
}
