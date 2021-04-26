package com.w2m.zeraus.supher.service;

import org.springframework.data.domain.Page;

import com.w2m.zeraus.supher.service.exceptions.SuperheroNotFoundException;
import com.w2m.zeraus.supher.service.model.SuperheroVO;

/**
 * 
 * SuperherosService Interface
 * 
 * @author employee zerausCo
 *
 */
public interface SuperherosService {

	/**
	 * 
	 * Method for find all the superheros
	 * 
	 * @param pageNumber number of page
	 * @param pageSize   size of page
	 * 
	 * @return search result
	 */
	public Page<SuperheroVO> findAll(Short pageNumber, Short pageSize);

	/**
	 * 
	 * Method for find superhero by id
	 * 
	 * @param id id of the superhero
	 * @return superhero data
	 * 
	 * @throws SuperheroNotFoundException exception of type {@link SuperheroNotFoundException}
	 */
	public SuperheroVO findById(Long id) throws SuperheroNotFoundException;

	/**
	 * 
	 * Method for find all the superheroes by the name or a part of the name.
	 * 
	 * @param name       value of name
	 * @param pageNumber number of page
	 * @param pageSize   size of page
	 * 
	 * @return search result
	 */
	public Page<SuperheroVO> findByName(String name, Short pageNumber, Short pageSize);

	/**
	 * 
	 * Method for update the superhero
	 * 
	 * @param superheroVO value of superhero
	 * 
	 * @throws SuperheroNotFoundException exception of type {@link SuperheroNotFoundException}
	 */
	public void update(SuperheroVO superheroVO) throws SuperheroNotFoundException;

	/**
	 * 
	 * Method for delete the superhero by id
	 * 
	 * @param id value of superhero
	 * 
	 */
	public void deleteById(Long id);

}
