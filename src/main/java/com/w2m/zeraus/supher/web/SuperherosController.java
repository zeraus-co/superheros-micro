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

import com.w2m.zeraus.supher.service.SuperherosService;
import com.w2m.zeraus.supher.service.model.SuperheroVO;
import com.w2m.zeraus.supher.utils.Duration;
import com.w2m.zeraus.supher.web.mapper.SuperherosControllerMapper;
import com.w2m.zeraus.supher.web.model.SuperheroTO;

@RestController
public class SuperherosController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuperherosController.class);

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private SuperherosService superherosService;

	@Autowired
	private SuperherosControllerMapper superherosControllerMapper;

	@Duration
	@Cacheable(cacheNames = "suphercache", condition = "#pageNumber == 0")
	@GetMapping(value = "/superheros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> findAll(@RequestParam(defaultValue = "0") Short pageNumber,
			@RequestParam(defaultValue = "3") Short pageSize) {
		LOGGER.info("START - findAll");

		Page<SuperheroVO> result = superherosService.findAll(pageNumber, pageSize);

		List<SuperheroTO> supherList = superherosControllerMapper.transformToTO(result.getContent());

		Map<String, Object> response = new HashMap<>();
		response.put("supherList", supherList);
		response.put("currentPage", result.getNumber());
		response.put("totalPages", result.getTotalPages());
		response.put("totalElements", result.getTotalElements());

		LOGGER.info("END - findAll");
		return new ResponseEntity<>(response,
				(supherList == null || supherList.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	@Duration
	@GetMapping(path = "/superhero/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SuperheroTO findById(@PathVariable("id") Long id) {
		LOGGER.info("START - findById");

		SuperheroTO response = superherosControllerMapper.transformToTO(superherosService.findById(id));

		LOGGER.info("END - findById");
		return response;
	}

	@Duration
	@GetMapping(path = "/superhero", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> findByName(@RequestParam("name") String name,
			@RequestParam(defaultValue = "0") Short pageNumber, @RequestParam(defaultValue = "3") Short pageSize) {
		LOGGER.info("START - findByName");

		Page<SuperheroVO> result = superherosService.findByName(name, pageNumber, pageSize);

		List<SuperheroTO> supherList = superherosControllerMapper.transformToTO(result.getContent());

		Map<String, Object> response = new HashMap<>();
		response.put("supherList", supherList);
		response.put("currentPage", result.getNumber());
		response.put("totalPages", result.getTotalPages());
		response.put("totalElements", result.getTotalElements());

		LOGGER.info("END - findByName");
		return new ResponseEntity<>(response,
				(supherList == null || supherList.isEmpty()) ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	@Duration
	@PutMapping(value = "/superhero", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody @Valid SuperheroTO supher) {
		LOGGER.info("START - update");

		clearCache();
		superherosService.update(superherosControllerMapper.transformToVO(supher));

		LOGGER.info("END - update");
	}

	@Duration
	@DeleteMapping(value = "/superhero/{id}")
	public void deleteById(@PathVariable(value = "id") Long id) {
		LOGGER.info("START - deleteById");

		clearCache();
		superherosService.deleteById(id);

		LOGGER.info("END - deleteById");
	}

	private void clearCache() {
		for (String name : cacheManager.getCacheNames()) {
			cacheManager.getCache(name).clear();
		}
	}

}