package com.goeuro.RoutePlanner.util;

public class RouteValidatorUtil {

	
	public static boolean validateRequest(Integer arrivalId, Integer departureId)
	{
		boolean isValidRequest = true;
		if(arrivalId==null ||arrivalId<0 )
		{
			isValidRequest = false;
		}
		if(departureId==null || departureId<0)
		{
			isValidRequest = false;
		}
		return isValidRequest;
		
	}
	
	
	
	
	
	
}
