package com.w2m.zeraus.supher.persistence.utils;

import org.springframework.data.jpa.domain.Specification;

/**
 * 
 * SpecificationsUtils Class
 * 
 * @author employee zerausCo
 *
 */
public final class SpecificationsUtils {

	/**
	 * 
	 * Method for find a name with like sentence
	 * 
	 * @param field field of db
	 * @param value value of field
	 * 
	 * @return specification object
	 */
	public static Specification containsLike(String field, String value) {
		return (root, query, cb) -> cb.like(root.get(field), "%" + value + "%");
	}

}
