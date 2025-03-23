package com.infy.service;

import com.infy.dto.TrainDTO;
import com.infy.exception.InfyRailException;

public interface TrainService {

	void removeTrainWithGivenTrainidAndRouteid(Integer trainId, Integer routeId) throws InfyRailException;
	Integer addTrain(TrainDTO train) throws InfyRailException;
	void updateTrain(Integer trainId, Double fare) throws InfyRailException;

}

