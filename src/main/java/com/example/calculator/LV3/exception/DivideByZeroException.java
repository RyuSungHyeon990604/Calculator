package com.example.calculator.LV3.exception;

public class DivideByZeroException extends Exception{
    public DivideByZeroException(){
        super("0으로 나눌 수 없습니다.");
    }
}
