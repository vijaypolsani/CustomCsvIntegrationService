package com.percolate.integrations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.Properties;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.google.common.collect.Iterables;
import com.percolate.integrations.exception.PercolateServiceConfigLoadingException;
import com.percolate.integrations.model.User;
import com.percolate.integrations.util.ConfigFileReader;
import com.percolate.integrations.exception.PercolateServiceException;
import com.google.common.collect.Iterators;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by vijay.polsani on 9/25/15.
 */
@Slf4j
public class PercolateService implements PercolateFileToJson {
	private static final Properties properties = new ConfigFileReader().loadProperties();
	private final static Charset ENCODING = StandardCharsets.UTF_8;

	public PercolateService() {
	}

	public static void main(String args[]) {
		PercolateService percolateService = new PercolateService();
		try {
			percolateService.readTextFileAlternate(properties.getProperty("input_file_location"));
		}catch (IOException  fe){
			fe.printStackTrace();
		}
	}
	public void readTextFileAlternate(String inputFileName) throws IOException {
		Path path = Paths.get(inputFileName);
		try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)){
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				getCleanedJson(line);
			}
		}
	}
	@Override
	public String getCleanedJson(String inputRawData) {
		try (Reader inputReader = new StringReader(inputRawData);) {
			Iterable<CSVRecord> csvRecords = CSVFormat.newFormat(',').parse(inputReader);
			//log.info("Number of records: "+ Iterables.size(csvRecords));

			for (CSVRecord csvRecord : csvRecords) {
				log.info(csvRecord.get(0) + " , " + csvRecord.get(1) + " , " + csvRecord.get(2) + " , " + csvRecord.get(3) );
			}

		} catch (IOException e) {
			log.error("CSV Parsing Error. %s", e.getLocalizedMessage());
			e.printStackTrace();
		}
		final User user = new User();
		return null;
	}
}
