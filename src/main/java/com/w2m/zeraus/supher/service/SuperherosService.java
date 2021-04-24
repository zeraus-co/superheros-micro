package com.w2m.zeraus.supher.service;

import java.util.List;

import com.w2m.zeraus.supher.model.Superhero;

public interface SuperherosService {

	public List<Superhero> findAll();

	public Superhero findById(Long id);

	public List<Superhero> findByName(String name);

	public void update(Superhero supher);

	public void deleteById(Long id);

}
