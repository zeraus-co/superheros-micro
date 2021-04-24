package com.w2m.zeraus.supher.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Company Entity
 * 
 * @author employee zerausCo
 *
 */
@Entity
@Table(name = "COMPANIES")
public class Company implements Serializable {

	/** Attribute representing serialVersionUID */
	private static final long serialVersionUID = 921072355992924929L;

	/** Attribute representing id */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/** Attribute representing name */
	@Column(name = "NAME", length = 25)
	private String name;

	/**
	 * Method for get value of field id
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Method for assing value at field id
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method for get value of field name
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method for assing value at field name
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
