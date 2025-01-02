package com.example.calculator;

public class Validator {
    public boolean isNumber(String input) {
        try{
            Double.parseDouble(input);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}
