package com.example.calculator.LV3.operation;

import com.example.calculator.LV3.exception.DivideByZeroException;

public class DivOperation implements Operation{
    @Override
    public double calculate(double a, double b) throws DivideByZeroException {
        if(b == 0){
            throw new DivideByZeroException();
        }
        return a / b;
    }
}
