package com.w2m.zeraus.supher.service;

import java.util.List;

import com.w2m.zeraus.supher.service.model.SuperheroVO;

public interface SuperherosService {

	public List<SuperheroVO> findAll();

	public SuperheroVO findById(Long id);

	public List<SuperheroVO> findByName(String name);

	public void update(SuperheroVO supher);

	public void deleteById(Long id);

}
