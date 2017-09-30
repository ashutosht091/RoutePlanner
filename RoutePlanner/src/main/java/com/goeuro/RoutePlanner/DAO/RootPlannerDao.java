package com.goeuro.RoutePlanner.DAO;

import java.util.Map;

import com.goeuro.RoutePlanner.pojo.Station;

public interface RootPlannerDao {

	Map<Integer,Station>  getAvailableRoutes();
}
