# [자바의 정석] Chapter 09. java.lang 패키지와 유용한 클래스

## 1. Java.lang 패키지

java.lang 패키지는 자바 프로그래밍에 가장 기본이 되는 클래스들을 포함하고 있다. java.lang 패키지의 클래스들은 import 문 없이도 사용할 수 있게 되어 있다.

### 1.1 Object 클래스

Object 클래스는 모든 클래스의 최고 조상이기 때문에 Object 클래스의 멤버들은 모든 클래스에서 바로 사용 가능하다. Object 클래스는 멤버변수는 없고 오직 11개의 메서드만 가지고 있다.

#### equals(Object obj)

매개변수로 객체의 참조 변수를 받아서 비교하여 그 결과를 boolean 값으로 알려주는 역할을 한다.

```java
public boolean equals(Object obj) {
	return (this == obj);
}
```

Object 클래스로부터 상속 받은 equals 메서드는 두 참조 변수에 저장된 값(주소값)이 같은지를 판단하는 기능밖에 할 수 없다.

String 클래스는 Object 클래스의 equals 메서드를 `오버라이딩` 해서 String 인스턴스가 갖는 문자열 값을 비교하도록 되어있다.

String 클래스뿐 아니라 Date, File, Wrapper 클래스(Integer, Double 등)의 equals 메서드도 주소값이 아닌 내용을 비교하도록 오버라이딩 되어 있다. StringBuffer 클래스는 오버라이딩 되어 있지 않다.

#### hashCode()

해싱(hashing) 기법에 사용되는 해시 함수(hash function)를 구현한 것이다. 해싱은 데이터 관리 기법 중의 하나인데, 다량의 데이터를 저장하고 검색하는 데 유용하다.

해시 함수는 찾고자 하는 값을 입력하면, 그 값이 저장된 위치를 알려주는 해시 코드(hash code)를 반환한다. Object 클래스에 정의된 hashCode 메서드는 `객체의 주소값을 이용해서 해시 코드를 만들어 반환하기 때문에 서로 다른 두 객체는 결코 같은 해시 코드를 가질 수 없다`.

```java 
// 객체의 주소값으로 해시 코드 생성
System.identityHashCode(Object x)
```

#### toString()

인스턴스에 대한 정보를 문자열(String)로 제공할 목적으로 정의된 메서드이다.

인스턴스의 정보를 제공한다는 것은 대부분 인스턴스 변수에 저장된 값들을 문자열로 표현한다는 것이다.

```java
public String toString() {
	return getClass.getName()+"@"+Integer.toHexString(hashCode());
}
```

#### clone()

자신을 복제하여 새로운 인스턴스 생성.

얕은 복사(실제 인스턴스의 주소는 같다.)만 이루어진다. 복제된 인스턴스의 작업이 원래의 인스턴스에 영향을 미치게 된다.

```java 
class Point implements Cloneable {
	int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "x=" + x + ", y=" + y;
	}

	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();  // clone은 받드시 예외처리를 해주어야 한다.
		} catch (CloneNotSupportedException e) {}
		return obj;
	}
}
```

clone()을 사용하려면 복제할 클래스가 Cloneable 인터페이스를 구현해야 하고, clone()을 오버라이딩 하면서 접근 제어자를 protected에서 public으로 변경해야 한다.

Cloneable 인터페이스를 구현한 클래스의 인스턴스만 clone()을 통한 복제가 가능한데, 그 이유는 `인스턴스의 데이터를 보호`하기 위해서이다. Cloneable 인터페이스가 구현되어 있다는 것은 클래스 작성자가 복제를 허용한다는 의미이다.

#### 공변 반환타입

Java 5부터 공변 반환타입(covariant return type)이라는 것이 추가되었는데, 이 기능은 `오버라이딩 할 때, 조상 메서드의 반환 타입을 자손 클래스의 타입으로 변경을 허용`하는 것이다.

