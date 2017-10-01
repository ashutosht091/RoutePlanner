package com.goeuro.RoutePlanner.util;

import com.goeuro.RoutePlanner.pojo.ValidationException;

public class RouteValidatorUtil {


	public static boolean validateRequest( Integer departureId,Integer arrivalId) throws ValidationException
	{
		StringBuilder errorString= new StringBuilder();
		boolean isValidRequest = true;
		if(arrivalId==null ||arrivalId<0 )
		{
			isValidRequest = false;
			errorString.append(RoutePlannerConstants.ARRIVAL_ID_INVALID+RoutePlannerConstants.DELIMITER+arrivalId);

		}
		if(departureId==null || departureId<0)
		{
			isValidRequest = false;
			errorString.append(RoutePlannerConstants.DEPARTURE_ID_INVALID+RoutePlannerConstants.DELIMITER+departureId);
		}
		if(isValidRequest&&(departureId.equals(arrivalId)))
		{
			isValidRequest=false;
			errorString.append(RoutePlannerConstants.ARRIVAL_ID_EQUAL_DEPARTURE_ID+RoutePlannerConstants.DELIMITER+arrivalId);

		}
		if(!isValidRequest)
		{
			throw new ValidationException(errorString.toString());
		}
		return isValidRequest;

	}






}
