package com.example.calculator.LV3;

import com.example.calculator.LV3.exception.DivideByZeroException;
import com.example.calculator.LV3.operation.*;

import java.util.HashMap;
import java.util.Map;

public enum OperatorType {
    ADD('+', (a, b) -> a + b),
    SUB('-', (a, b) -> a - b),
    MULTI('*', (a, b) -> a * b),
    DIV('/', (a, b) -> {
        if (b == 0) {
            throw new DivideByZeroException();
        }
        return a / b;
    });

    private final char label;
    private final Operation operation;
    private static final Map<Character, OperatorType> OPERATOR_MAP = new HashMap<>();

    OperatorType(char op, Operation operation) {
        this.label = op;
        this.operation = operation;
    }

    static {
        for (OperatorType op : OperatorType.values()) {
            OPERATOR_MAP.put(op.label, op);
        }
    }

    public char getLabel() {
        return label;
    }

    public Operation getOperation() {
        return operation;
    }

    public static OperatorType getOperatorType(char op) {
        OperatorType res = OPERATOR_MAP.get(op);
        if (res == null)
            throw new IllegalArgumentException("올바른 연산자를 입력해주세요 : " + op);
        return res;
    }
}
