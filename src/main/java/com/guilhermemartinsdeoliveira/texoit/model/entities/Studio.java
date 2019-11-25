package com.guilhermemartinsdeoliveira.texoit.model.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = { "movies" })
public class Studio {

	private String name;
	private List<Movie> movies;

	public Studio(String name) {
		this.name = name;
	}

	public void addMovie(Movie movie) {
		if (CollectionUtils.isEmpty(this.getMovies())) {
			this.setMovies(new ArrayList<>());
		}

		if (!this.getMovies().contains(movie)) {
			this.getMovies().add(movie);
		}
	}
}
