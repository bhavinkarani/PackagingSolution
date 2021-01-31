package com.mobiquity.vo;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.PackerContsants;

public final class Item {
    private int index;
    private double weight;
    private int price;

    private Item(int index, double weight, int price) {
        this.index = index;
        this.weight = weight;
        this.price = price;
    }


    public static Item getItem(String threeInputs) throws APIException {
        String[] parts = threeInputs.split(PackerContsants.COMMA);
        if(parts.length != PackerContsants.THREE){
            throw new APIException("invalida input : "+threeInputs);
        }
        try{
            return new Item(
                    Integer.parseInt(parts[0]),
                    Double.parseDouble(parts[1]),
                    Integer.parseInt(parts[2].replace(PackerContsants.EURO, PackerContsants.EMPTY_STRING))
            );
        } catch(NumberFormatException nfe){
            throw new APIException("number format exception : "+threeInputs);
        }
    }

    public Integer getIndex() {
        return index;
    }

    public Double getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

}
