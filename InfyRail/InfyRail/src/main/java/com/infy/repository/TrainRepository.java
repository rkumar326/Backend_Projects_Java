package com.infy.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.Train;

public interface TrainRepository extends CrudRepository<Train, Integer> {

	Iterable<Train> findAllById(Integer routeId);

//	Optional<Train> findByTrainIdAndRouteId(Integer trainId, Integer routeId);

//	void deleteByTrainIdAndRouteId(Integer trainId, Integer routeId);

}
