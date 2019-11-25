package com.guilhermemartinsdeoliveira.texoit.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CustomFileUtils {

	private CustomFileUtils() {

	}

	/**
	 * Reads content fom a CSV file inside project's resources paths.
	 * 
	 * @param path File location
	 * @param withHeaders Flag that indicates whether the file has a header or not.
	 * @param columnSeparator The character used in columns' separation.
	 * @return A list of map objects, in which each of these maps contains
	 * 
	 * key = column header
	 * value = cell value for that column
	 * 
	 * @throws IOException When any kind of file reading failure happens
	 */
	public static List<Map<Object, Object>> readCsvFromPath(String path, boolean withHeaders, Character columnSeparator)
			throws IOException {
		File file = new File(path);
		CsvSchema schema = CsvSchema.emptySchema();
		if (withHeaders) {
			schema = schema.withHeader();
		}
		if (columnSeparator != null) {
			schema = schema.withColumnSeparator(columnSeparator.charValue());
		}

		CsvMapper csvMapper = new CsvMapper();
		MappingIterator<Map<Object, Object>> mappingIterator = csvMapper.readerFor(Map.class).with(schema)
				.readValues(file);
		return mappingIterator.readAll();

	}

}
