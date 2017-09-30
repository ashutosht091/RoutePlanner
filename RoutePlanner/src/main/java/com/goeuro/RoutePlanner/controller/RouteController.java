package com.goeuro.RoutePlanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goeuro.RoutePlanner.pojo.RouteResponse;
import com.goeuro.RoutePlanner.pojo.RouteResponse;
import com.goeuro.RoutePlanner.service.RoutePlannerService;

@RestController
public class RouteController {

@Autowired
RoutePlannerService service;
	
    @RequestMapping(value = "/v1/checkKey", method = RequestMethod.GET)
	public RouteResponse isDirectRootAvailable(@RequestParam("dep_sid") Integer departureId,@RequestParam("arr_sid") Integer arrivalId)
	{
		
		return service.checkDirectRouteAvailability(departureId, arrivalId);
	}
	
	
	
}
