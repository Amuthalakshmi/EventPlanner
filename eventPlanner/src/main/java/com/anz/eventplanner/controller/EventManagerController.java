package com.anz.eventplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventManagerController {		
	

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
	
}
