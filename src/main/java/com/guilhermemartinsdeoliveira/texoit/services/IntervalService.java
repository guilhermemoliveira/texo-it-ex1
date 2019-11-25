package com.guilhermemartinsdeoliveira.texoit.services;

import java.util.List;

import com.guilhermemartinsdeoliveira.texoit.model.dtos.IntervalDTO;
import com.guilhermemartinsdeoliveira.texoit.model.entities.Producer;

public interface IntervalService {
	
	public List<IntervalDTO> getShortestAwardIntervalsByProducers(List<Producer> producers);
	public List<IntervalDTO> getLongestAwardIntervalsByProducers(List<Producer> producers);

}
