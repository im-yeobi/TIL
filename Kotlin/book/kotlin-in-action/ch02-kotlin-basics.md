# [Kotlin in Action] Chapter 02. 코틀린 기초

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


### 2. 클래스와 프로퍼티

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

// 코틀린 코드
class Person(val name: String)
```

코드가 없이 데이터만 저장하는 클래스를 값 객체(Value Object)라 부른다. 코틀린의 기본 접근 지정자(가시성)는 public이다.

### 2-1. 프로퍼티

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

```
val person = Person("Bob", true)
println(person.name) => 실제로는 게터 호출
```

### 2-2. 커스텀 접근자

프로퍼티의 접근자를 직접 작성

```kotlin
class Rectangle(val heigth: Int, val width: Int) {
	val isSequence: Boolean
		get() {  // 프로퍼티 게터 선언
			return height == width
		}
}
```

isSquare 프로퍼티에는 자체 값을 저장하는 필드가 필요 없다. 이 프로퍼티에는 자체 구현을 제공하는 게터만 존재한다.

### 2-3. 코틀린 소스코드 구조: 디렉토리와 패키지

자바의 경우 모든 클래스를 패키지 단위로 관리한다. 코틀린에도 자바와 비슷한 개념의 패키지가 있다.

`코틀린에서는 여러 클래스를 한 파일에 넣을` 수 있고, 파일의 이름도 마음대로 정할 수 있다. 하지만 대부분의 경우 자바와 같이 패키지별로 디렉토리를 구성하는 편이 낫다.(자바와 코틀린 함께 사용하는 경우)
<br />
각 클래스를 정의하는 소스코드 크기가 아주 작은 경우, 여러 클래스를 한 파일에 넣을 수 있어 관리가 용이하다.


## 3. 선택 표현과 처리: enum과 when

`when`은 자바의 switch를 대치하되 훨씬 더 강력하다. 코틀린에서 enum을 선언하는 방법과 `스마트 캐스트(smart cast)`에 대해서도 살펴본다.

### 3-1. enum 클래스 정의

```kotlin
enum class Color {
	RED, ORANCE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}
```

코틀린에서는 enum class를 사용하지만 자바에서는 enum 사용. 코틀린에서는 enum을 `소프트 키워드(soft keyword)`라 부른다.

```kotlin
enum class Color(val r: Int, val g: Int, val b: Int) {  // 상수의 프로퍼티 정의
	RED(255, 0 0), ORANCE(255, 165, 0),  // 각 상수 생성할 때 그에 대한 프로퍼티 값 지정
	YELLOW(255, 255, 0), GREEN(0, 255, 0), BLUE(0, 0, 255), 
	INDIGO(75, 0, 130), VIOLET(238, 130, 238);  // 세미콜론 필수

	fun rgb() = (r * 256 + g) * 256 + b  // enum 클래스 안에서 메소드 정의
}
```

enum 클래스 안에서 상수 목록과 메소드 정의 사이에 `세미콜론`을 필수로 넣어야 한다.

### 3-2. when으로 enum 클래스 다루기

if와 마찬가지로 when도 값을 만들어내는 식이다. `식이 본문인 함수에 when을 바로 사용`할 수 있다.

```kotlin
// 여러 줄 식을 본문으로 하는 함수
fun getMnemonic(color: Color) =  // 함수의 반환 값으로 when 식 직접 사용
	when (color) {  // 색이 특정 enum 상수와 같을 때, 대응하는 문자열 반환
		Color.RED -> "Richard"
		Color.ORANGE -> "Of"
		Color.YELLOW -> "York"
		Color.GREEN -> "Gave"
		Color.BLUE -> "Battle"
		Color.INDIGO -> "In"
		Color.VIOLET -> "Vain"
	}
```

자바와 달리 각 분기의 끝에 `break를 넣지 않아`도 된다. 한 분기 안에서 여러 값을 매치 패턴으로 사용할 수도 있다. 그럴 경우 값 사이를 콤마(,)로 분리한다.

```kotlin
fun getMnemonic(color: Color) =
	when (color) {
		Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
		Color.GREEN -> "neutral"
		Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
	}
```

### 3-3. when과 임의의 객체를 함께 사용

코틀린에서 when은 자바의 static보다 훨씬 더 강력하다. 코틀린 when의 분기 조건은 임의의 `객체를 허용`한다.

```kotlin
fun mix(c1: Color, c2: Color) = 
	when(setOf(c1, c2)) {
		setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
		setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
		setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
		else -> throw Exception("Dirty color") // 매치되는 분기 조건 없으면 이 문장 실행
	}
