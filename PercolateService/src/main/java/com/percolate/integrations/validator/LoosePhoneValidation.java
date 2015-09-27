package com.percolate.integrations.validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by vijay.polsani on 9/26/15.
 */
public class LoosePhoneValidation implements PhoneValidation {
	@Override
	public boolean isPhoneNumberValid(String phoneNumber) {
		//validate phone numbers of format "1234567890"
		if (phoneNumber.matches("\\d{10}")) return true;
			//validating phone number with -, . or spaces
		else if (phoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
			//validating phone number where area code is in braces ()
		else if (phoneNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
			//return false if nothing matches the input
		else return false;
	}
}
