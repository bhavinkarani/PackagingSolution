package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.vo.Item;
import com.mobiquity.vo.LineItem;

import java.util.ArrayList;
import java.util.List;

public class InputLineParser {

    public InputLineParser(){

    }
    public LineItem parseInput(String line) throws APIException{
        LineItem lineItem = new LineItem();
        String[] parts = line.split(" : ");
        if(parts.length != PackerContsants.TWO){
            throw new APIException("invalid String for line : "+ line);
        }
        try{
            lineItem.setMaxWeight(Integer.parseInt(parts[0]));
            lineItem.setItems(createItemsFromString(parts[1], lineItem.getMaxWeight()));
        }catch(APIException apie){
            throw apie;
        }catch (NumberFormatException npe){
            throw new APIException("invalid input : "+parts[0]);
        }
        return lineItem;

    }

    private List<Item> createItemsFromString(String part, int maxWeight) throws APIException{
        List<Item> items = new ArrayList<>();
        if(!isValidateParenthesis(part)){
            throw new APIException("invalid paranthesis in string : "+part);
        }
        String[] allInputItems =  part.split(PackerContsants.SPACE);
        for(String inputItem : allInputItems){
            inputItem = inputItem.trim();
            if(!inputItem.startsWith("(")  ||  !inputItem.endsWith(")")){
                throw new APIException("invalid paranthesis in string : "+inputItem);
            }
            String threeInputs = inputItem.replace(PackerContsants.OPEN_BRAC,PackerContsants.EMPTY_STRING)
                    .replace(PackerContsants.CLOSE_BRAC,PackerContsants.EMPTY_STRING);
            Item item = Item.getItem(threeInputs);
            if(item.getWeight() <= maxWeight){
                items.add(item);
            }
        }
        return items;
    }

    private boolean isValidateParenthesis(String part) {
        int openCurlyCount=0, closeCurlyCount =0;
        for (int i = 0; i < part.length(); i++) {
            if(part.charAt(i) == '('){
                openCurlyCount ++;
            }else if (part.charAt(i) == ')'){
                closeCurlyCount ++;
            }
        }
        return openCurlyCount == closeCurlyCount;
    }

}
