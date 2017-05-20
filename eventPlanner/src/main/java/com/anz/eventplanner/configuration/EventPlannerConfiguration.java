package com.anz.eventplanner.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.anz.eventplanner.converter.OrganiserToTask;
import com.anz.eventplanner.converter.TaskToOrganiser;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.anz.eventplanner")
public class EventPlannerConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	TaskToOrganiser taskToOrganiser;

	@Autowired
	OrganiserToTask organiserToTask;

	/**
	 * Configure ViewResolvers to deliver preferred views.
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setExposeContextBeansAsAttributes(true);

		return viewResolver;
	}

	/*
	 * @Override public void
	 * configureDefaultServletHandling(DefaultServletHandlerConfigurer
	 * configurer) { configurer.enable(); }
	 */

	/**
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	
	/**
	 * Configure Converter to be used. 
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		System.out.println("add Formatters");
		registry.addConverter(taskToOrganiser);
		registry.addConverter(organiserToTask);
	}	

	/**
	 * Configure MessageSource to lookup any validation/error message in
	 * internationalized property files
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
}
