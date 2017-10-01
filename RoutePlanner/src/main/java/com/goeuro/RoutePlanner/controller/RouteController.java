package com.goeuro.RoutePlanner.controller;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goeuro.RoutePlanner.pojo.RouteResponse;
import com.goeuro.RoutePlanner.service.RoutePlannerService;
import com.goeuro.RoutePlanner.util.RoutePlannerResponseUtil;
import com.goeuro.RoutePlanner.util.RouteValidatorUtil;


/**
 * @author ashutosh
 *
 */
@RestController
public class RouteController {

@Autowired
RoutePlannerService service;
	

private static final Logger LOGGER = LoggerFactory.getLogger(RouteController.class);

    @RequestMapping(value = "/api/direct", method = RequestMethod.GET)
	public RouteResponse isDirectRootAvailable(@RequestParam("dep_sid") Integer departureId,@RequestParam("arr_sid") Integer arrivalId)
	{
    	RouteResponse response = null;
		try{
			RouteValidatorUtil.validateRequest(departureId, arrivalId);
			response = service.checkDirectRouteAvailability(departureId, arrivalId);
		}catch(Exception exception)
		{
			LOGGER.error("error occured while getting response ",exception);
			//we can set error response here with error code ;
			response = RoutePlannerResponseUtil.prepareResponse(Collections.EMPTY_SET, arrivalId, departureId);
		}
		return response;
	}
	
	
	
}
