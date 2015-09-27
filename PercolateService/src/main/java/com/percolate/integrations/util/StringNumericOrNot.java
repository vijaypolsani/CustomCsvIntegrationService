package com.percolate.integrations.util;

/**
 * Created by vijay.polsani on 9/26/15.
 */
public class StringNumericOrNot {
	public static boolean isNumeric(final CharSequence cs) {

		if (isEmpty(cs)) {
			return false;
		}
		final int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

}
