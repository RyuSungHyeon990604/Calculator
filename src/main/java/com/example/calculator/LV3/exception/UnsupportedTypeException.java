package com.example.calculator.LV3.exception;

public class UnsupportedTypeException extends Exception{
    public UnsupportedTypeException(){
        super("Number 타입이 아닙니다.");
    }
}
