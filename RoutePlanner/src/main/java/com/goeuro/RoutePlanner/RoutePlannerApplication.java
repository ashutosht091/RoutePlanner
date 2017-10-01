package com.goeuro.RoutePlanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoutePlannerApplication  {


	

	private static final Logger LOGGER = LoggerFactory.getLogger(RoutePlannerConfigurationeManager.class);

	
	public static void main(String[] args) {
		
		Object[] objectSource = { RoutePlannerApplication.class, RoutePlannerConfigurationeManager.class };
		SpringApplication.run(objectSource, args);

	}

	



}
