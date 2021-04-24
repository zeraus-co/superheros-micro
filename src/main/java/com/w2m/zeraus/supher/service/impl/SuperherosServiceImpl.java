package com.w2m.zeraus.supher.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.w2m.zeraus.supher.service.SuperherosService;
import com.w2m.zeraus.supher.service.model.SuperheroVO;

@Service
public class SuperherosServiceImpl implements SuperherosService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuperherosServiceImpl.class);

	@Override
	public List<SuperheroVO> findAll() {
		LOGGER.trace("START - findAll");

		List<SuperheroVO> supherList = new ArrayList<>();

		SuperheroVO supher1 = new SuperheroVO(1L, "Spiderman", "M");
		supherList.add(supher1);

		SuperheroVO supher2 = new SuperheroVO(2L, "Capitana Marvel", "F");
		supherList.add(supher2);

		SuperheroVO supher3 = new SuperheroVO(3L, "Aquaman", "M");
		supherList.add(supher3);

		SuperheroVO supher4 = new SuperheroVO(4L, "Batgirl", "F");
		supherList.add(supher4);

		LOGGER.trace("END - findAll");
		return supherList;
	}

	@Override
	public SuperheroVO findById(Long id) {
		LOGGER.trace("START - findById");

		SuperheroVO response = new SuperheroVO(3L, "Aquaman", "M");

		LOGGER.trace("END - findById");
		return response;
	}

	@Override
	public List<SuperheroVO> findByName(String name) {
		LOGGER.trace("START - findByName");

		List<SuperheroVO> supherList = new ArrayList<>();

		SuperheroVO supher = new SuperheroVO(2L, "Capitana Marvel", "F");
		supherList.add(supher);

		LOGGER.trace("END - findByName");
		return supherList;
	}

	@Override
	public void update(SuperheroVO supher) {
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
