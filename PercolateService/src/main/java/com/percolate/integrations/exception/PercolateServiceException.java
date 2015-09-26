package com.percolate.integrations.exception;

/**
 * Created by vijay.polsani on 9/25/15.
 */
public class PercolateServiceException extends Exception {
	private String errorCode = "Percolate_Exception";

	public PercolateServiceException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}
}
