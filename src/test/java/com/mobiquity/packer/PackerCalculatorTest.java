package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.vo.LineItem;
import org.junit.Test;

import static org.junit.Assert.*;

public class PackerCalculatorTest {

    @Test
    public void testCalculateInput1() throws APIException {
        String line = "75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)";
        InputLineParser inputLineParser = new InputLineParser();
        LineItem lineItem = inputLineParser.parseInput(line);
        PackerCalculator packerCalculator = new PackerCalculator();
        String res=  packerCalculator.calculateBestSolution(lineItem.getMaxWeight(), lineItem.getItems());
        assertEquals(res, "2,7");
    }

    @Test
    public void testCalculateInput2() throws APIException {
        String line = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        InputLineParser inputLineParser = new InputLineParser();
        LineItem lineItem = inputLineParser.parseInput(line);
        PackerCalculator packerCalculator = new PackerCalculator();
        String res=  packerCalculator.calculateBestSolution(lineItem.getMaxWeight(), lineItem.getItems());
        assertEquals(res, "4");
    }

    @Test
    public void testCalculateInput3() throws APIException {
        String line = "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";
        InputLineParser inputLineParser = new InputLineParser();
        LineItem lineItem = inputLineParser.parseInput(line);
        PackerCalculator packerCalculator = new PackerCalculator();
        String res=  packerCalculator.calculateBestSolution(lineItem.getMaxWeight(), lineItem.getItems());
        assertEquals(res, "8,9");
    }

    @Test
    public void testCalculateInput4() throws APIException {
        String line = "8 : (1,15.3,€34)";
        InputLineParser inputLineParser = new InputLineParser();
        LineItem lineItem = inputLineParser.parseInput(line);
        PackerCalculator packerCalculator = new PackerCalculator();
        String res=  packerCalculator.calculateBestSolution(lineItem.getMaxWeight(), lineItem.getItems());
        assertEquals(res, "-");
    }

}