```

when의 분기 조건 부분에 식을 넣을 수 있다.

### 3-4. 인자 없는 when 사용

인자가 없는 when 식을 사용하면 `불필요한 객체 생성을 막을` 수 있다.

```kotlin
fun mixOptimized(c1: Color, c2: Color) =
	when {
		(c1 == Color.RED && c2 == Color.YELLOW) ||
		(c1 == Color.RED && c2 == Color.YELLOW) -> 
			Color.ORANGE
		(c1 == Color.YELLOW && c2 == Color.BLUE) ||
		(c1 == Color.BLUE && c2 == Color.YELLOW) -> 
			Color.GREEN
		(c1 == Color.BLUE && c2 == Color.VIOLET) ||
		(c1 == Color.VIOLET && c2 == Color.BLUE) -> 
			Color.INDIGO
		else -> throw Exception("Dirty color");
	}
```

when에 아무 인자도 없으려면 각 분기의 조건이 불리언 결과를 계산하는 식이어야 한다. mixOptimized는 `추가 객체를 만들지 않는다는 장점이 있지만 가독성은 더 떨어진다`.

### 3-5. 스마트 캐스트: 타입 검사와 타입 캐스트를 조합

`is 검사는 자바의 instanceof와 비슷`하다. 자바에서 어떤 변수의 타입을 instanceof로 확인한 다음에 그 타입에 속한 멤버에 접근하기 위해서는 명시적으로 변수 타입을 캐스팅해야 한다.

코틀린에서는 프로그래머 대신 컴파일러가 캐스팅을 해준다. 어떤 변수가 원하는 타입인지 일단 is로 검사하고 나면 굳이 변수를 원하는 타입으로 캐스팅하지 않아도, `처음부터 그 변수가 원하는 타입으로 선언된 것처럼 사용`할 수 있다. 실제로는 `컴파일러가 캐스팅을 수행`해준다. 이를 `스마트 캐스트(smart cast)` 라고 한다.

```kotlin
if (e is Sum) {
	return eval(e.right) + eval(e.left)
}
```

### 3-5. if를 when으로 변경

코틀린에서는 if가 값을 만들어내기 때문에 자바와 달리 3항 연산자가 없다.

```kotlin
// 코틀린
if (a > b) a else b

// 자바에서 동일 표현
(a > b) ? a : b

fun eval(e: Expr): Int =
	if (e is Num) {
		e.value	
	} else if (e is Sum) {
		eval(e.right) + eval(e.left)
	} else {
		throw IllegalArguementException("Unknown expression")
	}

fun eval(e: Expr): Int =
	when (e) {
		is Num ->
			e.value
		is Sum ->
			eval(e.right) + eval(e.left)
		else ->
			throw IllegalArgumentException("Unknown expression")
	}
```

when으로 if를 대신할 수 있는 경우를 생각해보자. if나 when 분기에서 수행해야 하는 로직이 복잡해지면 분기 본문에 블록을 사용할 수 있다.

### 3-6. if와 when의 분기에서 블록 사용

블록의 마지막 문장이 블록 전체의 결과가 된다.

```kotlin
fun evalWithLogging(e: Expr): Int =
	when (e) {
		is Num -> {
			println("num: ${e.value}")
			e.value
		}
		is Sum -> {
			val left = evalWithLogging(e.left)
			val right = evalWithLogging(e.right)
			println("sum: $left + $right")
			left + right
		}
		else -> throw IllegalArguementException("Unknown expression")
	}
```

'블록의 마지막 식이 블록의 결과'라는 규칙은 블록이 값을 만들어내야 하는 경우 항상 성립한다. 식인 경우에만 성립한다. `함수에 대해서는 성립하지 않는다`.


## 4. 대상을 이터레이션:  while과 for 루프

이번 챕터에서 설명한 코틀린 특성 중 자바와 가장 비슷한 것이 이터레이션이다. while 루프는 자바와 동일, for는 자바의 for-each 루프에 해당하는 형태만 존재한다.

### 4-1. while 루프

```kotlin
while (조건) {
	// ...
}

do {
	// 
} while (조건)
```

### 4-2. 수에 대한 이터레이션

코틀린에서는 자바의 for 루프(초기값, 증가값, 최종값 형식)에 해당하는 요소가 없다. 코틀린에서는 `범위(range)`를 사용한다. 범위는 기본적으로 두 값으로 이뤄진 구간이다.

```kotlin
코틀린의 범위는 폐구간(닫힌 구간) 또는 양끝을 포함하는 구간이다. 두 번째 값(10)이 항상 범위에 포함된다.
val oneToTen = 1..10
```

수열(progression) : 어떤 범위에 속한 값을 일정한 순서로 이터레이션 하는 경우

```kotlin
for (i in 1..100) {
	// 1 2 3 .. 100
	print(fizzBuzz(i))
}

for (i in 100 downTo 1 step 2) {
	// 100 98 96 .. 2
	print(fizzBuzz(i))
}

for (i in 0 until 100) {  // == in 0..99
	// 0 1 2 .. 99
	print(fizzBuzz(i))
}
```

### 4-3. 맵에 대한 이터레이션

컬렉션에 대한 이터레이션은 for .. in 루프를 자주 쓴다.

```kotlin
val binaryReps = TreeMap<Char, String>()

