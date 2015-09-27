package com.percolate.integrations.exception;

/**
 * Created by vijay.polsani on 9/25/15.
 */
public class PercolateServiceInputDataException extends PercolateServiceException {

	public PercolateServiceInputDataException(String message, String errorCode) {
		super(message, errorCode);
	}
}
