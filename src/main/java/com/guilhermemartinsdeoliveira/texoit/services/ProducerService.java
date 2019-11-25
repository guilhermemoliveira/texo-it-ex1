package com.guilhermemartinsdeoliveira.texoit.services;

import java.util.List;

import com.guilhermemartinsdeoliveira.texoit.model.entities.Producer;

public interface ProducerService {
	
	public List<Producer> getAllProducersWithMoreThan1AwardedMovie();

}
