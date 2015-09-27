package com.percolate.integrations.util;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import com.percolate.integrations.exception.PercolateServiceInputDataException;

/**
 * Created by vijay.polsani on 9/26/15.
 */
public class FileHelper {
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	private static final Properties properties = new ConfigFileReader().loadProperties();


	public static Reader getReader(String relativePath) throws PercolateServiceInputDataException {
		Path path = Paths.get(relativePath);
		try {
			return Files.newBufferedReader(path, ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PercolateServiceInputDataException(e.getLocalizedMessage(), "4000");
		}
	}

	public static Writer getWriter(String relativePath) throws PercolateServiceInputDataException {
		Path path = Paths.get(relativePath);
		try {
			return Files.newBufferedWriter(path, ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PercolateServiceInputDataException(e.getLocalizedMessage(), "4000");
		}
	}

}
