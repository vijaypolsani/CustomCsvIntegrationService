package com.percolate.integrations;

import static junit.framework.TestCase.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.percolate.integrations.exception.PercolateServiceException;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by vijay.polsani on 9/26/15.
 */
@Slf4j
public class TestPercolateService {
	private static final String GOOD_SAMPLE1 = "Booker T., Washington, 87360, 373 781 7380, yellow";
	private static final String GOOD_SAMPLE2 = "Lastname, Firstname, (703)-742-0996, Blue, 10013";
	private static final String GOOD_SAMPLE3 = "James Murphy, yellow, 83880, 018 154 6474";
	private static final String BAD_SAMPLE_INVALID_FIELDS = "asasdafsdfsdrfwe";
	private static final String BAD_SAMPLE_PHONE = "Lastname, Firstname, (703)-742-099, Blue, 10013";
	private static final String BAD_SAMPLE_ZIP = "Chandler, Kerri, (623)-668-9293, pink, 123123121";
	private static final String INPUT_FILE_NAME = "/Users/vijay.polsani/_intellij/percolate/PercolateService/src/main/resources/input/data.in";
	private static final String OUTPUT_FILE_NAME = "/Users/vijay.polsani/_intellij/percolate/PercolateService/src/main/resources/output/data.json";
	private static PercolateService percolateService = null;

	@Before
	public void setup() {
		percolateService = new PercolateService();
		String jsonOutput = null;
		try {
			jsonOutput = percolateService.getFormattedJson(INPUT_FILE_NAME, OUTPUT_FILE_NAME);
		} catch (PercolateServiceException e) {
			e.printStackTrace();
		}
		log.info("JsonOutput: " + jsonOutput);
		assertNotNull(jsonOutput);
	}

	@Test
	public void getCleanedJson() {

	}

	@After
	public void cleanup() {
		percolateService = null;
	}


}
