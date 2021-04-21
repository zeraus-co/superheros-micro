package com.w2m.zeraus.supher.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.w2m.zeraus.supher.model.Superhero;

@RestController
public class SuperherosController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuperherosController.class);
	
	@GetMapping(value = "/superheros", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Superhero> findAll() {

		List<Superhero> supherList = new ArrayList<>();

		Superhero supher1 = new Superhero(1L, "Spiderman", "M");
		supherList.add(supher1);

		Superhero supher2 = new Superhero(2L, "Capitana Marvel", "F");
		supherList.add(supher2);

		Superhero supher3 = new Superhero(3L, "Aquaman", "M");
		supherList.add(supher3);

		Superhero supher4 = new Superhero(4L, "Batgirl", "F");
		supherList.add(supher4);

		return supherList;

	}

	@GetMapping(path = "/superhero/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Superhero findById(@PathVariable("id") Long id) {
		return new Superhero(3L, "Aquaman", "M");
	}

	@GetMapping(path = "/superhero", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Superhero> findByName(@RequestParam("name") String name) {
		List<Superhero> supherList = new ArrayList<>();

		Superhero supher = new Superhero(2L, "Capitana Marvel", "F");
		supherList.add(supher);

		return supherList;
	}

	@PutMapping(value = "/superhero", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Superhero supher) {
		LOGGER.info("update");
		// Do nothing
	}

	@DeleteMapping(value = "/superhero/{id}")
	public void deleteById(@PathVariable(value = "id") Long id) {
		LOGGER.info("delete");
		// Do nothing
	}

}