```java
public Point clone() {  // 반환 타입을 Object에서 Point로 변경
	Object obj = null;

	try {
		obj = super.clone();
	} catch (CloneNotSupportedException e) {}
	return (Point)obj;  // Point 타입으로 형변환한다.
}
```

공변 반환타입을 사용하면 조상 타입이 아닌 실제로 반환되는 자손 객체의 타입으로 반환할 수 있어 `번거로운 형변환이 줄어든다` 는 장점이 있다.

```java
Point copy = (Point) original.clone() ==> Point copy = original.clone();

// 배열 복사 방법 1
int[] arr = {1, 2, 3, 4, 5};
int[] arrClone = arr.clone();

// 배열 복사 방법 2 
int[] arr = {1, 2, 3, 4, 5};
int[] arrClone = new int[arr.length];  // 배열을 생성하고
System.arraycopy(arr, 0, arrClone, 0, arr.length);  // 내용을 복사한다.
```

#### 얕은 복사와 깊은 복사

clone()은 단순히 객체에 저장된 값을 그대로 복제할 뿐, 객체가 참조하고 있는 객체까지 복제하지는 않는다.

- **얕은 복사** — 원본과 복제본이 같은 객체를 공유
- **깊은 복사** — 원본이 참조하고 있는 객체까지 복사. 원본과 복사본이 서로 다른 객체를 참조. 원본의 변경이 복사본에 영향을 미치지 않는다.

![얕은 복사와 깊은 복사](../../assets/003-shallow-n-deep-copy.png)

#### 💡getClass()

자신이 속한 클래스의 Class 객체를 반환하는 메서드이다.

```java
public final class Class implements ... {  // Class 클래스
	// ...
}
```

Class 객체는 클래스의 모든 정보를 담고 있으며, 클래스 당 1개만 존재한다. `클래스 파일이 클래스 로더(ClassLoader)에 의해서 메모리에 올라갈 때 자동으로 생성`된다.

`클래스 로더는 실행 시에 필요한 클래스를 동적으로 메모리에 로드하는 역할을 한다.` 먼저 기존에 생성된 클래스 객체가 메모리에 존재하는지 확인하고, 있으면 객체의 참조를 반환하고 없으면 클래스 패스(classpath)에 지정된 경로를 따라서 클래스 파일을 찾는다. 못 찾으면 ClassNotFoundException이 발생하고, 찾으면 클래스 파일을 읽어서 Class 객체로 반환한다.

파일 형태로 저장되어 있는 클래스를 읽어서 Class 클래스에 정의된 형식으로 변환하는 것이다. 즉, `클래스 파일을 읽어서 사용하기 편한 형태로 저장해 놓은 것이 클래스 객체`이다.

#### Class 객체를 얻는 방법

```java
Class 객체에 대한 참조를 얻는 방법

Class cObj = new Card().getClass();  // 생성된 객체로부터 얻는 방법
Class cObj = Card.class;             // 클래스 리터럴(*.class)로부터 얻는 방법
Class cObj = Class.forName("Card");  // 클래스 이름으로부터 얻는 방법
```

forName()은 특정 클래스 파일, 예를 들어 데이터베이스 드라이버를 메모리에 올릴 때 주로 사용한다.

Class 객체를 이용하면 클래스에 정의된 멤버의 이름이나 개수 등, 클래스에 대한 모든 정보를 얻을 수 있기 때문에 Class 객체를 통해서 객체를 생성하고 메서드를 호출하는 등 보다 `동적인 코드`를 작성할 수 있다.

```java
Card c = new Card();
Card c = Class.class.newInstance();  // Class 객체를 이용해서 객체 생성. newInstance()는 InstantiationException이 발생할 수 있다.
```

reflection API — 동적으로 객체 생성하고 메서드 호출하는 방법

Class 클래스 — 클래스의 정보를 얻을 수 있는 많은 수의 메서드 정의되어 있다.
