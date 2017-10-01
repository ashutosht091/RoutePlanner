package com.goeuro.RoutePlanner.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"dep_sid",
"arr_sid",
"direct_bus_route"
})
public class RouteResponse implements Serializable
{

/**
* 
* (Required)
* 
*/
@JsonProperty("dep_sid")
private Integer depSid;
/**
* 
* (Required)
* 
*/
@JsonProperty("arr_sid")
private Integer arrSid;
/**
* 
* (Required)
* 
*/
@JsonProperty("direct_bus_route")
private Boolean directBusRoute;



public RouteResponse() {
	super();
	// TODO Auto-generated constructor stub
}

public RouteResponse(Integer depSid, Integer arrSid, Boolean directBusRoute) {
	super();
	this.depSid = depSid;
	this.arrSid = arrSid;
	this.directBusRoute = directBusRoute;
}

@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
private final static long serialVersionUID = 5153873573681502229L;

/**
* 
* (Required)
* 
*/
@JsonProperty("dep_sid")
public Integer getDepSid() {
return depSid;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("dep_sid")
public void setDepSid(Integer depSid) {
this.depSid = depSid;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("arr_sid")
public Integer getArrSid() {
return arrSid;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("arr_sid")
public void setArrSid(Integer arrSid) {
this.arrSid = arrSid;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("direct_bus_route")
public Boolean getDirectBusRoute() {
return directBusRoute;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("direct_bus_route")
public void setDirectBusRoute(Boolean directBusRoute) {
this.directBusRoute = directBusRoute;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
	return "Route [depSid=" + depSid + ", arrSid=" + arrSid + ", directBusRoute=" + directBusRoute
			+ ", additionalProperties=" + additionalProperties + "]";
}



}


