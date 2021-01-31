package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.vo.LineItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Packer {

  private Packer() {
  }

  public static String pack(String filePath) throws APIException {
    System.out.println(System.getProperty("user.dir")+filePath );
    Path path = Paths.get(System.getProperty("user.dir") + filePath );
    try {
      StringBuffer sb = new StringBuffer();
      List<String> lines = Files.readAllLines(path);
      for ( String line : lines ) {
        sb.append(Packer.process(line)).append("\n");
      }
      return sb.toString();
    } catch (IOException e) {
      throw new APIException("could not calculate the output");
    }
  }

  /**
   * this method converts each lilne into a format which can be used by calculator to process the request.
   * @param line line
   * @return result
   * @throws APIException Apiexception
   */
  private static String process(String line) throws APIException {
    InputLineParser inputLineParser = new InputLineParser();
    LineItem lineItem = inputLineParser.parseInput(line);
    PackerCalculator packerCalculator = new PackerCalculator();
    return packerCalculator.calculateBestSolution(lineItem.getMaxWeight(), lineItem.getItems());
  }
}
