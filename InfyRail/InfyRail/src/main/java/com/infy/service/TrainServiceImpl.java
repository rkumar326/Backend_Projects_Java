package com.infy.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.TrainDTO;
import com.infy.entity.Train;
import com.infy.exception.InfyRailException;
import com.infy.repository.TrainRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TrainServiceImpl implements TrainService{
	
	@Autowired
	TrainRepository trainRepository;

	@Override
	public void removeTrainWithGivenTrainidAndRouteid(Integer trainId, Integer routeId) throws InfyRailException {
//		Optional<Train> optional = trainRepository.findByTrainIdAndRouteId(trainId, routeId);
//		Train train = optional.orElseThrow(()-> new InfyRailException("Service.TRAIN_NOT_FOUND"));
		Integer id = trainId;
		trainRepository.deleteById(id);
//		trainRepository.deleteByTrainIdAndRouteId(trainId,routeId);
	}

	@Override
	public Integer addTrain(TrainDTO train) throws InfyRailException {
		Train newTrain = new Train();
		newTrain.setId(train.getId());
		newTrain.setArrivalTime(train.getArrivalTime());
		newTrain.setDepartureTime(train.getDepartureTime());
		newTrain.setTrainName(train.getTrainName());
		newTrain.setFare(train.getFare());
		
		trainRepository.save(newTrain);
		return newTrain.getId();
	}

	@Override
	public void updateTrain(Integer trainId, Double fare) throws InfyRailException {
		Optional<Train> optional = trainRepository.findById(trainId);
		
		Train t = optional.orElseThrow(()-> new InfyRailException("Service.TRAIN_NOT_FOUND"));
		
		t.setFare(fare);
	}

}

