package com.example.calculator.LV1;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long num1 =0 ,num2 = 0;
        char op = ' ';
        String more = "go";

        //반복문을 사용하되, 반복의 종료를 알려주는 “exit” 문자열을 입력하기 전까지 무한으로 계산을 진행할 수 있도록 소스 코드를 수정하기
        while(!more.equals("exit")) {
            //양의 정수(0 포함)를 입력받기
            System.out.print("첫 번째 숫자를 입력하세요 [양의 정수(0 포함)] : ");
            num1 = sc.nextLong();
            while(num1 < 0) {
                System.out.print("양의 정수(0 포함)를 입력해주세요 : ");
                num1 = sc.nextLong();
            }
            System.out.print("두 번째 숫자를 입력하세요 [양의 정수(0 포함)] : ");
            num2 = sc.nextLong();
            while(num2 < 0) {
                System.out.print("양의 정수(0 포함)를 입력해주세요 : ");
                num2 = sc.nextLong();
            }
            //사칙연산 기호(➕,➖,✖️,➗)를 입력받기
            System.out.print("사칙연산 기호(+,-,*,/)를 입력 해주세요.");
            op = sc.next().charAt(0);

            //위에서 입력받은 양의 정수 2개와 사칙연산 기호를 사용하여 연산을 진행한 후 결과값을 출력하기
            switch (op) {
                case '+':
                    System.out.printf("%d + %d = %d\n",num1 ,num2, (num1 + num2));
                    break;
                case '-':
                    System.out.printf("%d - %d = %d\n",num1 ,num2, (num1 - num2));
                    break;
                case '*':
                    System.out.printf("%d * %d = %d\n",num1 ,num2, (num1 * num2));
                    break;
                case '/':
                    //연산 오류가 발생할 경우 해당 오류에 대한 내용을 정제하여 출력합니다.
                    if(num2 == 0) {
                        System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다\n");
                        continue;
                    }
                    System.out.printf("%d / %d = %.2f\n",num1 ,num2, (double)num1 / num2);
                    break;
                default:
                    System.out.println("올바른 기호를 입력해주세요");
            }

            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            more = sc.next();
        }

    }
}
