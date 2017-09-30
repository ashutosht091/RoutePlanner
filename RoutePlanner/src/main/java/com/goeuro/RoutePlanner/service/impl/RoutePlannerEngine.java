package com.goeuro.RoutePlanner.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.goeuro.RoutePlanner.DAO.RootPlannerDao;
import com.goeuro.RoutePlanner.pojo.RouteResponse;
import com.goeuro.RoutePlanner.pojo.Station;
import com.goeuro.RoutePlanner.service.RoutePlannerService;

@Service
public class RoutePlannerEngine implements RoutePlannerService {

	@Autowired
	RootPlannerDao routePlannerDao;


	@Override
	public RouteResponse checkDirectRouteAvailability(Integer departureId, Integer arrivalId) {
		// TODO Auto-generated method stub
		Set<Integer> routes  = null;
		Map<Integer,Station> stationMap = routePlannerDao.getAvailableRoutes();

		routes = this.availaibleRoutes(stationMap, departureId, arrivalId);

		return this.prepareResponse(routes, arrivalId, departureId);
	}

	public Set<Integer> availaibleRoutes(Map<Integer,Station> stationMap,int departId, int arrivalId)
	{
		Set<Integer> routes = new HashSet<Integer>(1);
		Station departureStation = stationMap.get(departId);
		Station arrivalStation  = stationMap.get(arrivalId);
		for(Integer route:departureStation.getRouteIds())
		{
			if(arrivalStation.getRouteIds().contains(route))
			{
				if(isReachable(departureStation,arrivalStation.getStationId()))
				routes.add(route);
			}
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
	private RouteResponse prepareResponse(Set<Integer> routes,Integer arrivalId,Integer departureId)
	{

		RouteResponse response= new RouteResponse(departureId, arrivalId, false);
		if(!CollectionUtils.isEmpty(routes))
		{
			response.setDirectBusRoute(true);
		}
		return response;

	}


}
