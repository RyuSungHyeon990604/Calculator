package com.example.calculator.LV3;


import com.example.calculator.LV3.exception.UnsupportedTypeException;
import com.example.calculator.LV3.operation.Operation;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArithmeticCalculator<T extends Number> {
    //App 클래스의 main 메서드에서 Calculator 클래스의 연산 결과를 저장하고 있는 컬렉션 필드에 직접 접근하지 못하도록 수정 (캡슐화)
    private final Queue<T> results = new LinkedList<>();
    private final Class<T> type;
    private Operation operation = null;

    public ArithmeticCalculator(Class<T> type) {
        this.type = type;
    }

    //사칙연산을 수행한 후, 결과값을 반환하는 메서드 구현
    public T calculate(T num1, T num2) throws Exception {
        if (operation == null) {
            throw new NullPointerException("Operation is null");
        }
        // double 형식의 값을 구한 뒤  T 타입으로 캐스팅
        T res = castToT(operation.calculate(num1, num2));
        saveResult(res);
        return res;
    }

    //T 타입으로 캐스팅
    private T castToT(double result) throws UnsupportedTypeException {
        if (type == Double.class) {
            return type.cast(result);
        } else if (type == Integer.class) {
            return type.cast((int) result);
        } else if (type == Long.class) {
            return type.cast((long) result);
        } else if (type == Float.class) {
            return type.cast((float) result);
        } else if (type == Short.class) {
            return type.cast((short) result);
        } else if (type == Byte.class) {
            return type.cast((byte) result);
        }
        throw new UnsupportedTypeException();
    }

    //OperatorType의 operation으로 set
    public void setOperation(Operation op) {
        this.operation = op;
    }

    //간접 접근을 통해 필드에 접근하여 가져올 수 있도록 구현합니다
    public Queue<T> getResults() {
        return results;
    }

    //간접 접근을 통해 필드에 접근하여 수정할 수 있도록 구현합니다
    private void saveResult(T res) {
        results.add(res);
    }

    //간접 접근을 통해 필드에 접근하여 수정할 수 있도록 구현합니다
    public T remove() {
        return results.remove();
    }

    //계산 결과가 비어있는지 확인
    public boolean isEmpty() {
        return results.isEmpty();
    }

    public List<T> getListBiggerThanNum(T num) {
        return results.stream().filter(a -> num.doubleValue() < a.doubleValue()).toList();
    }
}
