package com.guilhermemartinsdeoliveira.texoit.configs;

import static com.guilhermemartinsdeoliveira.texoit.model.constants.Constants.CsvCustomConfigurations.PRODUCERS_COLUMN;
import static com.guilhermemartinsdeoliveira.texoit.model.constants.Constants.CsvCustomConfigurations.REGEX_SEPARATOR;
import static com.guilhermemartinsdeoliveira.texoit.model.constants.Constants.CsvCustomConfigurations.STUDIOS_COLUMN;
import static com.guilhermemartinsdeoliveira.texoit.model.constants.Constants.CsvCustomConfigurations.TITLE_COLUMN;
import static com.guilhermemartinsdeoliveira.texoit.model.constants.Constants.CsvCustomConfigurations.WINNER_COLUMN;
import static com.guilhermemartinsdeoliveira.texoit.model.constants.Constants.CsvCustomConfigurations.WINNER_COLUMN_VALUE;
import static com.guilhermemartinsdeoliveira.texoit.model.constants.Constants.CsvCustomConfigurations.WITH_HEADERS;
import static com.guilhermemartinsdeoliveira.texoit.model.constants.Constants.CsvCustomConfigurations.YEAR_COLUMN;
import static com.guilhermemartinsdeoliveira.texoit.model.constants.Constants.CsvCustomConfigurations.COLUMN_SEPARATOR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.guilhermemartinsdeoliveira.texoit.model.entities.Movie;
import com.guilhermemartinsdeoliveira.texoit.model.entities.Producer;
import com.guilhermemartinsdeoliveira.texoit.model.entities.Studio;
import com.guilhermemartinsdeoliveira.texoit.model.enums.YesOrNo;
import com.guilhermemartinsdeoliveira.texoit.utils.CustomFileUtils;

/**
 * This class is responsible for creating the beans to be referenced by
 * repositories, simulating connections with a database.
 *
 */
@Component
public class DatabaseConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfiguration.class);

	private Set<Movie> movies;
	private Set<Studio> studios;
	private Set<Producer> producers;

	@Autowired
	public DatabaseConfiguration(@Value("${texoit.files.movielist.path}") String moviesListPath) {

		this.movies = new HashSet<>();
		this.studios = new HashSet<>();
		this.producers = new HashSet<>();

		List<Map<Object, Object>> denormalizedData = new ArrayList<>();
		try {
			denormalizedData = CustomFileUtils.readCsvFromPath(moviesListPath, WITH_HEADERS, COLUMN_SEPARATOR);
			this.normalizeData(denormalizedData);
		} catch (IOException e) {
			LOGGER.error("Could not instantiate the embedded database! Message: {}", e.getMessage());
		}

	}

	private void normalizeData(List<Map<Object, Object>> denormalizedMovies) {

		for (Map<Object, Object> input : denormalizedMovies) {

			Movie movie = prepareMovie(input);

			List<Studio> studiosFromThisMovie = prepareStudios(input, movie);
			movie.addStudios(studiosFromThisMovie);

			List<Producer> producersFromThisMovie = prepareProducers(input, movie);
			movie.addProducers(producersFromThisMovie);

			prepareMovieWinner(input, movie);

			this.studios.addAll(studiosFromThisMovie);
			this.producers.addAll(producersFromThisMovie);
			this.movies.addAll(Collections.singletonList(movie));

		}

	}

	private Movie prepareMovie(Map<Object, Object> input) {
		String year = (String) input.get(YEAR_COLUMN);
		String title = (String) input.get(TITLE_COLUMN);
		return new Movie(Integer.parseInt(year), title);

	}

	private List<Studio> prepareStudios(Map<Object, Object> input, Movie movie) {
		List<Studio> studiosFromThisMovie = new ArrayList<>();

		String studiosFromMap = (String) input.get(STUDIOS_COLUMN);

		for (String studioName : studiosFromMap.split(REGEX_SEPARATOR)) {
			addStudioToSetAndAssociateMovie(movie, studiosFromThisMovie, studioName);
		}
		return studiosFromThisMovie;
	}

	private void addStudioToSetAndAssociateMovie(Movie movie, List<Studio> studiosFromThisMovie, String studioName) {
		Studio studioToAdd = new Studio(studioName.trim());
		if (!studios.contains(studioToAdd)) {
			studiosFromThisMovie.add(studioToAdd);
			studioToAdd.addMovie(movie);
		} else {
			Studio studioEntityAlreadyExistent = studios.stream()
					.filter(studioFromSet -> studioFromSet.equals(studioToAdd)).findFirst().orElse(null);
			if (studioEntityAlreadyExistent != null) {
				studioEntityAlreadyExistent.addMovie(movie);
			}
		}
	}

	private List<Producer> prepareProducers(Map<Object, Object> input, Movie movie) {
		List<Producer> producersFromThisMovie = new ArrayList<>();

		String producersFromMap = (String) input.get(PRODUCERS_COLUMN);

		for (String producerName : producersFromMap.split(REGEX_SEPARATOR)) {
			addProducerToSetAndAssociateMovie(movie, producersFromThisMovie, producerName);
		}

		return producersFromThisMovie;
	}

	private void addProducerToSetAndAssociateMovie(Movie movie, List<Producer> producersFromThisMovie,
			String producerName) {
		Producer producerToAdd = new Producer(producerName.trim());
		if (!producers.contains(producerToAdd)) {
			producersFromThisMovie.add(producerToAdd);
			producerToAdd.addMovie(movie);
		} else {
			Producer producerEntityAlreadyExistent = producers.stream()
					.filter(producerFromSet -> producerFromSet.equals(producerToAdd)).findFirst().orElse(null);
			if (producerEntityAlreadyExistent != null) {
				producerEntityAlreadyExistent.addMovie(movie);
			}
		}
	}

	private void prepareMovieWinner(Map<Object, Object> input, Movie movie) {
		String winner = (String) input.get(WINNER_COLUMN);
		if (winner.equals(WINNER_COLUMN_VALUE)) {
			movie.setWinner(YesOrNo.YES);
		} else {
			movie.setWinner(YesOrNo.NO);
		}
	}

	@Bean
	public List<Movie> movieDatabase() {
		return new ArrayList<>(movies);
	}

	@Bean
	public List<Studio> studioDatabase() {
		return new ArrayList<>(studios);
	}

	@Bean
	public List<Producer> producerDatabase() {
		return new ArrayList<>(producers);
	}

}
