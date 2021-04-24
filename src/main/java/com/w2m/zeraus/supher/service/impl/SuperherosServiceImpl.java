package com.w2m.zeraus.supher.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.w2m.zeraus.supher.model.Superhero;
import com.w2m.zeraus.supher.service.SuperherosService;

@Service
public class SuperherosServiceImpl implements SuperherosService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuperherosServiceImpl.class);

	@Override
	public List<Superhero> findAll() {
		LOGGER.trace("START - findAll");

		List<Superhero> supherList = new ArrayList<>();

		Superhero supher1 = new Superhero(1L, "Spiderman", "M");
		supherList.add(supher1);

		Superhero supher2 = new Superhero(2L, "Capitana Marvel", "F");
		supherList.add(supher2);

		Superhero supher3 = new Superhero(3L, "Aquaman", "M");
		supherList.add(supher3);

		Superhero supher4 = new Superhero(4L, "Batgirl", "F");
		supherList.add(supher4);

		LOGGER.trace("END - findAll");
		return supherList;
	}

	@Override
	public Superhero findById(Long id) {
		LOGGER.trace("START - findById");

		Superhero response = new Superhero(3L, "Aquaman", "M");

		LOGGER.trace("END - findById");
		return response;
	}

	@Override
	public List<Superhero> findByName(String name) {
		LOGGER.trace("START - findByName");

		List<Superhero> supherList = new ArrayList<>();

		Superhero supher = new Superhero(2L, "Capitana Marvel", "F");
		supherList.add(supher);

		LOGGER.trace("END - findByName");
		return supherList;
	}

	@Override
	public void update(Superhero supher) {
		LOGGER.trace("START - update");

		// TODO Auto-generated method stub

		LOGGER.trace("END - update");
	}

	@Override
	public void deleteById(Long id) {
		LOGGER.trace("START - deleteById");

		// TODO Auto-generated method stub

		LOGGER.trace("END - deleteById");
	}

}
