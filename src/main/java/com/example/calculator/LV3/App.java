package com.example.calculator.LV3;

import com.example.calculator.Validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

//Lv 1에서 구현한 App 클래스의 main 메서드에 Calculator 클래스가 활용될 수 있도록 수정
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Validator v = new Validator();
        double num1 =0 ,num2 = 0;
        OperatorType op = null;
        String more = "go";
        char yn = 'y';
        double res=0.0;
        ArithmeticCalculator<Double> calc = new ArithmeticCalculator<>();
        //반복문을 사용하되, 반복의 종료를 알려주는 “exit” 문자열을 입력하기 전까지 무한으로 계산을 진행할 수 있도록 소스 코드를 수정하기
        while(!more.equals("exit")) {
            String input="";
            //숫자 입력받기
            System.out.print("첫 번째 숫자를 입력하세요 : ");
            input = sc.next();
            while(!v.isNumber(input)) {
                System.out.println("숫자를 입력해주세요");
                System.out.print("첫 번째 숫자를 입력하세요 : ");
                input = sc.next();
            }
            num1 = Double.parseDouble(input);

            System.out.print("두 번째 숫자를 입력하세요 : ");
            input = sc.next();
            while(!v.isNumber(input)) {
                System.out.println("숫자를 입력해주세요");
                System.out.print("두 번째 숫자를 입력하세요 : ");
                input = sc.next();
            }
            num2 = Double.parseDouble(input);

            try{
                //사칙연산 기호(➕,➖,✖️,➗)를 입력받기
                System.out.print("사칙연산 기호(+,-,*,/)를 입력 해주세요.");
                op = OperatorType.getOperatorType(sc.next().charAt(0));
                calc.setOperation(op.getOperation());
                res = calc.calculate(num1,num2);

                System.out.printf("%.1f %c %.1f = %.3f\n",num1, op.getLabel() ,num2, res);
            }catch (Exception e) {
                //연산 오류가 발생할 경우 해당 오류에 대한 내용을 정제하여 출력합니다.
                System.out.println(e.getMessage());
                continue;
            }

            System.out.println("결과를 저장하시겠습니까? ( Y / N )");
            yn = sc.next().charAt(0);
            if(yn == 'y' || yn == 'Y') {
                calc.saveResults(res);
            }


            if(!calc.getResults().isEmpty()) {
                System.out.println("가장 오래된 결과를 삭제하시겠습니까? ( Y / N )");
                System.out.println(calc.getResults());
                yn = sc.next().charAt(0);
                if((yn == 'Y' || yn == 'y')){
                    calc.remove();
                }
            }

            System.out.println("검색기능을 사용하시겠습니까? ( Y / N )");
            yn = sc.next().charAt(0);
            if((yn == 'Y' || yn == 'y')){
                System.out.print("어떤 수보다 큰 결과를 원하시나요? : ");
                input = sc.next();
                while(!v.isNumber(input)) {
                    System.out.print("숫자를 입력해주세요 : ");
                    input = sc.next();
                }
                double search = Double.parseDouble(input);
                List<Double> list = calc.search(search);
                if(list.isEmpty()) {
                    System.out.println(input + "보다 큰 계산 결과가 없습니다.");
                }else System.out.println(list);
            }

            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            more = sc.next();
        }

    }
}
