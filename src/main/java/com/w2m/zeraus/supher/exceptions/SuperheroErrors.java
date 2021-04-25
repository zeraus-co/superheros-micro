package com.w2m.zeraus.supher.exceptions;

public enum SuperheroErrors {

	ERROR_CONTROLLER(new SuperheroError("W2M_SUPHER ERROR_CONTROLLER:001",
			"An error has been thrown in the application controller")),

	ERROR_NOT_FOUND(new SuperheroError("W2M_SUPHER ERROR_NOT_FOUND:002", "The indicated superhero does not exist"));

	private SuperheroError error;

	SuperheroErrors(SuperheroError error) {
		this.error = error;
	}

	public SuperheroError getError() {
		return error;
	}

}
