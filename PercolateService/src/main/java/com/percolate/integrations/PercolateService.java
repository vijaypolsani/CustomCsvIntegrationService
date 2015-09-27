package com.percolate.integrations;

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

import com.percolate.integrations.exception.PercolateServiceException;
import com.percolate.integrations.exception.PercolateServiceInputDataException;
import com.percolate.integrations.parser.StringRowListProcessor;
import com.percolate.integrations.util.FileHelper;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by vijay.polsani on 9/25/15.
 */
@Slf4j
@Path("/percolate")
public class PercolateService implements PercolateFileToJson {

	private final CsvParserSettings csvParserSettings = new CsvParserSettings();
	private final StringRowListProcessor stringRowListProcessor = new StringRowListProcessor();
	private final ObjectMapper objectMapper = JsonFactory.create();
	private final CsvParser csvParser;

	public PercolateService() {
		csvParserSettings.setLineSeparatorDetectionEnabled(true);
		csvParserSettings.setHeaderExtractionEnabled(false);
		csvParserSettings.setNullValue("NA");
		csvParserSettings.setRowProcessor(stringRowListProcessor);
		csvParser = new CsvParser(csvParserSettings);
	}

	@Override
	@POST
	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	public String getJson(String rawData) throws PercolateServiceException {
		log.info("Input data: " + rawData);
		csvParser.parse(new StringReader(rawData));
		while (stringRowListProcessor.getJsonOutput().peek() != null)
			return (objectMapper.writeValueAsString(stringRowListProcessor.getJsonOutput().poll()));
		return "";
	}

	@Override
	public String getFormattedJson(String inputRawData, String outDataFile) throws PercolateServiceException {
		log.info("Input File location: " + inputRawData);
		log.info("Output File location: " + outDataFile);
		try {
			csvParser.parse(FileHelper.getReader(inputRawData));
		} catch (PercolateServiceInputDataException e) {
			e.printStackTrace();
			throw new PercolateServiceException(e.getLocalizedMessage(), "1000");
		}
		String jsonData = null;
		while (stringRowListProcessor.getJsonOutput().peek() != null)
			jsonData = (objectMapper.writeValueAsString(stringRowListProcessor.getJsonOutput().poll()));
		try {
			Writer writer = FileHelper.getWriter(outDataFile);
			writer.write(jsonData);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("JSON Write Exception");
			throw new PercolateServiceException("Sorry no way out. Please contact helpdesk!", "0000");
		}
		return jsonData;
	}

	public static void main(String args[]) {
		log.info("Staring Server.");
		PercolateFileToJson percolateService = new PercolateService();
		try {
			percolateService.getFormattedJson("", "");
		} catch (PercolateServiceException e) {
			e.printStackTrace();
			log.error("Bootup Exception." + e.getLocalizedMessage());
		}
	}

}
