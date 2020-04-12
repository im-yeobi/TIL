# [Kotlin in Action] Chapter 03. 함수 정의와 호출

3장에서 다루는 내용

- 컬렉션, 문자열, 정규식을 다루기 위한 함수
- 이름 붙인 인자, 디폴트 프로퍼티 값, 중위 호출 문법 사용
- 확장 함수와 확장 프로퍼티를 사용해 자바 라이브러리 적용
- 최상위 및 로컬 함수와 프로퍼티를 사용해 코드 구조화


## 1. 코틀린에서 컬렉션 만들기

코틀린에서는 기존 자바 컬렉션을 활용할 수 있다. 코틀린에서는 자바보다 더 많은 기능을 쓸 수 있다. 

```kotlin
// 마지막 원소 가져오기
val strings = listof("first", "second", "fourteeth")
println(strings.last())
>> fourteeth

// 수로 이뤄진 컬렉션에서 최댓값 가져오기
val numbers = setOf(1, 14, 2)
println(numbers.max())
>> 14
```


## 2. 함수를 호출하기 쉽게 만들기

### 2-1. 이름 붙인 인자

자바와 코틀린에서의 메소드 콜은 다음과 같이 차이를 지닌다. 코틀린에서는 함수에 전달하는 인자 중 일부(또는 전부)의 `이름을 명시`할 수 있다.

```kotlin
// 자바
joinToString(list, "; ", "(", ")");

// 코틀린
joinToString(
  collection = list,
  separator = "; ",
  prefix = "(",
  postfix = ")"
)
```

이름 지정 인자를 사용하면 순서와 관계없이 지정할 수 있다.

### 2-2. 디폴트 파라미터 값

```kotlin
fun <T> joingToString(
  collection: Collection<T>,
  separator: String = ", ",
  prefix: String = "",
  postfix: String = ""
): String
```

@JvmOverloads 애노테이션 : 함수에 추가하면 코틀린 컴파일러가 자동으로 맨 마지막 파라미터로부터 파라미터를 하나씩 생략한 오버로딩한 자바 메소드를 추가해준다.

코틀린에서는 함수를 클래스 안에 선언할 필요가 없다.

### 2-3. 정적인 유틸리티 클래스 없애기: 최상위 함수와 프로퍼티

객체지향 언어인 자바에서는 모든 코드를 클래스의 메소드로 작성해야 한다는 사실을 알고 있다. 다양한 정적 메소드를 모아두는 역할만 담당하며, 특별한 상태나 인스턴스 메소드는 없는 클래스가 생겨난다. Util 클래스가 이에 해당한다. 코틀린에서는 이런 무의미한 클래스가 필요 없다.

코틀린에서는 무의미한 클래스가 필요 없다. 대신 함수를 직접 소스 파일의 `최상위 수준`, 모든 `다른 클래스의 밖`에 위치시키면 된다.

#### @JvmName 애노테이션

- 코틀린 최상위 함수가 포함되는 클래스의 이름 지정

#### 최상위 프로퍼티

- 함수와 마찬가지로 프로퍼티도 파일의 최상위 수준에 놓을 수 있다. 어떤 데이터를 클래스 밖에 위치시켜야 하는 경우는 흔하지는 않지만, 그래도 가끔 유용할 때가 있다. 어떤 연산을 수행한 횟수를 저장하는 var 프로퍼티를 만들 수 있다.

```kotlin
var opCount = 0  // 최상위 프로퍼티 선언

fun performOperation() {
  opCount++  // 최상위 프로퍼티의 값 변경
}

fun reportOperationCount() {
  println("Operation performed $opCount times")
}
```

이런 프로퍼티 값은 `정적 필드`에 저장된다.

최상위 프로퍼티를 활용해 코드에 상수를 추가할 수 있다.

```kotlin
// 자바 상수
public static final String UNIX_LINE_SEPARATOR = "\n";

// 코틀린 상수
val UNIX_LINE_SEPARATOR = "\n"
```


## 3. 메소드를 다른 클래스에 추가: 확장 함수와 확장 프로퍼티

#### 확장 함수

확장 함수는 어떤 클래스의 멤버 메소드인 것처럼 호출할 수 있지만 그 클래스의 밖에 선언된 함수이다.

```kotlin
package strings

fun String.lastChar(): Char = this.get(this.length - 1)
```

확장 함수를 만들려면 추가하려는 함수 이름 앞에 그 함수가 확장할 클래스의 이름을 덧붙이기만 하면 된다. 클래스 이름을 수신 `객체 타입(receiver type)`이라 부르며, 확장 함수가 호출되는 대상이 되는 값(객체)을 `수신 객체(receiver object)`라고 부른다.

```kotlin
fun String(수신 객체 타입).lastChar(): Char = this(수신 객체).get(this.length - 1)
```

클래스 안에서 정의한 메소드와 달리 확장 함수 안에서는 클래스 내부에서만 사용할 수 있는 비공개(private) 멤버나 보호된(protected) 멤버를 사용할 수 없다.

