# [Kotlin in Action] Chapter 01. 코틀린 기초

2장에서 다루는 내용

- 함수, 변수, 클래스, enum, 프로퍼티를 선언하는 방법
- 제어 구조
- 스마트 캐스트
    - 타입 검사
    - 타입 캐스트
    - 타입 강제 변환
- 예외 처리
    - 예외 던지기와 예외 잡기

## 1. 기본 요소 : 함수와 변수

코틀린에서는 `타입 선언을 생략`해도 된다. 코틀린은 왜 변경 가능한 데이터보다 변경할 수 없는 불변 사용을 장려할까?

    fun main(args: Array<String>) {
    	println("Hello, world");
    }

- 함수를 선언할 때 fun 키워드를 사용한다.
- 파라미터 이름 뒤에 타입을 쓴다.
- 함수를 최상위 수준에 정의할 수 있다. 꼭 클래스 안에 함수를 넣어야 할 필요가 없다.
- 배열 처리를 위한 문법 따로 없다.
- 표준 자바 라이브러리 함수를 간결하게 사용할 수 있다. (println)
- 세미콜론은 붙이지 않아도 된다.

### 1-1. 함수

```
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}
```

#### 문(statement)과 식(expression)의 구분

코틀린에서 if는 식이지 문이 아니다. 
   - 식은 값을 만들어 내며 다른 식의 하위 요소로 계산에 참여할 수 있다.
   - 문은 자신을 둘러싸고 있는 가장 안쪽 블록의 최상위 요소로 존재하며 아무런 값을 만들어내지 않는다.
자바에서는 모든 제어 구조가 문인 반면 코틀린에서는 루프를 제외한 대부분의 제어가 구조가 식이다.

블록이 본문인 함수 — 본문이 중괄호로 둘러싸인 함수
<br />
식이 본문인 함수 — 등호와 식으로 이뤄진 함수
   - 식이 본문인 함수의 반환 타입 생략 가능 (`타입 추론`)

### 1-2. 변수

자바와 같이 타입으로 변수 선언을 시작하면 타입을 생략할 경우 식과 변수 선언을 구별할 수 없다.

```kotlin
val answer = 42
val answer: Int = 42

val answer: Int
answer = 42
```

초기화 식이 없다면 변수에 저장될 값에 대해 아무 정보가 없기 때문에 컴파일러가 타입을 추론할 수 없다.
<br />
변수의 타입은 고정돼 바뀌지 않는다.

#### 변경 가능한 변수와 변경 불가능한 변수

- val (value에서 따옴)
    - 변경 불가능한(immutable) 참조를 저장하는 변수이다. 자바에서 final
- var (variable에서 따옴)
    - 변경 가능한(mutable) 참조이다.

#### 문자열 리터럴

```kotlin
val name = "kotlin"
println("Hello, $name!")
```

컴파일된 코드는 `StringBuilder를 사용하고 문자열 상수와 변수의 값을 append로 문자열 빌더 뒤에 추가`한다. 자바에서 + 연산으로 문자열 변수를 붙여도 컴파일러는 StringBuilder를 사용하는 바이트코드를 생성해준다.

### 2-2. 클래스와 프로퍼티

```java
// 자바 코드
public class Person {
  private final String name;

  public Person(String name) {
      this.name = name;
  }

  public String getName() {
      return name;
  }
}
```

```kotlin
// 코틀린 코드
class Person(val name: String)
```

코드가 없이 데이터만 저장하는 클래스를 값 객체(Value Object)라 부른다. 코틀린의 기본 접근 지정자(가시성)는 public이다.

### 2-3. 프로퍼티

```kotlin
class Person(
  // 읽기 전용 프로퍼티
  // 코틀린은 (비공개)필드와 필드를 읽는 단순한 (공개)게터를 만든다.
  val name: String,  

  // 쓸 수 있는 프로퍼티
  // 코틀린은 (비공개)필드, (공개)게터, (공개)세터를 만들어낸다.
  var isMarried: Boolean  
)
```

프로퍼티 이름을 직접 사용해도 코틀린이 자동으로 게터를 호출해준다.

```kotlin
val person = Person("Bob", true)
println(person.name) => 실제로는 게터 호출
```


### 참고 자료

- [Kotlin in Action](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=120267010)