## 예외처리 (exception handling)

### \# 프로그램 오류

> 프로그램 실행 중 어떤 원이에 의해서 오작동을 하거나 비정상적으로 종료되는 경우가 있는데, 이러한 결과를 초래하는 원인을 프로그램 에러 또는 오류라고 한다.
>
> ```
> 컴파일 에러 : 컴파일 시에 발생하는 에러
> 런타임 에러 : 실행 시에 발생하는 에러
> 논리적 에러 : 실행은 되지만, 의도와 다르게 동작하는 것
> ```
>
> ```
> 에러(error) : 프로그램 코드에 의해서 수습될 수 없는 심각한 오류
> 예외(exception) : 프로그램 코드에 의해서 수습될 수 있는 다소 미약한 오류
> ```



### \# 예외 클래스의 계층구조

> ```
> Exception
> │
> ├── IOException
> ├── ClassNotFoundException
> ├── ClassNotFoundException
> ├── ...
> └── RuntimeException
> 		├── ArithmetixException
> 		├── ClassCastException
> 		├── NullPointerException
> 		├── ...
> 		└── IndexOutOfBoundsException
> 		
> (1) Exception 클래스와 그 자손들 (RuntimeException과 자손들 제외)
> 			- 사용자의 실수와 같은 외적인 요인에 의해 발생하는 예외
> (2) RuntimeException 클래스와 그 자손들
> 			- 프로그래머의 실수로 발생하는 예외
> ```



### \# 예외 처리하기 - try-catch문

> ```
> 예외처리(exception handling)
> 	정의 : 프로그램 실행 시 발생할 수 있는 예외의 발생에 대비한 코드를 작성하는 것.
> 	목적 : 프로그램의 비정상 종료를 막고, 정상적인 실행 상태를 유지하는 것.
> ```
>



### \# printStackTrace()와 getMessage()

> pringStackTrace() : 예외 발생 당시의 호출스택(Call Stack)에 있엇던 메서드의 정보와 예외 메시지를 화면에 출력한다.
>
> getMessge() : 발생한 예외클래스의 인스턴스에 저장된 메시지를 얻을 수 있다.



### \# 예외 발생시키기

> 키워드 throw 사용해서 프로그래머가 고의로 예외를 발생시킬 수 있다.
>
> ```java
> 1. 연산자 new를 이용해서 발생시키려는 예외 클래스의 객체를 만든 다음
> 		Exception e = new Eception("고의로 발생시켰음");
> 2. 키워드 throw를 이용해서 예외를 발생시킨다.
>   	throw e;
> // throw new Exception("고의로 발생시켰음");
> ```
>
> `Checked Exception` : 컴파일러가 예외처리 확인하는 Exception 클래스들.
>
> `Unchecked Exception` : 컴파일러가 예외처리 확인하지 않는 RuntimeException 클래스들.

