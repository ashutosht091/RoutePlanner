package com.goeuro.RoutePlanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.goeuro.RoutePlanner.DAO.RootPlannerDao;
import com.goeuro.RoutePlanner.DAO.impl.RoutePlannerDaoImpl;

@SpringBootApplication
public class RoutePlannerApplication implements ApplicationRunner {

	private static String path ;
	
	private final static int index = 0;
	public static void main(String[] args) {
		path = args[index];
		SpringApplication.run(RoutePlannerApplication.class, args);
		
	}


	@Bean
	public RootPlannerDao RootPlannerDao() throws FileNotFoundException{
		RootPlannerDao rootPlannerDao = null ;
		FileInputStream file = new FileInputStream(path);
		rootPlannerDao  = new RoutePlannerDaoImpl(file);
		return rootPlannerDao;
	}


	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		
		this.path = arg0.getSourceArgs()[index];
	}

}
