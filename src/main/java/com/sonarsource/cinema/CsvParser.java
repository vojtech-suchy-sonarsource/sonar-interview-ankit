package com.sonarsource.cinema;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvParser {

  private static final Logger logger = Logger.getLogger(CsvParser.class.getName());

  public Object parseCsv(String csvUrl) {
    InputStreamReader streamReader = getStreamReader(csvUrl);
    try (CSVReader csvReader = new CSVReaderBuilder(streamReader).withSkipLines(1).withCSVParser(getCsvParser()).build()) {
      String[] values;
      while ((values = csvReader.readNext()) != null) {
        String[] currentValues = values;
        logger.log(Level.INFO, () -> Arrays.toString(currentValues));
      }
      return "Parsing completed successfully";
    } catch (IOException | CsvValidationException e) {
      throw new CsvParsingException("Failed to parse CSV", e);
    }
  }

  private static InputStreamReader getStreamReader(String csvUrl)  {
    try {
      return new InputStreamReader(new URL(csvUrl).openStream(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new CsvParsingException("Failed to open CSV stream from URL: " + csvUrl, e);
    }
  }

  private static CSVParser getCsvParser() {
    return new CSVParserBuilder()
      .withSeparator(';')
      .withIgnoreQuotations(true)
      .build();
  }

}
