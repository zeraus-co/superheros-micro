package com.w2m.zeraus.supher.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w2m.zeraus.supher.entity.Superhero;
import com.w2m.zeraus.supher.persistence.SuperherosDao;
import com.w2m.zeraus.supher.service.SuperherosService;
import com.w2m.zeraus.supher.service.mapper.SuperherosServiceMapper;
import com.w2m.zeraus.supher.service.model.SuperheroVO;

@Service
public class SuperherosServiceImpl implements SuperherosService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuperherosServiceImpl.class);

	@Autowired
	private SuperherosDao superherosDao;

	@Autowired
	private SuperherosServiceMapper superherosServiceMapper;

	@Override
	public List<SuperheroVO> findAll() {
		LOGGER.info("START - findAll");

		List<SuperheroVO> response = superherosServiceMapper.transformToVO(superherosDao.findAll());

		LOGGER.info("END - findAll");
		return response;
	}

	@Override
	public SuperheroVO findById(Long id) {
		LOGGER.info("START - findById");

		Optional<Superhero> superhero = superherosDao.findById(id);

		SuperheroVO response = null;

		if (superhero.isPresent()) {
			response = superherosServiceMapper.transformToVO(superhero.get());
		}

		LOGGER.info("END - findById");
		return response;
	}

	@Override
	public List<SuperheroVO> findByName(String name) {
		LOGGER.info("START - findByName");

		List<SuperheroVO> response = superherosServiceMapper.transformToVO(superherosDao.findByName(name));

		LOGGER.info("END - findByName");
		return response;
	}

	@Override
	public void update(SuperheroVO supher) {
		LOGGER.info("START - update");

		superherosDao.save(superherosServiceMapper.transformToEntity(supher));

		LOGGER.info("END - update");
	}

	@Override
	public void deleteById(Long id) {
		LOGGER.info("START - deleteById");

		superherosDao.deleteById(id);

		LOGGER.info("END - deleteById");
	}

}
