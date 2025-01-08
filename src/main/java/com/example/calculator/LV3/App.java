package com.example.calculator.LV3;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

//Lv 1에서 구현한 App 클래스의 main 메서드에 Calculator 클래스가 활용될 수 있도록 수정
public class App {

    private final Scanner sc = new Scanner(System.in);
    private final ArithmeticCalculator<Double> calc = new ArithmeticCalculator<>(Double.class);

    public void run() {
        //숫자 입력받기
        double num1 = getInputNumber("첫 번째 숫자를 입력하세요");
        double num2 = getInputNumber("두 번째 숫자를 입력하세요");

        try {
            //사칙연산 기호(➕,➖,✖️,➗)를 입력받기
            System.out.print("사칙연산 기호(+,-,*,/)를 입력 해주세요. : ");
            OperatorType inputOperation = OperatorType.getOperatorType(sc.next().charAt(0));
            //set operation
            calc.setOperation(inputOperation.getOperation());
            printExpression(num1, num2, inputOperation, calc.calculate(num1, num2));
        } catch (Exception e) {
            //연산 오류가 발생할 경우 해당 오류에 대한 내용을 정제하여 출력합니다.
            System.out.println(e.getMessage());
            return;
        }
        //계산 결과가 비어있다면
        if (calc.isEmpty()) {
            System.out.println("저장된 결과가 없습니다.");
        } else {
            Queue<Double> results = calc.getResults();
            System.out.print("저장된 결과들 : ");
            printCollection(results);

            if (yn("\n가장 먼저 계산된 결과를 삭제하시겠습니까? ( Y / N )", "y")) {
                Double remove = calc.remove();
                System.out.println(remove + " 을 삭제했습니다.");
            }

            if (results.isEmpty()) {
                System.out.println("저장된 결과가 없습니다.");
            } else {
                System.out.print("저장된 결과들 : ");
                printCollection(results);
            }
        }

        //계산 결과가 존재한다면
        if (!calc.isEmpty()) {
            if (yn("\n검색기능을 사용하시겠습니까? ( Y / N )", "y")) {
                double num = getInputNumber("어떤 수보다 큰 결과를 원하시나요?");
                List<Double> list = calc.getListBiggerThanNum(num);
                if (list.isEmpty()) {
                    System.out.println("\n" + num + "보다 큰 계산 결과가 없습니다.");
                } else {
                    System.out.print(num + "보다 큰 계산 결과입니다 : ");
                    printCollection(list);
                }
            }
        }
    }

    //str을 출력하면서 입력을 받는다
    private double getInputNumber(String str) {
        System.out.print(str);
        System.out.print(" : ");
        String input = sc.next();
        while (!isNumber(input)) {
            System.out.print("(숫자를 입력해주세요)" + str);
            System.out.print(" : ");
            input = sc.next();
        }
        return Double.parseDouble(input);
    }

    //str을 출력하고 입력받은 문자가 ifTrue라면 true반환
    private boolean yn(String str, String isTrue) {
        System.out.print(str);
        System.out.print(" : ");
        String input = sc.next().toLowerCase();
        if (input.equals(isTrue.toLowerCase())) {
            return true;
        }
        return false;
    }

    private boolean isNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //식 출력
    private void printExpression(double num1, double num2, OperatorType op, double result) {
        System.out.printf("%s %s %s = %f\n", numberFormat(num1), op.getLabel(), numberFormat(num2), result);
    }

    private String numberFormat(double num) {
        return num >= 0 ? String.valueOf(num) : "(" + num + ")";
    }

    //collection 출력
    private void printCollection(Collection<? extends Number> obj) {
        if (obj == null) {
            System.out.println("obj is null");
            return;
        }
        if (obj.isEmpty()) {
            System.out.println("obj is empty");
            return;
        }
        System.out.println(obj);
    }
    public void runContinuous() {
        do {
            run();
            System.out.println("\n더 계산하시겠습니까? (exit 입력 시 종료)");
        } while (!sc.next().toLowerCase().equals("exit"));
        System.out.println("계산기를 종료합니다.");
    }

    public static void main(String[] args) {
        App app = new App();
        app.runContinuous();
    }
}
