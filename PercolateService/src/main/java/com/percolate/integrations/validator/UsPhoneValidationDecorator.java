package com.percolate.integrations.validator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by vijay.polsani on 9/26/15.
 */
@Slf4j
public class UsPhoneValidationDecorator extends PhoneValidationDecorator {
	private final static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

	public UsPhoneValidationDecorator(PhoneValidation decoratedPhoneValidation) {
		super(decoratedPhoneValidation);
	}

	@Override
	public boolean isPhoneNumberValid(String phoneNumber) {
		if (decoratedPhoneValidation.isPhoneNumberValid(phoneNumber))
			return strictGooglePhoneValid(phoneNumber);
		else return false;
	}

	private boolean strictGooglePhoneValid(String phoneNumber) {
		try {
			Phonenumber.PhoneNumber usPhoneNumber = phoneUtil.parse(phoneNumber, "US");
			return phoneUtil.isValidNumber(usPhoneNumber);
		} catch (NumberParseException e) {
			log.error("NumberParseException was thrown: " + e.toString());
			return false;
		}
	}
}
