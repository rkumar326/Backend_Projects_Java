package com.infy.service;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.RouteDTO;
import com.infy.dto.TrainDTO;
import com.infy.entity.Route;
import com.infy.entity.Train;
import com.infy.exception.InfyRailException;
import com.infy.repository.RouteRepository;
import com.infy.repository.TrainRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RouteServiceImpl implements RouteService{
	
	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	TrainRepository trainRepository;
	
	@Override
	public Integer addRoute(RouteDTO route) throws InfyRailException {
//		Route newRoute = new Route();
//		
//		newRoute.setId(route.getId());
//		newRoute.setDestination(route.getDestination());
//		newRoute.setSource(route.getSource());
//		List<TrainDTO> routeDTOs = route.getTrainList(); 
//		
//		List<Train> trains;
//		
//		trains = routeDTOs.stream()
//				.map(c -> new Train(c.getId(),c.getArrivalTime(),c.getDepartureTime(),c.getFare(), c.getTrainName()))
//				.collect(Collectors.toList());
//		
//		newRoute.setTrainList(trains);
//		
//		routeRepository.save(newRoute);
//		return newRoute.getId();
		return null;
	}

	@Override
	public RouteDTO getRoute(Integer routeId) throws InfyRailException {
		Optional<Route> optional =  routeRepository.findById(routeId);
		Route route = optional.orElseThrow(()->new InfyRailException("Service.ROUTE_NOT_FOUND"));
		
		System.out.println("@#@#@#@# Route details are : " + route.getId() + " " + route.getSource() + " " + route.getDestination()+ " " + route.getTrainList());
		RouteDTO routeDTO = new RouteDTO();
		routeDTO.setDestination(route.getDestination());
		routeDTO.setId(route.getId());
		routeDTO.setSource(route.getSource());
		List<Train> trains = route.getTrainList();
		
		List<TrainDTO> trainDtos = new LinkedList<>();
		
		if(!trains.isEmpty()) {
			trainDtos = trains.stream()
					    .map(n -> new TrainDTO(n.getId(),n.getArrivalTime(),n.getDepartureTime(),n.getTrainName(),n.getFare()))
					    .collect(Collectors.toList());
		}
		
		routeDTO.setTrainList(trainDtos);
		return routeDTO;
	}

	@Override
	public RouteDTO getAllRoutesBasedOnSourceAndDestination(String source, String destination) throws InfyRailException {
		Optional<Route> optional = routeRepository.findBySourceAndDestination(source,destination);
		Route route = optional.orElseThrow(()-> new InfyRailException("Service.ROUTE_NOT_FOUND"));
//		Integer routeId = route.getId();
		
		RouteDTO routeDTO = new RouteDTO();
		routeDTO.setId(route.getId());
		routeDTO.setSource(route.getSource());
		routeDTO.setDestination(route.getDestination());
		List<Train> trains = route.getTrainList();
		
		List<TrainDTO> trainDtos = new LinkedList<>();
		if(!trains.isEmpty()) {
			trainDtos = trains.stream()
						.map(n -> new TrainDTO(n.getId(),n.getArrivalTime(),n.getDepartureTime(),n.getTrainName(),n.getFare()))
						.collect(Collectors.toList());
		}
		routeDTO.setTrainList(trainDtos);
		return routeDTO;
	}

	@Override
	public void removeRouteWithGivenId(Integer routeId) throws InfyRailException {
		Optional<Route> optional = routeRepository.findById(routeId);
		Route route = optional.orElseThrow(() -> new InfyRailException("Service.ROUTE_NOT_FOUND"));
		routeRepository.delete(route);
	}

	@Override
	public void updateTrainDetailsWithGivenRouteId(Integer routeId, TrainDTO train) throws InfyRailException {
		Iterable<Train> listOfTrainWithGivenRouteId = trainRepository.findAllById(routeId);
		listOfTrainWithGivenRouteId.forEach(singleTrain -> {
			singleTrain.setArrivalTime(train.getArrivalTime());
			singleTrain.setDepartureTime(train.getDepartureTime());
			singleTrain.setFare(train.getFare());
			singleTrain.setId(train.getId());
			singleTrain.setTrainName(train.getTrainName());
		});
	}

	@Override
	public RouteDTO updateRouteWithGivenRouteId(Integer routeId, String source, String destination) throws InfyRailException {
		Optional<Route> optional = routeRepository.findById(routeId);
		Route routeFromDB = optional.orElseThrow(()-> new InfyRailException("Route not found"));
		
		routeFromDB.setDestination(destination);
		routeFromDB.setSource(source);
		
		RouteDTO routeDto = new RouteDTO();
		routeDto.setId(routeId);
		routeDto.setSource(source);
		routeDto.setDestination(destination);
		routeDto.setTrainList(null);
		
 		return routeDto;
	}

}

