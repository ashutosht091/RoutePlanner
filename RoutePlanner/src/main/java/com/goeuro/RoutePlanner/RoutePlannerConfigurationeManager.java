package com.goeuro.RoutePlanner;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import com.goeuro.RoutePlanner.DAO.RootPlannerDao;
import com.goeuro.RoutePlanner.DAO.impl.RoutePlannerDaoImpl;

public class RoutePlannerConfigurationeManager {



	@Autowired
	private ResourceLoader resourceLoader;

	@Value("${defaultroutes}")
	private String defRoutes;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoutePlannerConfigurationeManager.class);

	@Bean
	public RootPlannerDao RootPlannerDao(@Value("${routes:}") String path) throws IOException,NumberFormatException{
		RootPlannerDao rootPlannerDao = null ;
		FileInputStream file= null;
		if(StringUtils.isEmpty(path.trim())){
			System.out.println("reading default routes from resources route.txt file ");
			Resource resource = resourceLoader.getResource(defRoutes);
			file = new FileInputStream(resource.getFile());

		}else
		{
			file = new FileInputStream(path);
			LOGGER.info("reading routes from commandLin argse");
		}
		rootPlannerDao  = new RoutePlannerDaoImpl(file);
		return rootPlannerDao;
	}
	
	
}
