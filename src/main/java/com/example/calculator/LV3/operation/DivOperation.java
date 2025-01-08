package com.example.calculator.LV3.operation;

import com.example.calculator.LV3.exception.DivideByZeroException;

public class DivOperation implements Operation{
    @Override
    public double calculate(Number a, Number b) throws DivideByZeroException {
        if(b.doubleValue() == 0){
            throw new DivideByZeroException();
        }
        return a.doubleValue() / b.doubleValue();
    }
}
