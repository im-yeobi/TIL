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


## 3. java.time 패키지

Java 8 부터 java.time 패키지 추가

기존 Calendar 클래스는 변경 가능하므로 멀티 스레드 환경에서 안전하지 못하다. 멀티 스레드 환경에서는 동시에 여러 스레드가 같은 객체에 접근할 수 있기 때문에, 변경 가능한 객체는 데이터가 잘못될 가능성이 있다. (`thread-saf`)

### 3.1 java.time 패키지의 핵심 클래스

java.time 패키지에서는 날짜와 시간을 별도의 클래스로 분리

시간 표현 — LocalTime 클래스

날짜 표현 — LocalDate 클래스

날짜와 시간 모두 표현 — LocalDateTime 클래스

날짜와 시간을 초단위로 표현한 값을 `타임스탬프(time-stamp)`라고 한다. `타임스탬프는 날짜와 시간을 하나의 정수로 표현할 수 있으므로 날짜와 시간의 차이를 계산하거나 순서를 비교하는 데 유리해서 데이터베이스에 많이 사용된다.`

#### Period와 Duration

Period — 두 날짜간의 차이를 표현

Duration — 시간의 차이를 표현

```java
날짜 - 날짜 = Period
시간 - 시간 = Duration
```

객체 생성하기 — now(), of()

java.time 패키지에 속한 클래스의 객체를 생성하는 가장 기본적인 방법은 now()와 of()를 사용하는 것이다.

now() — 현재 날짜와 시간을 저장하는 객체를 생성한다.

```java
LocalDate date = LocalDate.now();  // 2020-01-23
LocalTime time = LocalTime.now();  // 20:12:01.424
LocalDateTime dateTime = LocalDateTime.now();  // 2020-01-23T20:12:01.424
ZonedDateTime dateTimeInKr = ZonedDateTime.now();  // 2020-01-23T20:12:01.424+09:00[Asia/Seoul]
```

of() — 해당 필드의 값을 순서대로 지정

```java
LocalDate date = LocalDate.of(2020, 1, 23);  // 2020년 01월 23일
LocalTime time = LocalTime.of(23, 59, 59);  // 23시 59분 59초
LocalDateTime dateTime = LocalDateTime.of(date, time);
ZonedDateTime zDateTime = ZonedDateTime.of(dateTime, ZoneId.of("Asia/Seoul"));
```

#### Temporal과 TemporalAmount

```java 
Temporal, TemporalAccessor, TemporalAdjuster를 구현한 클래스
  - LocalDate, LocalTime, LocalDateTime, ZonedDateTime, Instant 등

TemporalAmount를 구현한 클래스
  - Period, Duration
```

### 3.2 LocalDate와 LocalTime

LocalDate와 LocalTime은 java.time 패키지의 가장 기본이 되는 클래스이다.

```java
LocalDate birthDate = LocalDate.parse("1999-12-31");
LocalTime birthTime = LocalTime.parse("23:59:59");
```

#### 특정 필드의 값 가져오기 — get(), getXXX()

월요일 : 1, 일요일 : 7

1월 : 1, 12월: 12

#### 필드의 값 변경하기 — with(), plus(), minus()

with()를 사용하면, 원하는 필드를 직접 지정할 수 있다.

필드를 변경하는 메서드들은 `항상 새로운 객체를 생성해서 반환`하므로 아래와 같이 대입 연산자를 같이 사용해야 한다.

#### 날짜와 시간의 비교 — isAfter(), isBefore(), isEqual()

LocalDate와 LocalTime도 compareTo()가 적절히 오버라이딩 되어 있어서 비교 가능하다.

```java
// 보다 편리하게 비교할 수 있는 메서드 추가

boolean isAfter(ChronoLocalDate other)
boolean isBefore(ChronoLocalDate other)
boolean isEqual(ChronoLocalDate other)
```

isEqual()과 equals()의 차이

- isEqual() 연표(chronology)가 다른 두 날 짜 비교. 오직 날짜만 비교
- equals() 모든 필드 일치하는지 비교

### 3.3 Instant

Instant는 에포크 타임(EPOCH TIME, 1970-01-01 00:00:00 UTC)부터 경과된 시간을 나노초 단위로 표현한다.

UTC는 'Coordinated Universal Time'의 약어로 '세계 협정시'라고 한다. 1972년 1월 1일부터 시행된 국제 표준시이다.

#### Instant와 Date 간의 변환

Java 8부터 Date에 Instant로 변환할 수 있는 메서드 추가

```java
static Date from(Instant instant);  // Instant => Date
Instant toInstant();  // Date => Instant
```

### 3.4 LocalDateTime과 ZonedDateTime

LocalDateTime에 시간대(time zone)을 추가한 것이 ZonedDateTime이다.

```java
LocalDate + LocalTime = LocalDateTime
LocalDateTime + 시간대 = ZonedDateTime
```

### 3.5 TemporalAdjusters

자주 쓰일만한 날짜 계산들을 대신 해주는 메서드를 정의해놓은 것이 TemporalAdjusters 클래스이다.

### 3.6 Period와 Duration

Period는 날짜의 차이

Duration은 시간의 차이

```java
날짜 - 날짜 = Period
시간 - 시간 = Duration
```

#### between()

두 날짜 date1과 date2의 차이를 나타내는 Period는 between()으로 얻을 수 있다.

```
LocalDate date1 = LocalDate.of(2019, 1, 1);
LocalDate date2 = LocalDate.of(2020, 1, 1);

Period pe = Period.between(date1, date2);
// date1이 date2보다 날짜 상으로 이전이면 양수로, 이후면 음수로 Period에 저장된다.
```

#### until()

between()과 거의 같은 일을 한다. between()은 static 메서드이고, until()은 인스턴스 메서드라는 차이가 있다.

### 3.7 파싱과 포맷

날짜와 시간을 원하는 형식으로 출력하고 해석(파싱, parsing).

#### 출력 형식 직접 정의하기

- ofPattern()으로 원하는 출력 형식 직접 작성

#### 문자열을 날짜와 시간으로 파싱하기

- static 메서드 parse() 사용
