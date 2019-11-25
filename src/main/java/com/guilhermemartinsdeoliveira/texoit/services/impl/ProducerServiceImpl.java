package com.guilhermemartinsdeoliveira.texoit.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.guilhermemartinsdeoliveira.texoit.model.entities.Producer;
import com.guilhermemartinsdeoliveira.texoit.model.enums.YesOrNo;
import com.guilhermemartinsdeoliveira.texoit.repositories.ProducerRepository;
import com.guilhermemartinsdeoliveira.texoit.services.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService {

	private ProducerRepository producerRepository;

	@Autowired
	public ProducerServiceImpl(ProducerRepository producerRepository) {
		this.producerRepository = producerRepository;
	}

	/**
	 * Gets all the producers from the database and filters those who has more than
	 * 1 awarded movie. This method only filters the quantity of awarded movies, it
	 * does not restrain the other movies that may be present.
	 * 
	 * @return Only producers with two or more awarded movies.
	 */
	@Override
	public List<Producer> getAllProducersWithMoreThan1AwardedMovie() {
		return producerRepository.getAllProducers().stream().filter(this::validateMoreThan1AwardedMovieByProducer)
				.collect(Collectors.toList());
	}

	private boolean validateMoreThan1AwardedMovieByProducer(Producer producer) {
		return !CollectionUtils.isEmpty(producer.getMovies())
				&& producer.getMovies().stream().filter(movie -> movie.getWinner().equals(YesOrNo.YES)).count() > 1;
	}

}
