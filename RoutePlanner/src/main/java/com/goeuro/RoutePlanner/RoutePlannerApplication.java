package com.goeuro.RoutePlanner;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import com.goeuro.RoutePlanner.DAO.RootPlannerDao;
import com.goeuro.RoutePlanner.DAO.impl.RoutePlannerDaoImpl;

@SpringBootApplication
public class RoutePlannerApplication  {


	@Autowired
	private ResourceLoader resourceLoader;

	@Value("${defaultroutes}")
	private String defRoutes;
	

	private static final Logger LOGGER = LoggerFactory.getLogger(RoutePlannerConfigurationeManager.class);

	public static String filePath = null ;

	public static void main(String[] args) {
		if(args!=null &&args.length>0&&args[0]!=null)
		{
			filePath = args[0];
		}

		try{
			Object[] objectSource = { RoutePlannerApplication.class, RoutePlannerConfigurationeManager.class };
			SpringApplication.run(objectSource, args);
		}catch(Exception ex)
		{
			LOGGER.error("Error occured while loading Context ",ex);
		}
	}


	@Bean
	public RootPlannerDao RootPlannerDao() throws IOException,NumberFormatException{
		RootPlannerDao rootPlannerDao = null ;
		FileInputStream file= null;
		if(StringUtils.isEmpty(filePath)){
			System.out.println("reading default routes from resources data/example file ");
			Resource resource = resourceLoader.getResource(defRoutes);
			file = new FileInputStream(resource.getFile());

		}else
		{
			file = new FileInputStream(filePath);
			LOGGER.info("reading routes from commandLin argse");
		}
		rootPlannerDao  = new RoutePlannerDaoImpl(file);
		return rootPlannerDao;
	}



}
