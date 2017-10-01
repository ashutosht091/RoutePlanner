package com.goeuro.RoutePlanner.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.goeuro.RoutePlanner.pojo.ValidationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RootValidatorUtilTest {

	
	private static int depId1 = -3;
	private static int arrId1 = -6;
	

	@Test
	public void checkValidation() {
	
		Boolean result = null;
		try{
		result =RouteValidatorUtil.validateRequest(depId1, arrId1);
		assertFalse(true);
		}catch(ValidationException exception)
		{
			assertTrue(true);
			return;
		}
		assertFalse(true);
	}
	

}
