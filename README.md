# 계산기 과제

## 소개(LV3 기준)

이 프로젝트는 자바(Java)로 작성된 콘솔 기반 계산기 프로그램입니다. 사용자가 입력한 두 숫자와 사칙연산 기호를 활용해 계산을 수행하도록 설계되었습니다.
객체지향 프로그래밍 원칙을 지키기 위해 노력했으며, 예외 처리를 통해 오류를 효과적으로 다루도록 구현했습니다. 

## 주요 기능

1. **사칙연산 지원**
   - 더하기(`+`), 빼기(`-`), 곱하기(`*`), 나누기(`/`) 연산을 수행할 수 있습니다.
   - 나누기 연산 시 0으로 나누는 경우에 대한 예외 처리가 포함되어 있습니다.

2. **결과 저장 및 관리**
   - 모든 계산 결과를 내부 큐(Queue)에 저장합니다.
   - 저장된 결과를 확인하거나, 가장 오래된 결과를 삭제할 수 있습니다.

3. **결과 검색**
   - 특정 값보다 큰 결과를 검색하여 출력합니다.

4. **유연한 숫자 타입 지원**
   - `Double`, `Integer`, `Long` 등 다양한 숫자 타입을 지원합니다.

5. **반복 계산**
   - 사용자가 종료 명령어(`exit`)를 입력할 때까지 연속적으로 계산을 수행할 수 있습니다.

## 프로그램 구성

### `App` 클래스[🔗](./src/main/java/com/example/calculator/LV3/App.java)

- 프로그램의 진입점(Main) 역할을 하며, 사용자와 상호작용합니다.
- 주요 역할:
  - 사용자 입력 처리 (숫자 및 연산자 입력)
  - 계산 수행 및 결과 출력
  - 저장된 결과 확인, 삭제, 검색 기능 제공

### `ArithmeticCalculator<T>` 클래스[🔗](./src/main/java/com/example/calculator/LV3/ArithmeticCalculator.java)

- 사칙연산 계산의 핵심 로직을 담당하는 제네릭 클래스입니다.
- 주요 역할:
  - 입력된 숫자와 연산자를 기반으로 계산 수행
  - 제네릭 클래스로 구현하여 모든 Number 타입에대해서 계산수행
```java
ArithmeticCalculator<Integer> calc1 = new ArithmeticCalculator<>(Integer.class);
ArithmeticCalculator<Long> calc2 = new ArithmeticCalculator<>(Long.class);
ArithmeticCalculator<Float> calc3 = new ArithmeticCalculator<>(Float.class);
ArithmeticCalculator<Double> calc4 = new ArithmeticCalculator<>(Double.class);
ArithmeticCalculator<Short> calc5 = new ArithmeticCalculator<>(Short.class);
ArithmeticCalculator<Byte> calc6 = new ArithmeticCalculator<>(Byte.class);
/*
setOperation..
*/
int calculate = calc1.calculate(1, 2);
System.out.println("calculate = " + calculate);
long calculate1 = calc2.calculate(1L, 2L);
System.out.println("calculate1 = " + calculate1);
float calculate2 = calc3.calculate(1F, 2F);
System.out.println("calculate2 = " + calculate2);
double calculate3 = calc4.calculate(1D, 2D);
System.out.println("calculate3 = " + calculate3);
short calculate4 = calc5.calculate((short) 1, (short) 2);
System.out.println("calculate4 = " + calculate4);
byte  calculate5 = calc6.calculate((byte) 1, (byte) 2);
System.out.println("calculate5 = " + calculate5);
```
```
calculate = 3
calculate1 = 3
calculate2 = 3.0
calculate3 = 3.0
calculate4 = 3
calculate5 = 3
```
  - 계산 결과를 큐(Queue)에 저장 및 관리
  - 특정 조건에 맞는 결과 필터링
  

### `OperatorType` 열거형(Enum) [🔗](./src/main/java/com/example/calculator/LV3/OperatorType.java)

- 사칙연산(더하기, 빼기, 곱하기, 나누기)을 정의하며, 각 연산자와 연산 로직을 매핑합니다.
```java
public enum OperatorType {
    ADD('+', (a, b) -> a + b),
    SUB('-', (a, b) -> a - b),
    MULTI('*', (a, b) -> a * b),
    DIV('/', (a, b) -> {
        if (b == 0) {
            throw new DivideByZeroException();
        }
        return a / b;
    });

    private final char label;
    private final Operation operation;
    ...
}
```
- 잘못된 연산자를 입력할 경우 사용자에게 예외 메시지를 반환합니다.
```java
public static OperatorType getOperatorType(char op) {
  OperatorType res = OPERATOR_MAP.get(op);
  if (res == null)
      throw new IllegalArgumentException("올바른 연산자를 입력해주세요 : " + op);
  return res;
}
```

## 사용 방법

1. **프로그램 실행**
   - 프로젝트를 빌드하고 실행합니다.
2. **숫자 입력**
   - 첫 번째 숫자와 두 번째 숫자를 입력합니다.
3. **연산자 입력**
   - 사칙연산 기호(`+`, `-`, `*`, `/`) 중 하나를 입력합니다.
4. **결과 확인 및 관리**
   - 계산 결과가 출력되며, 저장된 결과를 확인하거나 삭제할 수 있습니다.
5. **결과 검색**
   - 특정 값보다 큰 결과를 검색할 수 있습니다.
6. **종료**
   - `exit`을 입력하여 프로그램을 종료합니다.

## 예제 실행

**입력:**
```
첫 번째 숫자를 입력하세요 : 10
두 번째 숫자를 입력하세요 : 5
사칙연산 기호(+,-,*,/)를 입력 해주세요. : /
```

**출력:**
```
10.0 / 5.0 = 2.000000
저장된 결과들 : [2.0]

가장 먼저 계산된 결과를 삭제하시겠습니까? ( Y / N ) y
2.0 을 삭제했습니다.
저장된 결과가 없습니다.

검색기능을 사용하시겠습니까? ( Y / N ) n

더 계산하시겠습니까? (exit 입력 시 종료)
```

```
10.0 / 5.0 = 2.000000
저장된 결과들 : [2.0]

가장 먼저 계산된 결과를 삭제하시겠습니까? ( Y / N ) n
저장된 결과들 : [2.0]

검색기능을 사용하시겠습니까? ( Y / N ) y
어떤 수보다 큰 결과를 원하시나요? : 1
1.0보다 큰 계산 결과입니다 : [2.0]

더 계산하시겠습니까? (exit 입력 시 종료)
```

## 작성자

- 작성자:류성현
- 이메일: shryoo990604@gmail.com

