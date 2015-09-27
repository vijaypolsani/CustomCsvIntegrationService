package com.percolate.integrations.validator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by vijay.polsani on 9/26/15.
 */
@Slf4j
public abstract class PhoneValidationDecorator implements PhoneValidation {
	protected final PhoneValidation decoratedPhoneValidation;

	public PhoneValidationDecorator(PhoneValidation decoratedPhoneValidation) {
		this.decoratedPhoneValidation = decoratedPhoneValidation;
	}
	@Override
	public boolean isPhoneNumberValid(String phoneNumber) {
		return decoratedPhoneValidation.isPhoneNumberValid(phoneNumber);
	}
}
