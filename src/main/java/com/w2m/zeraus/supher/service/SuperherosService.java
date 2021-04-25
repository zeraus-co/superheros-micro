package com.w2m.zeraus.supher.service;

import org.springframework.data.domain.Page;

import com.w2m.zeraus.supher.service.model.SuperheroVO;

public interface SuperherosService {

	public Page<SuperheroVO> findAll(Short pageNumber, Short pageSize);

	public SuperheroVO findById(Long id);

	public Page<SuperheroVO> findByName(String name, Short pageNumber, Short pageSize);

	public void update(SuperheroVO superheroVO);

	public void deleteById(Long id);

}
