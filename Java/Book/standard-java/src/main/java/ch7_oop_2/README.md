### 객체지향언어

### Chapter 7. 객체지향언어 프로그래밍 2

#### \# 상속
기존의 클래스를 재사용하영 새로운 클래스를 작성하는 것이다. 상속을 통해 코드를 공통적으로 관리할 수 있기 때문에, 코드의 재사용성을 높이고 코드의 중복을 제거할 수 있다.
 ````java
class Child extends Parent {
    // ...
}
````
 - 조상 클래스 : 부모(parent)클래스, 상위(super)클래스, 기반(base)클래스
 - 자손 클래스 : 자식(child)클래스, 하위(sub)클래스, 파생된(derived)클래스
 - 상속을 받는다는 것은 조상 클래스를 확장(extend)한다는 의미로 해석할 수도 있으며, 이것이 상속에 사용되는 키워드가 'extends'인 이유이기도 하다.
 - 생성자와 초기화 블럭은 상속되지 않는다. 멤버만 상속된다. 
 - 접근 제어자가 private 또는 default인 멤버들은 상속되지 않는다기보다 상속은 받지만 자손 클래스로부터의 접근이 제한되는 것이다.
 - 자손 클래스의 인스턴스를 생성하면, 조상 클래스의 멤버와 자손 클래스의 멤버가 합쳐진 하나의 인스턴스가 생성된다.
 
 #### \# 포함관계
 한 클래스의 멤버변수로 다른 클래스 타입의 참조변수를 선언하는 것. 
````java
class Circle {
    Point c = new Point();  // 포함관계
    int r;
}

class Point {
    int x;
    int y;
}
````
단위 클래스 별로 코드가 작게 나뉘어 작성되어 있기 때문에 코드를 관리하는 데도 수월하다.

#### \# is-a , has-a
- 상속관계 : '~은 ~이다.' `is-a`
- 포함관계 : '~은 ~을 가지고 있다.' `has-a`

#### \# 단일 상속 (single inheritance)
- 자바에서는 단일 상속만 허용한다.
- 다중 상속은 클래스간의 관계가 매우 복잡해진다는 단점을 가지고 있다. 또한, 서로 다른 클래스로부터 상속받은 멤버간의 이름이 같은 경우 구별할 수 있는 방법이 없다는 단점을 가지고 있다.
- 단일 상속은 클래스 간의 관계가 보다 명확해지고 코드를 더욱 신뢰할 수 있게 만들어준다.

#### \# Object 클래스 - 모든 클래스의 조상
- Object 클래스는 모든 클래스 상속계층도의 최상위에 있는 조상클래스이다.
- 다른 클래스로부터 상속을 받지 않는 클래스는 컴파일러에 의해 자동적으로 Object 클래스를 상속받는다.
````java
class Tv extends Object {
    // ...
}
````
- 자바의 모든 클래스들은 Object 클래스의 멤버들을 상속 받기 때문에, Object 클래스에 정의된 멤버들을 사용할 수 있다. (toString(), equals() 등..)

#### \# 오버라이딩 (overriding)
- 조상 클래스로부터 상속받은 메서드의 내용을 변경하는 것.
```java
class Point {
    int x;
    int y;
    
    String getLocation() {
        return "x : " + x + ", y : " + y;
    }
}

class Point3D extends Point {
    int z;
    
    // overriding
    String getLocation() {
        return "x : " + x ", y : " + y + ", z : " + z;
    }
}
``` 
- 오버라이딩 조건
>- 조상 클래스의 메서드와 이름이 같아야 한다.
>- 조상 클래스의 메서드와 매개변수가 같아야 한다.
>- 조상 클래스의 메서드와 반환타입이 같아야 한다. (JDK1.5 부터 공변 반환타입이 추가되어, 반환타입을 자손 클래스의 타입으로 변경하는 것은 가능하도록 조건이 완화되었다.)
>- `접근제어자를 조상 클래스의 메서드보다 좁은 범위로 변경 할 수 없다.`
>- `조상 클래스의 메서드보다 많은 수의 예외를 선언할 수 없다.` (예외의 개수가 아닌, 같은 예외인 경우를 말하는 것.)
>- 인스턴스 메서드를 static 메서드로 또는 그 반대로 변경할 수 없다.