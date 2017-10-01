package com.goeuro.RoutePlanner.pojo;

import java.util.*;

public class Station {

	Integer stationId;
	
	Set<Integer> routeIds;
	
	List<Station> adJconnectedStations;
	
	

	public Station(Integer stationId, Set<Integer> routeIds, List<Station> adJconnectedStations) {
		super();
		this.stationId = stationId;
		this.routeIds = routeIds;
		this.adJconnectedStations = adJconnectedStations;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public Set<Integer> getRouteIds() {
		return routeIds;
	}

	public void setRouteIds(Set<Integer> routeIds) {
		this.routeIds = routeIds;
	}

	public List<Station> getAdJconnectedStations() {
		return adJconnectedStations;
	}

	public void setAdJconnectedStations(List<Station> adJconnectedStations) {
		this.adJconnectedStations = adJconnectedStations;
	}

	@Override
	public String toString() {
		return "Station [stationId=" + stationId + ", routeIds=" + routeIds + ", adJconnectedStations="
				+ adJconnectedStations + "]";
	}

	

	

	

	
	
	
	
	
}
