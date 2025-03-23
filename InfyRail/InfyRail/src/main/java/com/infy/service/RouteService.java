package com.infy.service;

import com.infy.dto.RouteDTO;
import com.infy.dto.TrainDTO;
import com.infy.exception.InfyRailException;

public interface RouteService {

	Integer addRoute(RouteDTO route) throws InfyRailException;

	RouteDTO getRoute(Integer routeId) throws InfyRailException;

	RouteDTO getAllRoutesBasedOnSourceAndDestination(String source, String destination) throws InfyRailException;

	void removeRouteWithGivenId(Integer routeId) throws InfyRailException;

	void updateTrainDetailsWithGivenRouteId(Integer routeId, TrainDTO train) throws InfyRailException;

	RouteDTO updateRouteWithGivenRouteId(Integer routeId, String source, String destination) throws InfyRailException;

}

