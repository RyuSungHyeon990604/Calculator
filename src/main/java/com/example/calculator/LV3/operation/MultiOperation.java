package com.example.calculator.LV3.operation;

public class MultiOperation implements Operation{
    @Override
    public double calculate(Number a, Number b) {
        return a.doubleValue() * b.doubleValue();
    }
}
