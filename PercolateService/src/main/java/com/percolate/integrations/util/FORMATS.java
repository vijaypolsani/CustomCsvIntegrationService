package com.percolate.integrations.util;

/**
 * Created by vijay.polsani on 9/26/15.
 */
public enum FORMATS {
	FIVE_FIELDS(5), FOUR_FIELDS(4), ILL_FORMED(-1);

	private int field;

	private FORMATS(int field) {
		this.field = field;
	}

	public static FORMATS getCurrentFormat(int field) {
		if (field == 5)
			return FIVE_FIELDS;
		else if (field == 4)
			return FOUR_FIELDS;
		else return ILL_FORMED;
	}

	private int getvalue() {
		return field;
	}
}
