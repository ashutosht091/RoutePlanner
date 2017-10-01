# RoutePlanner
Rest Service for checking direct routes.

This service returns true /false based on direct root availability . 

Sample data can be viewed in /data/example file .If No File supplied through commandlinearguments then this file will be used 

Rest endpoints : http://{host}:8088/api/direct?dep_sid={departureID}&arr_sid={arrivalID} 

Method :GET

Sample response :
{
    "dep_sid": 3,
    "arr_sid": 6,
    "direct_bus_route": true
}
