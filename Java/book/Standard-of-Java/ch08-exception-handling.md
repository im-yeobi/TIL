# [자바의 정석] Chapter 08. 예외처리 (exception handling)

## 1. 예외처리 (exception handling)

### 1.1 프로그램 오류

프로그램이 실행 중 어떤 오류에 의해 오작동을 하거나 비정상적으로 종료되는 경우, 이러한 결과를 초래하는 원인을 프로그램 에러 또는 오류라고 한다.

#### 컴파일

- 컴파일 시에 발생하는 에러

#### 런타임 에러

- 프로그램 실행 도중에 발생하는 에러

#### 논리적 에러

- 실행은 되지만 의도와 다르게 동작하는 것

소스코드를 컴파일하면 컴파일러가 소스코드(*.java)에 대해 오타나 잘못된 구문, 자료형 체크 등의 기본적인 검사를 수행하여 오류가 있는지를 알려준다.

런타임 에러를 방지하기 위해서는 프로그램의 실행 도중 발생할 수 있는 모든 경우의 수를 고려하여 이에 대한 대비를 하는 것이 필요하다.

#### 에러 (error)

- OutOfMemoryError나 StackOverFlowError와 같이 일단 발생하면 복구할 수 없는 심각한 오류. 프로그램 코드에 의해서 수습될 수 없는 심각한 오류

#### 예외 (exception)

- 프로그램 코드에 의해서 수습될 수 있는 다소 미약한 오류

### 1.2 예외 클래스의 계층 구조

자바에서는 실행 시 발생할 수 있는 오류(Exception과 Error)를 클래스로 정의하였다.

    Exception 클래스와 RuntimeException 클래스 중심의 상속 계층도
    
    Exception
       ├── IOException
       ├── ClassNotFoundException
       ├── ...
       └── RuntimeException
           ├── ArithmetixException
           ├── ClassCastException
           ├── NullPointerException
           ├── ...
           └── IndexOutOfBoundsException
    
    1. Exception 클래스와 그 자손들 (RuntimeException과 그 자손들 제외)
    2. RuntimeException 클래스와 그 자손들

RuntimException 클래스들은 주로 `프로그래머의 실수`에 의해서 발생할 수 있는 예외로 자바의 프로그래밍 요소와 관계가 깊다.

Exception 클래스들은 주로 `외부의 영향`으로 발생할 수 있는 것들로 프로그램의 사용자들의 동작에 의해서 발생하는 경우가 많다. FileNotFoundException, ClassNotFoundException, DataFormatException 등..

### 1.3 예외 처리하기 — try-catch문

프로그램의 실행 도중에 발생하는 에러는 어쩔 수 없지만, 예외는 프로그래머가 이에 대한 처리를 미리 해주어야 한다.

`예외처리` 란 프로그램 실행 시 발생할 수 있는 `예기치 못한 예외의 발생에 대비한 코드를 작성`하는 것이다.

    예외처리(exception handling)의
    	정의 : 프로그램 실행 시 발생할 수 있는 예외의 발생에 대비한 코드를 작성하는 것
    	목적 : 프로그램의 비정상 종료를 막고, 정상적인 실행 상태를 유지하는 것

    예외를 처리하기 위한 try-catch문 
    
    try {
    	// 예외가 발생할 가능성이 있는 문장
    } catch (Exception1 e1) {
    	// Exception1이 발생했을 경우, 처리하기 위한 문장
    } catch (Exception2 e2) {
    	// Exception2가 발생했을 경우, 처리하기 위한 문장
    }
