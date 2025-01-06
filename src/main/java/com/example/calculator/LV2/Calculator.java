package com.example.calculator.LV2;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {
    //App 클래스의 main 메서드에서 Calculator 클래스의 연산 결과를 저장하고 있는 컬렉션 필드에 직접 접근하지 못하도록 수정 (캡슐화)
    private Queue<Integer> results = null;
    public Calculator() {
        results = new LinkedList<Integer>();
    }
    //사칙연산을 수행한 후, 결과값을 반환하는 메서드 구현
    public int calculate(int num1, int num2, char op) {
        int res = 0;
        switch (op) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                //연산 오류가 발생할 경우 해당 오류에 대한 내용을 정제하여 출력합니다.
                if(num2 == 0) {
                    throw new RuntimeException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다\n");
                }
                res = num1 / num2;
                break;
            default:
                throw new RuntimeException("올바른 기호를 입력해주세요.");
        }
        saveResults(res);
        return res;
    }


    //간접 접근을 통해 필드에 접근하여 가져올 수 있도록 구현합니다
    public Queue<Integer> getResults() {
        return results;
    }

    //간접 접근을 통해 필드에 접근하여 수정할 수 있도록 구현합니다
    public void saveResults(int res) {
        results.add(res);
    }
    //간접 접근을 통해 필드에 접근하여 수정할 수 있도록 구현합니다
    public void remove(){
        results.remove();
    }
}
