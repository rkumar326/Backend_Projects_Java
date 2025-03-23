package com.infy.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.Route;

public interface RouteRepository extends CrudRepository<Route, Integer> {

	Optional<Route> findBySourceAndDestination(String source, String destination);

}
