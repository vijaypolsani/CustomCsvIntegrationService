package com.percolate.integrations.parser;

import static com.percolate.integrations.util.FORMATS.getCurrentFormat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.percolate.integrations.model.Output;
import com.percolate.integrations.model.User;
import com.percolate.integrations.util.StringNumericOrNot;
import com.percolate.integrations.util.ZipCodeVerifier;
import com.percolate.integrations.validator.LoosePhoneValidation;
import com.percolate.integrations.validator.PhoneValidation;
import com.percolate.integrations.validator.UsPhoneValidationDecorator;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.AbstractRowProcessor;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by vijay.polsani on 9/26/15.
 */
@Slf4j
public class StringRowListProcessor extends AbstractRowProcessor {
	private static final int FOUR = 4;
	private static final int THREE = 3;
	private static final int TWO = 2;
	private static final int ONE = 1;
	private static final int ZERO = 0;
	@Getter
	private final Output output = new Output();
	@Getter
	private final Queue<Output> jsonOutput = new LinkedBlockingQueue<>(1);
	private static final PhoneValidation loosePhoneValidation = new LoosePhoneValidation();

	//Use this for strict validation.
	private static final PhoneValidation usGooglePhoneValidation = new UsPhoneValidationDecorator(new LoosePhoneValidation());


	public void rowProcessed(String[] row, ParsingContext context) {
		//TODO: Phone Number cleansing and validation
		switch (getCurrentFormat(row.length)) {
			case FIVE_FIELDS:
				final User userFive = new User();
				if (StringNumericOrNot.isNumeric(row[FOUR])) {
					log.info("ROW: " + Arrays.toString(row));
					//if (!usGooglePhoneValidation.isPhoneNumberValid(row[TWO]) || !ZipCodeVerifier.isValidZipcode(row[FOUR])) {
					if (!loosePhoneValidation.isPhoneNumberValid(row[TWO]) || !ZipCodeVerifier.isValidZipcode(row[FOUR])) {
						output.getErrors().add(context.currentLine());
						break;
					}
					userFive.setFirstName(row[ONE]);
					userFive.setLastName(row[ZERO]);
					userFive.setPhoneNumber(row[TWO]);
					userFive.setColor(row[THREE]);
					userFive.setZipcode(row[FOUR]);
				} else {
					log.info("ROW: " + Arrays.toString(row));
					//if (!usGooglePhoneValidation.isPhoneNumberValid(row[THREE]) || !ZipCodeVerifier.isValidZipcode(row[TWO])) {
					if (!loosePhoneValidation.isPhoneNumberValid(row[THREE]) || !ZipCodeVerifier.isValidZipcode(row[TWO])) {
						output.getErrors().add(context.currentLine());
						break;
					}
					userFive.setLastName(row[ZERO]);
					userFive.setFirstName(row[ONE]);
					userFive.setZipcode(row[TWO]);
					userFive.setPhoneNumber(row[THREE]);
					userFive.setColor(row[FOUR]);
				}
				output.getEntries().add(userFive);
				break;
			case FOUR_FIELDS:
				final User userFour = new User();
				log.info("ROW: " + Arrays.toString(row));
				//if (!usGooglePhoneValidation.isPhoneNumberValid(row[THREE])  || !ZipCodeVerifier.isValidZipcode(row[TWO])) {
				if (!loosePhoneValidation.isPhoneNumberValid(row[THREE]) || !ZipCodeVerifier.isValidZipcode(row[TWO])) {
					output.getErrors().add(context.currentLine());
					break;
				}
				String[] firstLastNames = row[ZERO].split(" ", TWO);
				userFour.setFirstName(firstLastNames[ZERO]);
				userFour.setLastName(firstLastNames[ONE]);
				userFour.setColor(row[ONE]);
				userFour.setZipcode(row[TWO]);
				userFour.setPhoneNumber(row[THREE]);
				output.getEntries().add(userFour);
				break;
			default:
				output.getErrors().add(context.currentLine());
				break;
		}
	}

	@Override
	public void processEnded(ParsingContext context) {
		Collections.sort(output.getEntries());
		jsonOutput.offer(output);
	}
}
