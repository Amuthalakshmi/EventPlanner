package com.anz.eventplanner.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.anz.eventplanner.model.EventOrganiser;
import com.anz.eventplanner.service.EventOrganiserService;

@Component
public class OrganiserToTask implements Converter<Object, EventOrganiser>{
	
	@Autowired
	EventOrganiserService eventOrganiserService;
	
	@Override
	public EventOrganiser convert(Object element) {
		System.out.println("Task To Organiser");
		Integer eventOrganiserId = Integer.parseInt((String)element);
		EventOrganiser eventOrganiser = eventOrganiserService.findById(eventOrganiserId);
		System.out.println("EventOrganiser: " + eventOrganiser);
		return eventOrganiser;
	}	
}



