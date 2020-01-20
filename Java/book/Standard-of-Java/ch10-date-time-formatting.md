# [자바의 정석] Chapter 10. 날짜와 시간 & 형식화

## 1. 날짜와 시간

### 1.1 Calendar와 Date

Date는 날짜와 시간을 다룰 목적으로 JDK 1.0부터 제공되어온 클래스이다.

Date 클래스 기능 부족했기 때문에 Calendar 클래스 제공

Java 8부터 java.time 패키지로 기존의 단점 개선한 새로운 클래스 추가

#### Calendar와 GregorianCalendar

Calendar는 추상 클래스이기 때문에 직접 객체를 생성할 수 없고, 메서드를 통해서 완전히 구현된 클래스의 인스턴스를 얻어야 한다.

Calendar를 상속받아 완전히 구현한 클래스로는 GregorianCalendar와 BuddhistCalendar가 있다.

getInstance()는 시스템의 국가와 지역 설정을 확인해서 태국인 경우 BuddhistCalendar 인스턴스 반환, 그 외에는 GregorianCalendar 인스턴스 반환한다.

#### Date와 Calendar 간의 변환

```java
1. Calendar를 Date로 변환
	Calendar cal = Calendar.getInstance();
	Date d = new Date(cal.getTimeInMillis());  // Date(long date)
  // Date d = cal.getTime();

2. Date를 Calendar로 변환
	Date d = new Date();
	Calendar cal = Calendar.getInstance();
	cal.setTime(d);
```

Calendar의 경우 1970년 1월 1일을 기준으로 계산한다. 1970년 1월 1일 이전의 날짜에 대해 getTimeInMillis()를 호출하면 음수를 결과로 얻는다.


## 2. 형식화 클래스

java.text 패키지에 포함

숫자, 날짜, 텍스트 데이터를 일정한 형식에 맞게 표현할 수 있는 방법을 객체지향적으로 설계하여 표준화 하였다.

### 2.1 DecimalFormat

형식화 클래스 중에서 `숫자를 형식화` 하는데 사용

### 2.2 SimpleDateFormat

날짜를 출력하는 방법

DateFormat은 추상 클래스로 SimpleDateFormat의 조상이다.

```java
Date today = new Date();
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

// 오늘 날짜를 yyyy-MM-dd 형태로 변환하여 반환한다.
String result = df.format(today);

DateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일");

try {
	Date d = df.parse("2015년 11월 23일");
} catch (Exception e) {
	// 지정된 형식과 입력된 형식이 일치하지 않는 경우에 예외 발생
}
```

hasNextLine() — 줄바꿈 확인하여 있으면 true 반환

### 2.3 ChoiceFormat

특정 범위에 속하는 값을 문자열로 변환해준다.

```java
double[] limits = {60, 70, 80, 90};
// limits, grades 간의 순서와 개수를 맞추어야 한다.
String[] grades = {"D", "C", "B", "A"};

int[] scores = {100, 96, 88, 70, 52, 60, 70};
ChoiceFormat form = new ChoiceFormat(limits, grades);

for (int i = 0; i < scores.length; i++) {
	System.out.println(scores[i] + ":" + form.format(socres[i]));
}

// 출력
100:A
96:A
88:B
70:C
52:D
60:D
70:C
```

limits는 범위의 경계값을 저장한다. 경계값은 doubl형으로 반드시 모두 오름차순으로 정렬되어야 한다.

grades는 범위에 포함된 값을 치환한 문자열을 저장한다. 문자열의 개수는 경계값에 의해 정의된 범위의 개수와 일치해야 한다.

```java
// 패턴 
String pattern = "60#D|70#C|80<B|90#A";
```

패턴은 구분자로 '#'와 '<' 두 가지를 제공한다. '#'은 경계값을 포함시키지만, '<'는 포함시키지 않는다.

### 2.4 MessageFormat

데이터를 정해진 양식에 맞게 출력할 수 있도록 도와준다.

```java
String msg = "Name: {0} \\nTel: {1} \\nAge: {2} \\nBirthday: {3}";

Object[] arguments = {
	"이자바", "010", "20", "01-01"
};

String result = MessageFormat.format(msg, arguments);
System.out.println(result);

// 출력
Name: 이자바
Tel: 010
Age: 20
Birthday: 01-01
```

parse(String source)를 이용해서 출력된 데이터로부터 필요한 데이터만을 추출

```java
String[] data = {
	"INSERT INTO CUST_INFO VALUES ('이자바', '010', 27, '01-01')"
};

String pattern = "INSERT INTO CUST_INFO VALUES ({0}, {1}, {2}, {3})";
MessageFormat sf = new MessageFormat(pattern);

Object[] obj = sf.parse(data[0]);

// 출력
'이자바', '010', 27, '01-01'
```
