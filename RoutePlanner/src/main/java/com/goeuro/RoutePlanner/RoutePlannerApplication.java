package com.goeuro.RoutePlanner;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import com.goeuro.RoutePlanner.DAO.RootPlannerDao;
import com.goeuro.RoutePlanner.DAO.impl.RoutePlannerDaoImpl;
import com.goeuro.RoutePlanner.service.RoutePlannerService;

@SpringBootApplication
public class RoutePlannerApplication  {


	

	private static final Logger LOGGER = LoggerFactory.getLogger(RoutePlannerConfigurationeManager.class);

	
	public static void main(String[] args) {
		
		Object[] objectSource = { RoutePlannerApplication.class, RoutePlannerConfigurationeManager.class };
		SpringApplication.run(objectSource, args);

	}

	



}
