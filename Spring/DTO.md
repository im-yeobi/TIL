### DTO 

VO와 DTO 둘 다 데이터를 저장하는 용도이다.

#### \# DTO (Data Transfer Object)
- 계층간 데이터 교환을 위한 객체를 말한다. (계층간의 데이터 교환은 View, Controller, Business Layer, Persistent Layer를 말한다.)
- VO와의 차이점으로, DTO는 같은 시스템에서 사용되는 것이 아닌 다른 시스템으로 전달하는 작업을 처리하는 객체이다. (`Layer 간의 통신 용도`로 오가는 객체.)
- 일반적인 흐름
>- 서버 측 : Database Column Data => DTO => API(JSON or XML) => Client
>- 클라이언트 측 : Server => API(JSON or XML) => DTO => View or Local Database System
- 프로퍼티(property)
>- 자바에서는 C#과 달리 프로퍼티 문법 제공하지 않기 때문에, getter/setter 직접 구현.
````
자바는 다양한 프레임워크에서 데이터 자동화 처리를 위해 리플렉션 기법을 사용한다. 데이터 자동화 처리에서 제일 중요한것은 표준규격이다. 
예를 들어, DTO 클래스에서 property가 name, age면 name, age의 키 값으로 들어온 데이터는 리플렉션 기법으로 setter를 실행시켜 데이터를 넣을 수 있다. 

중요한 것은, 우리가 setter를 요청하는 것이 아닌, 프레임워크단(우리 눈에 안 보이는)에서 setter가 실행된다는 점이다. 그로 인하여, Layer 간에 데이터를 넘길때는 DTO를 쓰면 편하다는 것이다. (데이터가 자동적으로 클래스화가 된다는 것이다.) 
form 에서 name 필드 값을 프로퍼티에 맞춰서 값을 다른 페이지로 넘겼을 시, 값을 받아야 할 페이지에서는 값을 하나씩 일일이 받는 것이 아니라 name 속성의 이름이랑 매칭되는 프로퍼티에 자동적으로 DTO가 인스턴스화 되어 PersonDTO를 자료형으로 값을 받을 수 있다는 것이다. 
결론적으로, key & value로 존재하는 데이터는 자동화 처리된 DTO로 변환되여 우리는 손 쉽게 데이터가 셋팅된 오브젝트를 받을 수 있다.
````
 
#### \# VO (Value Object)
- 데이터 그 자체로 의미있는 것을 담고 있는 객체이다.
- DTO와 비슷한 개념이나 차이점은 불변 클래스로, read only 속성을 가진다.
- 간단한 독립체(Entity)를 의미하는 작은 객체를 의미한다.
- `관계형 데이터베이스의 레코드에 대응`되는 자바클래스이다.

#### \# 정리
- DTO/VO의 사용을 명확하게 구분하기보다는, 개발자들과의 협의를 통해 일관된 방법을 사용하는 것이 좋을 것 같다. 
- 일반적으로, 외부 시스템과 데이터 통신을 할 경우 DTO, DB에서 가져오는 데이터는 VO로 정의해서 사용.


<br />

### References.
- [DTO와 VO란?](https://mommoo.tistory.com/61)
- [VO vs DTO](https://ijbgo.tistory.com/9)
