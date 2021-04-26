package com.w2m.zeraus.supher.service.exceptions;

public class SuperheroNotFoundException extends ServiceException {

	private static final long serialVersionUID = -1604449351342693699L;
	private static final String NOT_FOUND_ERROR = "superhero_not_found";
	private static final String NOT_FOUND_MESSAGE = "Not found superhero";

	public SuperheroNotFoundException() {
		super(SuperheroNotFoundException.NOT_FOUND_ERROR, NOT_FOUND_MESSAGE);
	}

}
