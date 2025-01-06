package com.example.calculator.LV3;


import com.example.calculator.LV3.operation.Operation;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public  class ArithmeticCalculator<T extends Number> {
    //App 클래스의 main 메서드에서 Calculator 클래스의 연산 결과를 저장하고 있는 컬렉션 필드에 직접 접근하지 못하도록 수정 (캡슐화)
    private Queue<T> results = null;
    private Operation operation = null;
    public ArithmeticCalculator() {
        results = new LinkedList<T>();
    }
    //사칙연산을 수행한 후, 결과값을 반환하는 메서드 구현
    public T calculate(T num1, T num2) throws Exception {
        if(operation == null){
            throw new NullPointerException("Operation is null");
        }
        // double 형식의 값을 구한 뒤  T 타입으로 캐스팅
        T res = (T) Double.valueOf(operation.calculate(num1.doubleValue(), num2.doubleValue()));
        saveResults(res);
        return res;
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
    public void saveResults(T res) {
        results.add(res);
    }
    //간접 접근을 통해 필드에 접근하여 수정할 수 있도록 구현합니다
    public T remove(){
        return results.remove();
    }

    public boolean isEmpty(){
        return results.isEmpty();
    }
    public List<T> search(T num) {
        return results.stream().filter(a -> num.doubleValue() < a.doubleValue()).toList();
    }
}
