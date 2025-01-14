package com.example.calculator.LV3;

import com.example.calculator.LV3.operation.*;
import java.util.HashMap;
import java.util.Map;

public enum OperatorType {
    ADD('+', new AddOperation()),
    SUB('-', new SubOperation()),
    MULTI('*', new MultiOperation()),
    DIV('/', new DivOperation()),;

    private final char label;
    private final Operation operation;
    private static final Map<Character, OperatorType> operatorTypeMap = new HashMap<>();

    OperatorType(char op, Operation operation) {
        this.label = op;
        this.operation = operation;
    }

    static {
        for (OperatorType op : OperatorType.values()) {
            operatorTypeMap.put(op.label, op);
        }
    }

    public char getLabel() {
        return label;
    }

    public Operation getOperation() {
        return operation;
    }

    public static OperatorType getOperatorType(char op) {
        OperatorType res = operatorTypeMap.get(op);
        if (res == null)
            throw new IllegalArgumentException("올바른 연산자를 입력해주세요 : " + op);
        return res;
    }
}
