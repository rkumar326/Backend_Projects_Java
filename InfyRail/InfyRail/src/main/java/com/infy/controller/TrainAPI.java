package com.infy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.TrainDTO;
import com.infy.exception.InfyRailException;
import com.infy.service.TrainService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/infyrail")
public class TrainAPI {

	@Autowired
	TrainService trainService;

//	 Create train details
	// Not working
//	This request should add the details of a train to the database and return the train id
//	This API is working but data is getting inserted as null. This statement is also not working after using @NotNUll in trainDTO
	@PostMapping(value = "/trains")
	public ResponseEntity<Integer> addTrain(@RequestBody @Valid TrainDTO train) throws InfyRailException {
		Integer trainId = trainService.addTrain(train);
		return new ResponseEntity<Integer>(trainId, HttpStatus.OK);
	}

//	 Update fare
//	This request should update the fare of a particular train and return the success message
	// working fine
	@PutMapping(value = "/trains/{trainId}")
	public ResponseEntity<String> updateFare(@PathVariable Integer trainId, @RequestBody TrainDTO train)
			throws InfyRailException {
		trainService.updateTrain(trainId, train.getFare());
		String msg = "Train fair gets sucessfully updated";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}

