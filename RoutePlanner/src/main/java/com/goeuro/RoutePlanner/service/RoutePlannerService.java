package com.goeuro.RoutePlanner.service;

import com.goeuro.RoutePlanner.pojo.RouteResponse;

public interface RoutePlannerService {

	RouteResponse checkDirectRouteAvailability(Integer departureId,Integer arrivalId);
	
}
