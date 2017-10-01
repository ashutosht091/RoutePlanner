package com.goeuro.RoutePlanner.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import com.goeuro.RoutePlanner.DAO.RootPlannerDao;
import com.goeuro.RoutePlanner.DAO.impl.RoutePlannerDaoImpl;
import com.goeuro.RoutePlanner.pojo.RouteResponse;
import com.goeuro.RoutePlanner.service.impl.RoutePlannerEngine;

@RunWith(SpringRunner.class)

public class RoutePlannerEngineTest {


	@Autowired
	private ResourceLoader resourceLoader;


	public static final String path="classpath:routes.txt";
	private static int depId1 = 3;
	private static int arrId1 = 6;
	private static int depId2 = 0;
	private static int arrId2 = 5;

	@Test
	public void checkPathPreset() throws IOException,NumberFormatException {

		RouteResponse response = null;
		RoutePlannerEngine service = new RoutePlannerEngine();
		service.setRoutePlannerDao(this.getDao());
		response =	service.checkDirectRouteAvailability(depId1, arrId1);
		assertFalse(null == response);		
		assertTrue(response.getDirectBusRoute());
		response = 	service.checkDirectRouteAvailability(depId2, arrId2);
		assertFalse(null == response);		
		assertTrue(!response.getDirectBusRoute());
	}



	public RootPlannerDao getDao() throws IOException,NumberFormatException
	{
		RootPlannerDao rootPlannerDao = null;
		Resource resource = resourceLoader.getResource(path);
		FileInputStream file = new FileInputStream(resource.getFile());
		rootPlannerDao  = new RoutePlannerDaoImpl(file);
		return rootPlannerDao;
	}
}
