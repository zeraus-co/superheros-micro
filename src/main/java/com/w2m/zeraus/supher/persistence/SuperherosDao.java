package com.w2m.zeraus.supher.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.w2m.zeraus.supher.entity.Superhero;

public interface SuperherosDao extends JpaRepository<Superhero, Long>, JpaSpecificationExecutor<Superhero> {

}
