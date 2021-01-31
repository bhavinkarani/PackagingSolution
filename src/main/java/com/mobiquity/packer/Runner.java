package com.mobiquity.packer;

import com.mobiquity.exception.APIException;

public class Runner {
    public static void main(String[] args) throws APIException {
        String res= Packer.pack("\\src\\main\\test\\resources\\example_input");
        System.out.println(res);
    }
}
