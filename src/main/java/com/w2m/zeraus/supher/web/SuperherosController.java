package com.w2m.zeraus.supher.web;

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
import com.w2m.zeraus.supher.web.mapper.SuperherosControllerMapper;
import com.w2m.zeraus.supher.web.model.SuperheroTO;

@RestController
public class SuperherosController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuperherosController.class);

	@Autowired
	private SuperherosService superherosService;
	
	@Autowired
	private SuperherosControllerMapper superherosControllerMapper;

	@GetMapping(value = "/superheros", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SuperheroTO> findAll() {
		LOGGER.info("START - findAll");

		List<SuperheroTO> response = superherosControllerMapper.transformToTO(superherosService.findAll());

		LOGGER.info("END - findAll");
		return response;
	}

	@GetMapping(path = "/superhero/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SuperheroTO findById(@PathVariable("id") Long id) {
		LOGGER.info("START - findById");

		SuperheroTO response = superherosControllerMapper.transformToTO(superherosService.findById(id));

		LOGGER.info("END - findById");
		return response;
	}

	@GetMapping(path = "/superhero", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SuperheroTO> findByName(@RequestParam("name") String name) {
		LOGGER.info("START - findByName");

		List<SuperheroTO> response = superherosControllerMapper.transformToTO(superherosService.findByName(name));

		LOGGER.info("END - findByName");
		return response;
	}

	@PutMapping(value = "/superhero", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody SuperheroTO supher) {
		LOGGER.info("START - update");

		superherosService.update(superherosControllerMapper.transformToVO(supher));

		LOGGER.info("END - update");
	}

	@DeleteMapping(value = "/superhero/{id}")
	public void deleteById(@PathVariable(value = "id") Long id) {
		LOGGER.info("START - deleteById");

		superherosService.deleteById(id);

		LOGGER.info("END - deleteById");
	}

}