package com.percolate.integrations.validator;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by vijay.polsani on 9/26/15.
 */
@Slf4j
public class GooglePhoneValidation implements PhoneValidation {
	private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
	@Override
	public boolean isPhoneNumberValid(String phoneNumber) {
		return phoneUtil.isPossibleNumber(new Phonenumber.PhoneNumber().setRawInput(phoneNumber));
	}

}