import 할 때 이름을 바꿔서 확장 함수 이름 충돌을 해결할 수 있다.

### 3-1. 확장 함수로 유틸리티 함수 정의

```kotlin
fun <T> Collection<T>.joinToString(
  separator: String = ", ",
  prefix: String = "",
  postfix: String = ""
): String {
  val result = StringBuilder(prefix)
  for ((index, element) in this.withIndex()) {
    if (index > 0) result.append(separator)
    result.append(element)
  }

  result.append(postfix)
  return result.toString()
}
```

### 3-2. 확장 함수는 오버라이드 할 수 없다.

코틀린의 메소드 오버라이드도 일반적인 객체 지향의 메소드 오버라이드와 마찬가지다. 하지만 확장 함수는 오버라이드 할 수 없다.

동적 디스패치 (dynamic dispatch)

- 실행 시점에 객체 타입에 따라 동적으로 호출될 대상 메소드를 결정하는 방식

정적 디스패치 (static dispatch)

- 컴파일 시점에 알려진 변수 타입에 따라 정해진 메소드를 호출하는 방식

### 3-3. 확장 프로퍼티

확장 프로퍼티를 사용하면 기존 클래스 객체에 대한 프로퍼티 형식의 구문으로 사용할 수 있는 API를 추가할 수 있다.

### 3-4. 컬렉션 처리: 가변 길이 인자, 중위 함수 호출, 라이브러리 지원

- vararg 키워드
    - 호출 시 인자 개수가 달라질 수 있는 함수를 정의할 수 있다.
- 중위(infix) 함수 호출 구문을 사용하면 인자가 하나뿐인 메소드를 간편하게 호출할 수 있다.
- 구조 분해 선언(destructuring declaration)

### 3-5. 가변 인자 함수: 인자의 개수가 달라질 수 있는 함수 정의

자바에서는 타입 뒤에 ...를 붙이는 대신 코틀린에서는 파라미터 앞에 `vararg` 변경자를 붙인다. 코틀린에서는 배열을 병시적으로 풀어서 배열의 각 원소가 인자로 전달되게 해야 한다. 기술적으로는 스프레드(spread) 연산자가 그런 작업을 해준다.

```kotlin
fun main(args: Array<String>) {
  val list = listOf("args: ", *args)  // 스프레드 연산자가 배열의 내용을 펼쳐준다.
  println(list)
}
```

### 3-6. 값의 쌍 다루기: 중위 호출과 구조 분해 선언

맵을 만들려면 `mapOf` 함수를 사용한다.

```kotlin
val map = mapOf(1 to "one", 7 to "seven", 53 to "fifity-three")
```

`to` 라는 단어는 코틀린 키워드가 아니다. `중위 호출(infix call)`이라는 특별한 방식으로 to라는 일반 메소드를 호출한 것이다.

to 함수는 확장 함수다. to를 사용하면 타입과 관계없이 임의의 순서쌍을 만들 수 있다.


### 4. 코드 다듬기: 로컬 함수와 확장

코틀린에서는 함수에서 추출한 함수를 원 함수 내부에 중첩시킬 수 있다. 흔히 발생하는 코드 중복을 로컬 함수를 통해 어떻게 제거할 수 있는지 살펴보자.

```kotlin
// 검증 로직을 확장 함수로 추출하기
class User(val id: Int, val name: String, val address: String)

fun User.validateBeforeSave() {
  fun validate(value: String, fieldName: String) {
    if (value.isEmpty()) {
      // User 프로퍼티 직접 사용
      throw IllegalArguementException("Can't save user $id: empty $fieldName")
    }
  }

  validate(name, "Name")
  validate(address, "Address")
}

fun saveUser(user: User) {
  user.validateBeforeSave()  // 확장 함수 호출

  // user를 데이터베이스에 저장한다.
}
```

중첩된 함수의 깊이가 깊어지면 코드를 읽기가 어려워진다. 일반적으로 한 단계만 함수를 중첩시키는 것을 권장한다.

## 요약

- 코틀린은 자체 컬렉션 클래스를 정의하지 않지만 자바 클래스를 확장해서 더 풍부한 API를 제공한다.
- `이룸 붙인 인자`를 활용해 가독성 향상
- 클래스 멤버가 아닌 `최상위 함수와 프로퍼티` 직접 선언 가능
- `확장 함수와 프로퍼티`를 사용해 모든 클래스의 API 확장 가능. 확장 함수를 사용해도 `실행 시점에 부가 비용이 들지 않는다`.
- 중위 호출을 통해 인자 하나밖에 없는 메소드나 함수를 더 깔끔한 구문으로 호출 가능
- 코틀린은 다양한 문자열 처리 함수 제공
- `3중 따옴표 문자열`을 이용해 이스케이프 필요한 문자열 깔끔하게 처리
- `로컬 함수`를 써서 코드 깔끔하게 유지하면서 중복 제거 가능


### 참고 자료

- [Kotlin in Action](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=120267010)
