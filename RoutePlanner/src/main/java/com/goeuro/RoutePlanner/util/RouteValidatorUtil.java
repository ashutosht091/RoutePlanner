package com.goeuro.RoutePlanner.util;

import com.goeuro.RoutePlanner.pojo.ValidationException;

public class RouteValidatorUtil {


	public static boolean validateRequest( Integer departureId,Integer arrivalId) throws ValidationException
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

		if(!isValidRequest)
		{
			throw new ValidationException("invalid input Parameters departure+" +departureId+"arrival"+arrivalId);
		}
		return isValidRequest;

	}






}
