package com.goeuro.RoutePlanner.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.goeuro.RoutePlanner.DAO.RootPlannerDao;
import com.goeuro.RoutePlanner.pojo.RouteResponse;
import com.goeuro.RoutePlanner.service.RoutePlannerService;
import com.goeuro.RoutePlanner.service.impl.RoutePlannerEngine;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoutePlannerEngineTest {

	@Autowired
	RoutePlannerService service;

	private static int depId1 = 3;
	private static int arrId1 = 6;
	private static int depId2 = 0;
	private static int arrId2 = 5;

	@Test
	public void checkPathPreset() {

		RouteResponse response =	service.checkDirectRouteAvailability(depId1, arrId1);
		assertFalse(null == response);		
		assertTrue(response.getDirectBusRoute());
		response = 	service.checkDirectRouteAvailability(depId2, arrId2);
		assertFalse(null == response);		
		assertTrue(!response.getDirectBusRoute());
	}


}
