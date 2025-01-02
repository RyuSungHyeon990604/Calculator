package com.example.calculator.LV3;

import com.example.calculator.LV3.operation.*;

import java.util.HashMap;
import java.util.Map;

public enum OperatorType {
    ADD('+', new AddOperation()),
    SUB('-', new SubOperation()),
    MULTI('*', new MultiOperation()),
    DIV('/', new DivOperation());

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

    public double calc(double a, double b){
        return operation.calculate(a,b);
    }

    public static OperatorType getOperatorType(char op) {
        OperatorType res = OPERATOR_MAP.get(op);
        if (res == null)
            throw new IllegalArgumentException("올바른 연산자를 입력해주세요 : " + op);
        return res;
    }
}
