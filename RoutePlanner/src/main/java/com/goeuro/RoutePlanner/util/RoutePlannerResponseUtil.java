package com.goeuro.RoutePlanner.util;

import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.goeuro.RoutePlanner.pojo.RouteResponse;

public class RoutePlannerResponseUtil {

	
	
	
	public static RouteResponse prepareResponse(Set<Integer> routes,Integer arrivalId,Integer departureId)
	{

		RouteResponse response= new RouteResponse(departureId, arrivalId, false);
		if(!CollectionUtils.isEmpty(routes))
		{
			response.setDirectBusRoute(true);
		}
		return response;

	}
}
