package com.example.calculator.LV3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

//Lv 1에서 구현한 App 클래스의 main 메서드에 Calculator 클래스가 활용될 수 있도록 수정
public class App {
    private Scanner sc;
    private OperatorType op = null;
    private ArithmeticCalculator<Double> calc;
    public App(){
        sc = new Scanner(System.in);
        calc = new ArithmeticCalculator<>();
    }
    public void run(){
        double num1 =0 ,num2 = 0;
        double res=0.0;
        //숫자 입력받기
        num1 = getInput("첫 번째 숫자를 입력하세요 : ");
        num2 = getInput("두 번째 숫자를 입력하세요 : ");

        try{
            //사칙연산 기호(➕,➖,✖️,➗)를 입력받기
            System.out.print("사칙연산 기호(+,-,*,/)를 입력 해주세요.");
            op = OperatorType.getOperatorType(sc.next().charAt(0));
            //set operation
            calc.setOperation(op.getOperation());
            res = calc.calculate(num1,num2);
            System.out.printf("%.1f %c %.1f = %.5f\n",num1, op.getLabel() ,num2, round(res, 5));
        }catch (Exception e) {
            //연산 오류가 발생할 경우 해당 오류에 대한 내용을 정제하여 출력합니다.
            System.out.println(e.getMessage());
            return;
        }

        if(calc.isEmpty()) {
            System.out.println("저장된 결과가 없습니다.");
        }else{
            Queue<Double> results = calc.getResults();
            printCollection(results);

            if(yn("\n가장 먼저 계산된 결과를 삭제하시겠습니까? ( Y / N )","y")){
                Double remove = calc.remove();
                System.out.println(new BigDecimal(remove).setScale(3,RoundingMode.HALF_UP)+" 을 삭제했습니다.");
            }

            results = calc.getResults();
            if(results.isEmpty()) {
                System.out.println("저장된 결과가 없습니다.");
            }else{
                printCollection(results);
            }
        }

        if(!calc.isEmpty()) {
            if(yn("\n검색기능을 사용하시겠습니까? ( Y / N )","y")){
                double search = getInput("어떤 수보다 큰 결과를 원하시나요? : ");
                List<Double> list = calc.search(search);
                if(list.isEmpty()) {
                    System.out.println("\n"+ search + "보다 큰 계산 결과가 없습니다.");
                }else {
                    System.out.println(search +"보다 큰 계산 결과입니다 : ");
                    printCollection(list);
                }
            }
        }
    }
    private double getInput(String str) {
        System.out.print(str);
        String input = sc.next();
        while(!isNumber(input)) {
            System.out.print("숫자를 입력해주세요 : ");
            input = sc.next();
        }
        return Double.parseDouble(input);
    }
    private boolean yn(String str,String ifTrue) {
        System.out.println(str);
        String input = sc.next().toLowerCase();
        return input.equals(ifTrue);
    }

    public boolean isNumber(String input) {
        try{
            Double.parseDouble(input);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public void printCollection(Collection<? extends Number> obj) {
        if(obj == null){
            System.out.println("obj is null");
            return;
        }
        if(obj.isEmpty()){
            System.out.println("obj is empty");
            return;
        }
        for (Number o : obj) {
            System.out.print(round(o.doubleValue(),5)+" ");
        }
    }

    public BigDecimal round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd;
    }

    public static void main(String[] args) {
        App app  =new App();
        Scanner sc = new Scanner(System.in);
        do{
            app.run();
            System.out.println("\n더 계산하시겠습니까? (exit 입력 시 종료)");
        }while(!sc.next().equals("exit"));
        System.out.println("계산기를 종료합니다.");
    }
}
