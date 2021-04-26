package com.w2m.zeraus.supher.web.model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Valid
public class SuperheroTO implements Serializable {

	/** Attribute representing serialVersionUID */
	private static final long serialVersionUID = 984656584348664090L;

	@NotNull(message = "ID is required")
	private Long id;

	@NotNull(message = "Name is required")
	@Length(max = 25, message = "The name must have maximum to 25 characters")
	private String name;

	@NotNull(message = "Gender is required")
	@Pattern(regexp = "[M|F]", message = "The value must be M (Male) or F (Female)")
	private String gender;

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
