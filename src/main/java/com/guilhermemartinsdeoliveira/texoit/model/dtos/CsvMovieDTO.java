package com.guilhermemartinsdeoliveira.texoit.model.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CsvMovieDTO {

	private String year;
	private String title;
	private String studios;
	private String producers;
	private String winner;

}
