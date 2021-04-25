package com.w2m.zeraus.supher.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.w2m.zeraus.supher.exceptions.SuperheroErrors;
import com.w2m.zeraus.supher.exceptions.SuperheroException;
import com.w2m.zeraus.supher.service.SuperherosService;
import com.w2m.zeraus.supher.service.exceptions.SuperheroNotFoundException;
import com.w2m.zeraus.supher.service.model.SuperheroVO;
import com.w2m.zeraus.supher.utils.Duration;
import com.w2m.zeraus.supher.web.mapper.SuperherosControllerMapper;
import com.w2m.zeraus.supher.web.model.SuperheroTO;

/**
 * 
 * SuperherosController
 * 
 * @author employee zerausCo
 *
 */
@RestController
public class SuperherosController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuperherosController.class);

	private static final String SUPERHERO_EXCEPTION = "Superhero Exception";

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private SuperherosService superherosService;

	@Autowired
	private SuperherosControllerMapper superherosControllerMapper;

	/**
	 * 
	 * Method to find the superheroes. It has pagination and, by default, the size
	 * of each page is 3 records.
	 * 
	 * @param pageNumber number of page.
	 * @param pageSize   size of page
	 * 
	 * @return map of the result
	 * 
	 * @throws SuperheroException exception of type {@link SuperheroException}
	 */
	@Duration
	@Cacheable(cacheNames = "suphercache", condition = "#pageNumber == 0")
	@GetMapping(value = "/superheros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> findAll(@RequestParam(defaultValue = "0") Short pageNumber,
			@RequestParam(defaultValue = "3") Short pageSize) throws SuperheroException {
		LOGGER.info("START - findAll");

		Page<SuperheroVO> result;
		List<SuperheroTO> supherList;
		Map<String, Object> response = new HashMap<>();

		try {
			result = superherosService.findAll(pageNumber, pageSize);

			supherList = superherosControllerMapper.transformToTO(result.getContent());

			response.put("supherList", supherList);
			response.put("currentPage", result.getNumber());
			response.put("totalPages", result.getTotalPages());
			response.put("totalElements", result.getTotalElements());

		} catch (Exception e) {
			LOGGER.error(SUPERHERO_EXCEPTION, e);
			throw new SuperheroException(SuperheroErrors.ERROR_CONTROLLER);
		}

		LOGGER.info("END - findAll");
		return new ResponseEntity<>(response,
				(supherList == null || supherList.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	/**
	 * Method for find superhero by id.
	 * 
	 * @param id id of the superhero
	 * @return superhero data
	 * 
	 * @throws SuperheroException exception of type {@link SuperheroException}
	 */
	@Duration
	@GetMapping(path = "/superhero/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SuperheroTO findById(@PathVariable("id") Long id) throws SuperheroException {
		LOGGER.info("START - findById");

		SuperheroTO response = null;

		try {

			response = superherosControllerMapper.transformToTO(superherosService.findById(id));

		} catch (SuperheroNotFoundException e) {
			LOGGER.error(SUPERHERO_EXCEPTION, e);
			throw new SuperheroException(SuperheroErrors.ERROR_NOT_FOUND);

		} catch (Exception e) {
			LOGGER.error(SUPERHERO_EXCEPTION, e);
			throw new SuperheroException(SuperheroErrors.ERROR_CONTROLLER);
		}

		LOGGER.info("END - findById");
		return response;

	}

	/**
	 * 
	 * Method for find all the superheroes by the name or a part of the name. It has
	 * pagination and, by default, the size of each page is 3 records.
	 * 
	 * @param name       value of name
	 * @param pageNumber number of page.
	 * @param pageSize   size of page
	 * 
	 * @return map of the result
	 * 
	 * @throws SuperheroException exception of type {@link SuperheroException}
	 */
	@Duration
	@GetMapping(path = "/superhero", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> findByName(@RequestParam("name") String name,
			@RequestParam(defaultValue = "0") Short pageNumber, @RequestParam(defaultValue = "3") Short pageSize)
			throws SuperheroException {
		LOGGER.info("START - findByName");

		Page<SuperheroVO> result;
		List<SuperheroTO> supherList;
		Map<String, Object> response = new HashMap<>();

		try {

			result = superherosService.findByName(name, pageNumber, pageSize);

			supherList = superherosControllerMapper.transformToTO(result.getContent());

			response = new HashMap<>();
			response.put("supherList", supherList);
			response.put("currentPage", result.getNumber());
			response.put("totalPages", result.getTotalPages());
			response.put("totalElements", result.getTotalElements());

		} catch (Exception e) {
			LOGGER.error(SUPERHERO_EXCEPTION, e);
			throw new SuperheroException(SuperheroErrors.ERROR_CONTROLLER);
		}

		LOGGER.info("END - findByName");
		return new ResponseEntity<>(response,
				(supherList == null || supherList.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	/**
	 * 
	 * Method for update the superhero
	 * 
	 * @param superheroTO
	 * 
	 * @throws SuperheroException exception of type {@link SuperheroException}
	 */
	@Duration
	@PutMapping(value = "/superhero", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody @Valid SuperheroTO superheroTO) throws SuperheroException {
		LOGGER.info("START - update");

		try {

			clearCache();
			superherosService.update(superherosControllerMapper.transformToVO(superheroTO));

		} catch (SuperheroNotFoundException e) {
			LOGGER.error(SUPERHERO_EXCEPTION, e);
			throw new SuperheroException(SuperheroErrors.ERROR_NOT_FOUND);

		} catch (Exception e) {
			LOGGER.error(SUPERHERO_EXCEPTION, e);
			throw new SuperheroException(SuperheroErrors.ERROR_CONTROLLER);
		}

		LOGGER.info("END - update");
	}

	/**
	 * 
	 * Method for delete the superhero by id
	 * 
	 * @param id value of superhero
	 * 
	 * @throws SuperheroException exception of type {@link SuperheroException}
	 */
	@Duration
	@DeleteMapping(value = "/superhero/{id}")
	public void deleteById(@PathVariable(value = "id") Long id) throws SuperheroException {
		LOGGER.info("START - deleteById");

		try {

			clearCache();
			superherosService.deleteById(id);

		} catch (Exception e) {
			LOGGER.error(SUPERHERO_EXCEPTION, e);
			throw new SuperheroException(SuperheroErrors.ERROR_CONTROLLER);
		}

		LOGGER.info("END - deleteById");
	}

	/**
	 * Method for clear the cache
	 */
	private void clearCache() {
		for (String name : cacheManager.getCacheNames()) {
			cacheManager.getCache(name).clear();
		}
	}

}
