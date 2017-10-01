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
		// TODO Auto-generated method stub
		Set<Integer> routes  = null;
		try{
			Map<Integer,Station> stationMap = routePlannerDao.getAvailableRoutes();
			if(stationMap!=null)
			routes = this.availaibleRoutes(stationMap, departureId, arrivalId);
			LOGGER.info("routes recovered are {}",routes);
		}catch(Exception ex)
		{
			LOGGER.error("error occured while getting routes for given depart{} and arr{}",departureId,arrivalId,ex);
		}
		return RoutePlannerResponseUtil.prepareResponse(routes, arrivalId, departureId);
	}

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
			LOGGER.error("Missing Stops in any route{}",stopId);
		}


		return routes;

	}

	Boolean isReachable(Station sourceStation, int departure)
	{


		// Mark all the vertices as not visited(By default set
		// as false)
		Map<Integer,Boolean> visited = new HashMap<Integer,Boolean>();

		// Create a queue for BFS
		LinkedList<Station> queue = new LinkedList<Station>();

		// Mark the current node as visited and enqueue it
		visited.put(sourceStation.getStationId(), true);
		queue.add(sourceStation);

		// 'i' will be used to get all adjacent vertices of a vertex
		ListIterator<Station> i;
		while (queue.size()!=0)
		{
			// Dequeue a vertex from queue and print it
			sourceStation = queue.poll();

			Station adjStation;
			i = sourceStation.getAdJconnectedStations().listIterator();

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			while (i.hasNext())
			{
				adjStation = i.next();

				// If this adjacent node is the destination node,
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
