package com.w2m.zeraus.supher.model;

import java.io.Serializable;

public class Superhero implements Serializable {

	/** Attribute representing serialVersionUID */
	private static final long serialVersionUID = -4865863289722011632L;

	private Long id;

	private String name;

	private String gender;

	public Superhero() {
		super();
	}

	public Superhero(Long id, String name, String gender) {
		this.id = id;
		this.name = name;
		this.gender = gender;
	}

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

}
