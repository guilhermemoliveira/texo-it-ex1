package com.guilhermemartinsdeoliveira.texoit.services;

import java.util.List;

import com.guilhermemartinsdeoliveira.texoit.model.entities.Movie;
import com.guilhermemartinsdeoliveira.texoit.model.entities.Producer;

public interface MovieService {
	
	public List<Movie> getOnlyAwardedMoviesFromProducer(Producer producer);

}
