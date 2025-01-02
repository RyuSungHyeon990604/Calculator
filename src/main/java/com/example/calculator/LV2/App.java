package com.example.calculator.LV2;

import java.util.Scanner;

//Lv 1에서 구현한 App 클래스의 main 메서드에 Calculator 클래스가 활용될 수 있도록 수정
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 =0 ,num2 = 0;
        char op = ' ';
        String more = "go";
        char yn = 'y';
        Calculator calc = new Calculator();
        int res;
        //반복문을 사용하되, 반복의 종료를 알려주는 “exit” 문자열을 입력하기 전까지 무한으로 계산을 진행할 수 있도록 소스 코드를 수정하기
        while(!more.equals("exit")) {
            //양의 정수(0 포함)를 입력받기
            System.out.print("첫 번째 숫자를 입력하세요 [양의 정수(0 포함)] : ");
            num1 = sc.nextInt();
            while(num1 < 0) {
                System.out.print("양의 정수(0 포함)를 입력해주세요 : ");
                num1 = sc.nextInt();
            }
            System.out.print("두 번째 숫자를 입력하세요 [양의 정수(0 포함)] : ");
            num2 = sc.nextInt();
            while(num2 < 0) {
                System.out.print("양의 정수(0 포함)를 입력해주세요 : ");
                num2 = sc.nextInt();
            }

            //사칙연산 기호(➕,➖,✖️,➗)를 입력받기
            System.out.print("사칙연산 기호(+,-,*,/)를 입력 해주세요.");
            op = sc.next().charAt(0);

            try{
                res = calc.calculate(num1,num2,op);
                System.out.printf("%d %c %d = %d\n",num1, op ,num2, res);
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


            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            more = sc.next();
        }

    }
}
