package com.guilhermemartinsdeoliveira.texoit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermemartinsdeoliveira.texoit.model.constants.Constants;
import com.guilhermemartinsdeoliveira.texoit.model.dtos.IntervalDTO;
import com.guilhermemartinsdeoliveira.texoit.model.dtos.IntervalResponseDTO;
import com.guilhermemartinsdeoliveira.texoit.model.entities.Producer;
import com.guilhermemartinsdeoliveira.texoit.services.IntervalService;
import com.guilhermemartinsdeoliveira.texoit.services.ProducerService;

@RestController
public class IntervalController {

	private ProducerService producerService;
	private IntervalService intervalService;

	@Autowired
	public IntervalController(ProducerService producerService, IntervalService intervalService) {
		this.producerService = producerService;
		this.intervalService = intervalService;

	}

	@GetMapping(Constants.ProducerRoutes.ROUTE_MIN_AND_MAX_INTERVAL_PRODUCERS)
	public ResponseEntity<IntervalResponseDTO> getMinAndMaxIntervalBetweenAwards() {
		List<Producer> producers = producerService.getAllProducersWithMoreThan1AwardedMovie();

		List<IntervalDTO> max = intervalService.getLongestAwardIntervalsByProducers(producers);
		List<IntervalDTO> min = intervalService.getShortestAwardIntervalsByProducers(producers);

		IntervalResponseDTO response = new IntervalResponseDTO(min, max);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
