package com.goeuro.RoutePlanner.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goeuro.RoutePlanner.DAO.RootPlannerDao;
import com.goeuro.RoutePlanner.pojo.RouteResponse;
import com.goeuro.RoutePlanner.pojo.Station;
import com.goeuro.RoutePlanner.service.RoutePlannerService;
import com.goeuro.RoutePlanner.util.RoutePlannerResponseUtil;

@Service
public class RoutePlannerEngine implements RoutePlannerService {

	@Autowired
	private RootPlannerDao routePlannerDao;




	private static final Logger LOGGER = LoggerFactory.getLogger(RoutePlannerEngine.class);


	@Override
	public RouteResponse checkDirectRouteAvailability(Integer departureId, Integer arrivalId) {
		
		Set<Integer> routes  = null;
		try{
			Map<Integer,Station> stationMap = routePlannerDao.getAvailableRoutes();
			if(stationMap!=null)
			routes = this.availaibleRoutes(stationMap, departureId, arrivalId);
			LOGGER.info("routes recovered are {}",routes);
		}catch(Exception ex)
		{
			LOGGER.error("error occured while getting routes for given depart {} and arr{}",departureId,arrivalId,ex);
		}
		return RoutePlannerResponseUtil.prepareResponse(routes, arrivalId, departureId);
	}

	/*
	 * This function first check whether there is common route (direct root)
	 * Then it checks whether departure connects to arrival or not
	 * 
	 */
	public Set<Integer> availaibleRoutes(Map<Integer,Station> stationMap,Integer departId, Integer arrivalId)
	{
		Set<Integer> routes = new HashSet<Integer>(1);
		Station departureStation = stationMap.get(departId);
		Station arrivalStation  = stationMap.get(arrivalId);
		if(departureStation!=null && arrivalStation!=null ){
			for(Integer route:departureStation.getRouteIds())
			{
				if(arrivalStation.getRouteIds().contains(route))
				{
					if(isReachable(departureStation,arrivalStation.getStationId()))
						routes.add(route);
				}
			}
		}else
		{
			String stopId = departureStation==null?arrivalStation==null?departId+"-"+arrivalId:departId.toString():arrivalId.toString();
			LOGGER.error("Missing Stops in any available route {}",stopId);
		}


		return routes;

	}
/*
 * Simple BFS to check the connectivity
 * This function wont be called unless there is atleast one common route
 *  then only there will exist a direct route
 */
	Boolean isReachable(Station sourceStation, int departure)
	{


		// Mark all the Stations as not visited(By default set
		// as false)
		Map<Integer,Boolean> visited = new HashMap<Integer,Boolean>();

		// Create a queue for BFS
		LinkedList<Station> queue = new LinkedList<Station>();

		// Mark the current Station as visited and enqueue it
		visited.put(sourceStation.getStationId(), true);
		queue.add(sourceStation);

		// 'adjStations' will be used to get all adjacent Stations of a station
		ListIterator<Station> adjStations;
		while (queue.size()!=0)
		{
			// Dequeue a station from queue and print it
			sourceStation = queue.poll();

			Station adjStation;
			adjStations = sourceStation.getAdJconnectedStations().listIterator();

			// Get all adjacent Stations of the dequeued station
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			while (adjStations.hasNext())
			{
				adjStation = adjStations.next();

				// If this adjacent Station is the destination Station,
				// then return true
				if (adjStation.getStationId()==departure)
					return true;

				// Else, continue to do BFS
				if (visited.containsKey(adjStation.getStationId())==false)
				{
					visited.put(adjStation.getStationId(), true) ;
					queue.add(adjStation);
				}
			}
		}

		// If BFS is complete without visited d
		return false;
	}
	public RootPlannerDao getRoutePlannerDao() {
		return routePlannerDao;
	}

	public void setRoutePlannerDao(RootPlannerDao routePlannerDao) {
		this.routePlannerDao = routePlannerDao;
	}


}
