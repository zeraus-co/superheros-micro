package com.w2m.zeraus.supher.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.w2m.zeraus.supher.service.SuperherosService;
import com.w2m.zeraus.supher.service.model.SuperheroVO;

@RestController
public class SuperherosController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuperherosController.class);

	@Autowired
	private SuperherosService superherosService;

	@GetMapping(value = "/superheros", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SuperheroVO> findAll() {
		LOGGER.trace("START - findAll");

		List<SuperheroVO> response = superherosService.findAll();

		LOGGER.trace("END - findAll");
		return response;
	}

	@GetMapping(path = "/superhero/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SuperheroVO findById(@PathVariable("id") Long id) {
		LOGGER.trace("START - findById");

		SuperheroVO response = superherosService.findById(id);

		LOGGER.trace("END - findById");
		return response;
	}

	@GetMapping(path = "/superhero", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SuperheroVO> findByName(@RequestParam("name") String name) {
		LOGGER.trace("START - findByName");

		List<SuperheroVO> response = superherosService.findByName(name);

		LOGGER.trace("END - findByName");
		return response;
	}

	@PutMapping(value = "/superhero", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody SuperheroVO supher) {
		LOGGER.trace("START - update");

		superherosService.update(supher);

		LOGGER.trace("END - update");
	}

	@DeleteMapping(value = "/superhero/{id}")
	public void deleteById(@PathVariable(value = "id") Long id) {
		LOGGER.trace("START - deleteById");

		superherosService.deleteById(id);

		LOGGER.trace("END - deleteById");
	}

}