package com.anz.eventplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

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
	
}
