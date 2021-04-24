package com.w2m.zeraus.supher.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.w2m.zeraus.supher.entity.Superhero;

public interface SuperherosDao extends JpaRepository<Superhero, Long> {

	@Query("SELECT sh FROM Superhero sh WHERE lower(name) LIKE lower(concat('%',?1,'%'))")
	List<Superhero> findByName(String name);

}
