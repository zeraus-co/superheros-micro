package com.w2m.zeraus.supher.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * Superhero Entity
 * 
 * @author employee zerausCo
 *
 */
@Entity
@Table(name = "SUPERHEROS")
public class Superhero implements Serializable {

	/** Attribute representing serialVersionUID */
	private static final long serialVersionUID = 5770204785255112677L;

	/** Attribute representing id */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/** Attribute representing name */
	@Column(name = "NAME", length = 50)
	private String name;

	/** Attribute representing gender */
	@Column(name = "GENDER", length = 1)
	private String gender;

	/** Attribute representing gender */
	@ManyToOne
	@JoinColumn(name = "COMPANY_ID")
	private Company company;

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

	/**
	 * Method for get value of field gender
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Method for assing value at field gender
	 * 
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Method for get value of field company
	 *
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Method for assing value at field company
	 * 
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

}
