package com.percolate.integrations.util;

import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by vijay.polsani on 9/26/15.
 */
@Slf4j
public class ZipCodeVerifier {
	private static final String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
	private static final Pattern pattern = Pattern.compile(regex);

	public static boolean isValidZipcode(String zipcode) {
		if (pattern.matcher(zipcode).matches())
			return true;
		return false;
	}
}
