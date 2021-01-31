package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.vo.LineItem;
import org.junit.Assert;
import org.junit.Test;

public class InputLineParserTest {

    @Test(expected = APIException.class)
    public void testParseInput1() throws APIException {
        String line = "81";
        InputLineParser inputLineParser = new InputLineParser();
        inputLineParser.parseInput(line);
    }

    @Test(expected = APIException.class)
    public void testParseInput2() throws APIException {
        String line = "81 : :";
        InputLineParser inputLineParser = new InputLineParser();
        inputLineParser.parseInput(line);
    }

    @Test(expected = APIException.class)
    public void testParseInput3() throws APIException {
        String line = "81 : :";
        InputLineParser inputLineParser = new InputLineParser();
        inputLineParser.parseInput(line);
    }

    @Test(expected = APIException.class)
    public void testParseInput4() throws APIException {
        String line = "81 : ()";
        InputLineParser inputLineParser = new InputLineParser();
        inputLineParser.parseInput(line);
    }

    @Test(expected = APIException.class)
    public void testParseInput5() throws APIException {
        String line = "81 : (()";
        InputLineParser inputLineParser = new InputLineParser();
        inputLineParser.parseInput(line);
    }

    @Test(expected = APIException.class)
    public void testParseInput6() throws APIException {
        String line = "81 : )";
        InputLineParser inputLineParser = new InputLineParser();
        inputLineParser.parseInput(line);
    }

    @Test(expected = APIException.class)
    public void testParseInput7() throws APIException {
        String line = "8 : (1,15.3)";
        InputLineParser inputLineParser = new InputLineParser();
        inputLineParser.parseInput(line);
    }


    @Test(expected = APIException.class)
    public void testParseInput8() throws APIException {
        String line = "8 : (1,15.3,€34,2)";
        InputLineParser inputLineParser = new InputLineParser();
        inputLineParser.parseInput(line);
    }

    @Test()
    public void testParseInput9() throws APIException {
        String line = "8 : (1,15.3,€34)";
        InputLineParser inputLineParser = new InputLineParser();
        LineItem lineItem = inputLineParser.parseInput(line);
        Assert.assertEquals(8, lineItem.getMaxWeight());
        Assert.assertEquals(0, lineItem.getItems().size()); // as value is greater than max
    }

    @Test()
    public void testParseInput10() throws APIException {
        String line = "40 : (1,15.3,€34)";
        InputLineParser inputLineParser = new InputLineParser();
        LineItem lineItem = inputLineParser.parseInput(line);
        Assert.assertEquals(40, lineItem.getMaxWeight());
        Assert.assertEquals(1, lineItem.getItems().size()); // as value is greater than max
    }

    @Test()
    public void testParseInput11() throws APIException {
        String line = "75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)";
        InputLineParser inputLineParser = new InputLineParser();
        LineItem lineItem = inputLineParser.parseInput(line);
        Assert.assertEquals(75, lineItem.getMaxWeight());
        Assert.assertEquals(5, lineItem.getItems().size());
    }





}