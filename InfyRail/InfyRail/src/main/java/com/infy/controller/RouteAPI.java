package com.infy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.RouteDTO;
import com.infy.dto.TrainDTO;
import com.infy.exception.InfyRailException;
import com.infy.service.RouteService;
import com.infy.service.TrainService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/infyrail")
@Validated
public class RouteAPI {

	@Autowired
	RouteService routeService;

	@Autowired
	TrainService trainService;

	// Create a Route
	// Working fine but i think business operation is not as expected
	@PostMapping(value = "/routes")
	public ResponseEntity<Integer> addRoute(@RequestBody @Valid RouteDTO route) throws InfyRailException {
		Integer routeId = routeService.addRoute(route);
		return new ResponseEntity<>(routeId, HttpStatus.OK);
	}

	// View a route
	// Working for happy case
	@GetMapping(value = "/routes/{routeId}")
	public ResponseEntity<RouteDTO> getRoute(@PathVariable Integer routeId) throws InfyRailException {
		RouteDTO route = routeService.getRoute(routeId);
		return new ResponseEntity<>(route, HttpStatus.OK);
	}

	// View train details by source and destination
	// list of routeDTO kyu chahiye, ek source and destination se ek hi route id
	// milegi naa
	// something exception getting
	@GetMapping(value = "/routes/trains?source=&destination=")
	public ResponseEntity<RouteDTO> getAllRoutes(@RequestParam String source, @RequestParam String destination)
			throws InfyRailException {
		RouteDTO allRoutes = routeService.getAllRoutesBasedOnSourceAndDestination(source, destination);
		return new ResponseEntity<>(allRoutes, HttpStatus.OK);
	}

	// Update route details
	@PutMapping(value = "/routes/{routeId}")
	public ResponseEntity<RouteDTO> udpateRoute(@PathVariable Integer routeId, @RequestBody RouteDTO route)
			throws InfyRailException {
		RouteDTO routeDto = routeService.updateRouteWithGivenRouteId(routeId, route.getSource(),
				route.getDestination());
//		String msg = "Successfully update route from : " + route + " to " + routeDto;
		return new ResponseEntity<RouteDTO>(routeDto, HttpStatus.OK);
	}

	// Delete a train from the list of trains
	@DeleteMapping(value = "/routes/{routeId}/{trainId}")
	public ResponseEntity<String> removeTrain(@PathVariable Integer routeId, @PathVariable Integer trainId)
			throws InfyRailException {
		trainService.removeTrainWithGivenTrainidAndRouteid(trainId, routeId);
		String msg = "Train removed successfully with given routeId : " + routeId + " and trainId : " + trainId;
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

//	Update train details in the existing route
	// Ye thoda tedha lag rha h
//	@PutMapping(value="/routes/{routeId}")
	public ResponseEntity<String> updateTrain(@PathVariable Integer routeId, @RequestBody TrainDTO train)
			throws InfyRailException {
		routeService.updateTrainDetailsWithGivenRouteId(routeId, train);
		String msg = "Train details successfully get updated";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
