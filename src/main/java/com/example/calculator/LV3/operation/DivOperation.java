package com.example.calculator.LV3.operation;

public class DivOperation implements Operation{
    @Override
    public double calculate(double a, double b) {
        if(b == 0){
            throw new RuntimeException("0으로 나눌 수 없습니다.");
        }
        return a / b;
    }
}
