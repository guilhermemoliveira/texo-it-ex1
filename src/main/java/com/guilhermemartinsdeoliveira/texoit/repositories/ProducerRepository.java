package com.guilhermemartinsdeoliveira.texoit.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guilhermemartinsdeoliveira.texoit.model.entities.Producer;

@Repository
public class ProducerRepository {

	private List<Producer> producerDatabase;

	@Autowired
	public ProducerRepository(List<Producer> producersDatabase) {
		this.producerDatabase = producersDatabase;
	}
	
	public List<Producer> getAllProducers() {
		return producerDatabase;
	}
}
