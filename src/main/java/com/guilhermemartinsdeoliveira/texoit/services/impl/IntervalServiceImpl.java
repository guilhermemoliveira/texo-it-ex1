package com.guilhermemartinsdeoliveira.texoit.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermemartinsdeoliveira.texoit.model.dtos.IntervalDTO;
import com.guilhermemartinsdeoliveira.texoit.model.entities.Movie;
import com.guilhermemartinsdeoliveira.texoit.model.entities.Producer;
import com.guilhermemartinsdeoliveira.texoit.services.IntervalService;
import com.guilhermemartinsdeoliveira.texoit.services.MovieService;

@Service
public class IntervalServiceImpl implements IntervalService {

	private MovieService movieService;

	@Autowired
	public IntervalServiceImpl(MovieService movieService) {
		this.movieService = movieService;
	}

	/**
	 * Returns the producers with the least time between two awards. If there's two
	 * or more producers with the same interval, all of them are returned.
	 * 
	 * @param producers producers to be filtered
	 * @return returns the list with only the producers with shortest intervals
	 *         found.
	 * 
	 */
	@Override
	public List<IntervalDTO> getShortestAwardIntervalsByProducers(List<Producer> producers) {

		List<IntervalDTO> result = new ArrayList<>();

		for (Producer producer : producers) {
			List<Movie> moviesWithAwards = movieService.getOnlyAwardedMoviesFromProducer(producer);

			IntervalDTO minIntervalForThisProducer = calculateMinDiffBetweenAwards(moviesWithAwards);
			minIntervalForThisProducer.setProducer(producer.getName());

			result.add(minIntervalForThisProducer);
		}

		return getMinimumAwardIntervals(result);
	}

	/**
	 * Returns the producers with the most time between two awards. If there's two
	 * or more producers with the same interval, all of them are returned.
	 * 
	 * @param producers producers to be filtered
	 * @return returns the list with only the producers with longest intervals
	 *         found.
	 * 
	 */
	@Override
	public List<IntervalDTO> getLongestAwardIntervalsByProducers(List<Producer> producers) {

		List<IntervalDTO> result = new ArrayList<>();

		for (Producer producer : producers) {
			List<Movie> moviesWithAwards = movieService.getOnlyAwardedMoviesFromProducer(producer);

			IntervalDTO maxIntervalForThisProducer = calculateMaxDiffBetweenAwards(moviesWithAwards);
			maxIntervalForThisProducer.setProducer(producer.getName());

			result.add(maxIntervalForThisProducer);
		}

		return getMaximumAwardIntervals(result);
	}

	/**
	 * Gets the smallest interval between the awarded movies informed.
	 * 
	 * @param moviesWithAwards movies, ordered ascendently by year.
	 * @return The smallest interval found.
	 */
	private IntervalDTO calculateMinDiffBetweenAwards(List<Movie> moviesWithAwards) {
		IntervalDTO result = new IntervalDTO();
		int smallerDiff = Integer.MAX_VALUE;

		int minDiff = moviesWithAwards.get(1).getYear() - moviesWithAwards.get(0).getYear();

		for (int i = 1; i < moviesWithAwards.size(); i++) {

			Movie next = moviesWithAwards.get(i);
			Movie previous = moviesWithAwards.get(i - 1);

			minDiff = Math.abs(Math.min(minDiff, next.getYear() - previous.getYear()));
			if (smallerDiff > minDiff) {
				result.setFollowingWin(next.getYear());
				result.setPreviousWin(previous.getYear());
				result.setInterval(minDiff);
				smallerDiff = minDiff;
			}
		}
		return result;
	}

	/**
	 * Gets the longest interval between the awarded movies informed.
	 * 
	 * @param moviesWithAwards movies, ordered ascendently by year.
	 * @return The longest interval found.
	 */
	private IntervalDTO calculateMaxDiffBetweenAwards(List<Movie> moviesWithAwards) {
		IntervalDTO result = new IntervalDTO();
		int longestDiff = Integer.MIN_VALUE;

		int maxDiff = moviesWithAwards.get(1).getYear() - moviesWithAwards.get(0).getYear();

		for (int i = 1; i < moviesWithAwards.size(); i++) {

			Movie next = moviesWithAwards.get(i);
			Movie previous = moviesWithAwards.get(i - 1);

			maxDiff = Math.abs(Math.max(maxDiff, next.getYear() - previous.getYear()));
			if (longestDiff < maxDiff) {
				result.setFollowingWin(next.getYear());
				result.setPreviousWin(previous.getYear());
				result.setInterval(maxDiff);
				longestDiff = maxDiff;
			}
		}
		return result;
	}

	/**
	 * Given a list of intervals, this method filters the elements with the smallest
	 * intervals within this list.
	 * 
	 * @param intervals Intervals to be filtered.
	 * @return The list filtered.
	 */
	private List<IntervalDTO> getMinimumAwardIntervals(List<IntervalDTO> intervals) {

		IntervalDTO minIntervalFound = Collections.min(intervals, Comparator.comparing(IntervalDTO::getInterval));

		List<IntervalDTO> intervalsToBeRemoved = new ArrayList<>();

		for (int i = 0; i < intervals.size(); i++) {
			if (intervals.get(i).getInterval() > minIntervalFound.getInterval()) {
				intervalsToBeRemoved.add(intervals.get(i));
			}
		}

		intervals.removeAll(intervalsToBeRemoved);

		return intervals;

	}

	/**
	 * Given a list of intervals, this method filters the elements with the longest
	 * intervals within this list.
	 * 
	 * @param intervals Intervals to be filtered.
	 * @return The list filtered.
	 */
	private List<IntervalDTO> getMaximumAwardIntervals(List<IntervalDTO> intervals) {

		IntervalDTO maxIntervalFound = Collections.max(intervals, Comparator.comparing(IntervalDTO::getInterval));

		List<IntervalDTO> intervalsToBeRemoved = new ArrayList<>();

		for (int i = 0; i < intervals.size(); i++) {
			if (intervals.get(i).getInterval() < maxIntervalFound.getInterval()) {
				intervalsToBeRemoved.add(intervals.get(i));
			}
		}

		intervals.removeAll(intervalsToBeRemoved);

		return intervals;

	}

}
