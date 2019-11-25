package com.guilhermemartinsdeoliveira.texoit.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.guilhermemartinsdeoliveira.texoit.model.entities.Movie;
import com.guilhermemartinsdeoliveira.texoit.model.entities.Producer;
import com.guilhermemartinsdeoliveira.texoit.model.enums.YesOrNo;
import com.guilhermemartinsdeoliveira.texoit.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService {


	/**
	 * Returns a new movie list containing only the awarded movies by producer informed.
	 * Sorts ascendently by "year".
	 * 
	 * @param producer The producer containing the list.
	 * @return The sorted movie list.
	 */
	@Override
	public List<Movie> getOnlyAwardedMoviesFromProducer(Producer producer) {
		List<Movie> movies = producer.getMovies().stream().filter(movie -> movie.getWinner().equals(YesOrNo.YES))
				.collect(Collectors.toList());
		movies.sort(Comparator.comparing(Movie::getYear));

		return movies;

	}

}
