package com.anz.eventplanner.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anz.eventplanner.model.Activity;
import com.anz.eventplanner.model.Event;
import com.anz.eventplanner.service.ActivityService;

@Controller
public class ActivityController {

	@Autowired
	EventController eventController;
	
	@Autowired
	ActivityService activityService;	

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/eo-{eventOrganiserId}/event-{eventId}/newActivity" }, method = RequestMethod.GET)
	public String newActivity(@PathVariable(value = "eventId") int eventId,
			@PathVariable(value = "eventOrganiserId") int eventOrganiserId, ModelMap model) {
		Activity activity = new Activity();
		Event event = eventController.eventService.findById(eventId);
		model.addAttribute("event", event);
		model.addAttribute("activity", activity);
		model.addAttribute("edit", false);
		return "addActivity";
	}

	@RequestMapping(value = { "/eo-{eventOrganiserId}/event-{eventId}/newActivity" }, method = RequestMethod.POST)
	public String saveActivity(@PathVariable(value = "eventId") int eventId,
			@PathVariable(value = "eventOrganiserId") int eventOrganiserId, @Valid Activity activity, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "addActivity";
		}

		Event event = eventController.eventService.findById(eventId);
		activity.setActivityStatus("Open");
		activity.setEvent(event);
		activityService.saveActivity(activity);

		return "redirect:/organiser{eventOrganiserId}/plan/event{eventId}";
	}

	@RequestMapping(value = { "/eo-{eventOrganiserId}/event-{eventId}/activity-{activityId}" }, method = RequestMethod.GET)
	public String editActivity(@PathVariable(value = "eventOrganiserId") int eventOrganiserId, @PathVariable(value = "eventId") int eventId,
			@PathVariable(value = "activityId") int activityId, ModelMap model) {
		Activity activity = activityService.findById(activityId);
		Event event = activity.getEvent();
		model.addAttribute("activity", activity);
		model.addAttribute("event", event);
		model.addAttribute("eo", eventOrganiserId);
		model.addAttribute("edit", true);
		return "addActivity";
	}

	@RequestMapping(value = { "/eo-{eventOrganiserId}/event-{eventId}/activity-{activityId}" }, method = RequestMethod.POST)
	public String updateActivity(@PathVariable(value = "eventOrganiserId") int eventOrganiserId, @PathVariable(value = "eventId") int eventId,
			@PathVariable(value = "activityId") int activityId, @Valid Activity activity, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "addActivity";
		}
		
		Event event = activityService.findById(activityId).getEvent();
		activity.setEvent(event);
		activityService.updateActivity(activity);
		model.addAttribute("eventId", activity.getEvent().getEventId());

		return "redirect:/organiser{eventOrganiserId}/plan/event{eventId}";
	}

	@RequestMapping(value = { "/eo-{eventOrganiserId}/delete-activity{activityId}" }, method = RequestMethod.GET)
	public String deleteActivity(@PathVariable(value = "eventOrganiserId") int eventOrganiserId,
			@PathVariable(value = "activityId") int activityId, ModelMap model) {

		Event event = activityService.findById(activityId).getEvent();
		model.addAttribute("eventId", event.getEventId());

		activityService.deleteActivityById(activityId);
		return "redirect:/organiser{eventOrganiserId}/plan/event{eventId}";
	}	
}
