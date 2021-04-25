package com.w2m.zeraus.supher.service.exceptions;

public abstract class ServiceException extends Exception {

	/** Attribute representing serialVersionUID */
	private static final long serialVersionUID = 5815212789801734685L;

	private final long timestamp;

	private final String error;

	ServiceException(String error, String message) {

		super(message);

		this.timestamp = System.currentTimeMillis();
		this.error = error;

	}

	/**
	 * Method for get value of field timestamp
	 *
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Method for get value of field error
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}

}
