package com.percolate.integrations;

import com.percolate.integrations.exception.PercolateServiceException;

/**
 * Created by vijay.polsani on 9/25/15.
 */
public interface PercolateFileToJson {
	public String getJson(String rawData) throws PercolateServiceException;

	public String getFormattedJson(String inputRawData, String outputJsonData) throws PercolateServiceException;
}