for (c in 'A'..'F') { // A -> F까지 이터레이션
	val binary = Integer.toBinaryString(c.toInt);
	binaryReps[c] = binary;  // c를 키로 2진 표현을 맵에 넣는다
}

for ((letter, binary) in binaryReps) {  // 맵에 대한 이터레이션
	println("$letter = $binary")
}
```

get과 put 사용하는 대신 `map[key]`나 `map[key] = value`를 사용해 값을 가져오고 설정할 수 있다.

컬렉션이나 범위에 대해 `in` 키워드를 사용한다. 어떤 값이 범위나 컬렉션에 들어있는지 알고 싶을 때도 in을 사용한다.

```kotlin
val list = arrayListOf("10", "11", "1001")
for ((index, element) in list.withIndex()) {  // 인덱스와 함께 컬렉션을 이터레이션 한다.
	println("$index: $element")
}
```

### 4-4. in으로 컬렉션이나 범위의 원소 검사

`in` 연산자를 사용해 어떤 값이 범위에 속하는지 검사할 수 있다. `!in` 을 사용하면 어떤 값이 범위에 속하지 않는지 검사할 수 있다.

```kotlin
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'
```

in과 !in 연산자를 when 식에서 사용해도 된다.

```kotlin
fun recognize(c: Char) = when (c) {
	in '0'..'9' -> "It's a digit!"
	in 'a'..'z', 'A'..'Z' -> "It's a letter!"
	else -> "I don't know"
}
```

String에 있는 Comparable 구현이 두 문자열을 알파벳 순서로 비교하기 때문에 in 검사에서도 문자열을 알파벳 순서로 비교한다.

```kotlin
// "Java" <= "Kotlin" && "Kotlin" <= "Scala"와 같다.
>>> println("Kotlin" in "Java".."Scala")
true
```


## 5. 코틀린의 예외 처리

코틀린의 예외 처리는 자바나 다른 언어의 예외 처리와 비슷하다.

```kotlin
if (percentage !in 0..100) {
	throw IllegalArgumentException(
		"A percentage value must be between 0 and 10: $percentage")
}
```

예외 인스턴스를 만들 때도 new를 붙일 필요가 없다. 자바와 달리 `코틀린의 throw는 식`이므로 다른 식에 포함될 수 있다.

```kotlin
val percentage = 
	if (number in 0..100)
		number
	else
		throw IllegalArgumentException(  // throw는 식이다.
			"A percentage value must be between 0 and 10: $percentage")		
```

### 5-1. try, catch, finally

자바와 마찬가지로 예외를 처리하려면 try, catch, exception 절을 함께 사용한다.

```kotlin
fun readNumber(reader: BufferedReader): Int? {  // 예외 명시할 필요가 없다.
  try {
    val line = reader.readLine()
		return Integer.parseInt(line)
	}
  catch (e: NumberFormatException) {  // 예외 타입을 :의 오른쪽에 쓴다.
		return null
	} 
	finally {
		reader.close()
	}
}
```

자바와 가장 큰 차이는 `throws(이 경우 s가 붙어있다) 절이 코드에 없다`는 점이다. 자바에서는 체크 예외(checked exception)를 명시적으로 처리해야 한다. 다른 최신 JVM 언어와 마찬가지로 코틀린도 `체크 예외와 언체크 예외를 구분하지 않는다`.

### 5-2. try를 식으로 이용

자바와 코틀린의 중요한 차이를 하나 더 살펴보자.

```kotlin
fun readNumber(reader: BufferedReader) {
  val number = try {
      // 이 식의 값이 try식의 값이 된다.
      Integer.parseInt(reader.readLine())  
    } catch (e: NumberFormatException) {
      null  // 예외가 발생하면 null 값을 사용한다.
    }
	println(number)
}
```


## 요약

- 함수를 정의할 때 `fun` 키워드를 사용한다. `val`은 읽기 전용 변수. `var`는 변경 가능한 변수.
- 변수 이름 앞에 `$`를 붙이거나, 식을 `${식}`처럼 ${}로 둘러싸인 변수나 식의 값을 문자열 안에 넣을 수 있다. (문자열 템플릿)
- 코드가 없이 데이터만 저장하는 `값 객체(Value Object)` 클래스를 간결하게 표현할 수 있다.
- `if`는 식이며 값을 만들어낸다.
- `when`은 자바의 switch와 비슷하지만 더 강력하다.
- 어떤 변수의 타입을 검사하면 굳이 그 변수를 캐스팅하지 않아도 형변환 가능하다. (`스마트 캐스트`)
- 1..5와 같은 식은 범위를 만들어낸다. 범위와 수열을 통해 for 루프에 대해 같은 추상화 제공. `in`, `!in`을 사용해 범위 안에 들어있는지 표현.
- 코틀린에서는 함수가 던질 수 있는 예외를 선언하지 않아도 된다.



### 참고 자료

- [Kotlin in Action](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=120267010)