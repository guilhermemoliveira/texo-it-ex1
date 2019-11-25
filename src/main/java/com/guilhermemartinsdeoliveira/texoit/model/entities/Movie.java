package com.guilhermemartinsdeoliveira.texoit.model.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.guilhermemartinsdeoliveira.texoit.model.enums.YesOrNo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = { "studios", "producers" })
@EqualsAndHashCode(of = { "year", "title" })
public class Movie {

	private Integer year;
	private String title;
	private List<Studio> studios;
	private List<Producer> producers;
	private YesOrNo winner;

	public Movie(Integer year, String title) {
		this.setYear(year);
		this.setTitle(title);
	}

	public void addStudios(List<Studio> studios) {
		if (CollectionUtils.isEmpty(this.getStudios())) {
			this.setStudios(new ArrayList<>());
		}
		for (Studio studio : studios) {
			if (!this.studios.contains(studio)) {
				this.studios.add(studio);
			}
		}
	}

	public void addProducers(List<Producer> producers) {
		if (CollectionUtils.isEmpty(this.getProducers())) {
			this.setProducers(new ArrayList<>());
		}
		for (Producer producer : producers) {
			if (!this.producers.contains(producer)) {
				this.producers.add(producer);
			}
		}
	}
}
