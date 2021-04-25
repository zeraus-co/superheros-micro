package com.w2m.zeraus.supher.exceptions;

public class SuperheroException extends Exception {

	/** Attribute representing serialVersionUID */
	private static final long serialVersionUID = 244689111664438135L;

	public SuperheroException(SuperheroErrors error) {
		super(error.getError().getErrorCode() + ": " + error.getError().getErrorDescription());
	}

}
