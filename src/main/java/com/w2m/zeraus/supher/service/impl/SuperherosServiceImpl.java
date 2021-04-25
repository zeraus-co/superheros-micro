package com.w2m.zeraus.supher.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.w2m.zeraus.supher.entity.Superhero;
import com.w2m.zeraus.supher.persistence.SuperherosDao;
import com.w2m.zeraus.supher.persistence.utils.SpecificationsUtils;
import com.w2m.zeraus.supher.service.SuperherosService;
import com.w2m.zeraus.supher.service.exceptions.SuperheroNotFoundException;
import com.w2m.zeraus.supher.service.mapper.SuperherosServiceMapper;
import com.w2m.zeraus.supher.service.model.SuperheroVO;

/**
 * 
 * SuperherosServiceImpl
 * 
 * @author employee zerausCo
 *
 */
@Service
public class SuperherosServiceImpl implements SuperherosService {

	/** Attribute representing serialVersionUID */
	private static final Logger LOGGER = LoggerFactory.getLogger(SuperherosServiceImpl.class);

	@Autowired
	private SuperherosDao superherosDao;

	@Autowired
	private SuperherosServiceMapper superherosServiceMapper;

	@Override
	public Page<SuperheroVO> findAll(Short pageNumber, Short pageSize) {
		LOGGER.info("START - findAll");

		// Call to superherosDao.findAll method and transform the result to VO
		Page<SuperheroVO> response = superherosServiceMapper
				.transformPageToVO(superherosDao.findAll(PageRequest.of(pageNumber.intValue(), pageSize.intValue())));

		LOGGER.info("END - findAll");
		return response;
	}

	@Override
	public SuperheroVO findById(Long id) throws SuperheroNotFoundException {
		LOGGER.info("START - findById");

		// Call to superherosDao.findById method
		Superhero superhero = superherosDao.findById(id).orElseThrow(SuperheroNotFoundException::new);

		// Transform the result to VO
		SuperheroVO response = superherosServiceMapper.transformToVO(superhero);

		LOGGER.info("END - findById");
		return response;
	}

	@Override
	public Page<SuperheroVO> findByName(String nameValue, Short pageNumber, Short pageSize) {
		LOGGER.info("START - findByName");

		// Filter specification
		Specification<Superhero> filter = SpecificationsUtils.containsLike("name", nameValue);

		// Call to superherosDao.findAll method and transform the result to VO
		Page<SuperheroVO> response = superherosServiceMapper.transformPageToVO(
				superherosDao.findAll(filter, PageRequest.of(pageNumber.intValue(), pageSize.intValue())));

		LOGGER.info("END - findByName");
		return response;
	}

	@Override
	public void update(SuperheroVO superheroVO) throws SuperheroNotFoundException {
		LOGGER.info("START - update");

		// Call to superherosDao.findById method
		Superhero superhero = superherosDao.findById(superheroVO.getId()).orElseThrow(SuperheroNotFoundException::new);

		// Transform the result to entity for update the superhero
		Superhero supher = null;
		supher = superherosServiceMapper.transformToEntity(superheroVO);
		supher.setCompany(superhero.getCompany());

		superherosDao.save(supher);

		LOGGER.info("END - update");
	}

	@Override
	public void deleteById(Long id) {
		LOGGER.info("START - deleteById");

		// Call to superherosDao.deleteById method
		superherosDao.deleteById(id);

		LOGGER.info("END - deleteById");
	}

}
