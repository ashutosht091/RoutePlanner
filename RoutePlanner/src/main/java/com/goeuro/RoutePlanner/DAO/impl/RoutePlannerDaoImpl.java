package com.goeuro.RoutePlanner.DAO.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goeuro.RoutePlanner.DAO.RootPlannerDao;
import com.goeuro.RoutePlanner.controller.RouteController;
import com.goeuro.RoutePlanner.pojo.Station;


public class RoutePlannerDaoImpl implements RootPlannerDao {

	private FileInputStream file;

	private Map<Integer,Station> stationMap ;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoutePlannerDaoImpl.class);



	
	public void generateDataBase() throws IOException,NumberFormatException
	{
		try (BufferedReader br
				= new BufferedReader(new InputStreamReader(file))) {
			String line;
			int counter = 0;
			while ((line = br.readLine()) != null) {
				if(counter == 0)
				{
					generateDataSet(Integer.parseInt(line.trim()));
					++counter;
				}else
				{
					addRoute(line);
				}
			}
		}

	}


	private void generateDataSet(int size)
	{
		stationMap = new HashMap<Integer,Station>(size);
	}
	private void addRoute(String routeDetails) throws NumberFormatException
	{

		try{
		String[] route = routeDetails.split(" ");
		Integer routeId = Integer.parseInt(route[0]);
		Integer prevStationsId = null;
		for(int i = 1;i<route.length;i++)
		{
			Station station = null;
			if(stationMap.get(Integer.parseInt(route[i]))!=null)
			{

				station = stationMap.get(Integer.parseInt(route[i]));
				station.getRouteIds().add(routeId);
			}else
			{
				Set<Integer> routeIds = new HashSet<Integer>();
				routeIds.add(routeId);
				List<Station> stations = new ArrayList<Station>();
				station = new Station(Integer.parseInt(route[i]), routeIds, stations);
				stationMap.put(Integer.parseInt(route[i]), station);
			}
			if(prevStationsId!=null)
			{
				stationMap.get(prevStationsId).getAdJconnectedStations().add(station);
			}
			prevStationsId = 	Integer.parseInt(route[i]);
		}
		}catch(NumberFormatException ex)
		{
			LOGGER.error("error occured while parsing the file",ex);
			throw ex;
		}catch(Exception ex)
		{
			LOGGER.error("Internal error occured while generating station map",ex);
			throw ex;
		}
	}	

	public RoutePlannerDaoImpl(FileInputStream file)throws IOException,NumberFormatException
	{
		this.file = file;
		generateDataBase();
	}

	@Override
	public Map<Integer,Station>  getAvailableRoutes() {
		
		
		
		
		return this.stationMap;
	}


}
