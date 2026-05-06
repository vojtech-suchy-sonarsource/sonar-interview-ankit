package com.sonarsource.cinema;

import java.util.logging.Logger;

public class Main {
  private static final Logger logger = Logger.getLogger(Main.class.getName());

  public static void main(String[] args) {
    logger.info("Welcome to SonarSource Cinema!");
    printMovies();
  }

  public static void printMovies() {
    CsvParser csvParser = new CsvParser();
    csvParser.parseCsv("https://raw.githubusercontent.com/aurelien-poscia-sonarsource/sonar-cinema/refs/heads/main/src/main/resources/food.csv");
  }
}
