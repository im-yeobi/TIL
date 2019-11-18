## 클린코더

단순 기술자에서 진정한 소프트웨어 장인이 되기까지

### 저자

로버트 마틴(Robert C.Martin). 한국에서는 밥 아저씨로 많이 알려져 있다고 한다.

아샬님의 [책 소개 영상](https://www.youtube.com/watch?v=ZzBD_X9W9WI)을 보고 클린코더를 처음 접하였다. 

책 소개 그대로 프로 개발자로 가기 위한 길을 알려준다. 어떻게? 밥 아저씨가 개발자로 살아오면서 겪은 히스토리를 독자에게 들려주면서 프로의 길로 인도한다.

    이 책은 내가 잘못한 일의 목록, 내가 저지른 범죄의 사건일지, 
    내가 초년생 때 했던 실수를 독자들이 피할 수 있도록 만들어주는 안내서로 생각하자.
    
    -미리 읽어두기(p.43)-

어떻게 하면 더 좋은 개발자가 될 수 있을지 매일 고민하는 나에게, 단비와 같은 책이라 생각하여 바로 구매를 하였다.

클린코더를 읽고 중요한 부분, 기억하고 싶은 내용,  추가로 나의 생각을 정리 해보았다.


## 1장. 프로의 마음가짐

### QA는 아무것도 찾지 못해야 한다

    QA가 문제를 찾지 못할 것이라고 어느 정도 자신할 수 있어야 한다. 
    코드에 결함이 있는 걸 알면서도 QA에게 코드를 보내는 일은 매우 프로답지 못한 행동이다.
    어떤 이들은 QA를 오류를 찾는 용도로 사용한다.
    QA 혹은 사용자가 문제를 찾을 때마다, 개발자는 놀라움과 분함을 느껴야 마땅하다.
    -프로의 마음가짐(p.51)-

QA는 오류를 찾는 작업이라고 알고 있었다. 개발자가 QA를 요청하기 전에 오류가 없는지 테스트 하는 것은 당연한 것이지만, 예기치 못한 오류는 발생할 수 있다고 생각했다. 밥 아저씨는 프로 개발자라면 QA가 오류를 찾아줄 것이라는 무책임한 생각을 해서는 안 된다고 말한다. `테스트하고 또 테스트 해야 한다`.

### 제대로 작동하는지 아닌지 알아야 한다

    테스트하고 또 테스트하라.
    이렇게도 테스트하고 저렇게도 테스트하라.
    월, 화, 수, 목, 금, 토, 일 일곱 가지 방식으로 테스트하라.
    테스트를 자동화해야 한다.
    작성한 코드는 한 줄도 빠짐없이 전부 테스트해야 한다.
    작성한 코드는 당연히 실행된다. 실행된다면 제대로 돌아가는지 알아야 한다. 알 수 있는 방법은 테스트뿐이다.
    -제대로 작동하는지 아닌지 알아야 한다(p.51)-

모든 코드에 대한 `테스트 코드`를 만들고, 작성한 코드를 계속해서 검증해야 한다.

### 구조에 해를 끼치지 마라

    구조가 좋아야 코드가 유연해진다.
    소프트웨어는 변한다라는 생각은 모든 소프트웨어 프로젝트의 기본 가정이다.
    코드를 조금 빠구는 일은 언제 해야 할까? 항상 해야 한다!
    코드를 읽을 때마다 구조를 바꿔야 한다.
    정말 위험한 일은 소프트웨어를 고정된 상태로 두는 일이다.
    왜 개발자들은 코드 바꾸기를 무서워할까? 코드를 망가트릴까봐 겁이 나서다! 왜 코드를 망칠까봐 겁이 날까? 테스트가 없기 때문이다.
    -구조에 해를 끼치지 마라(p.54)-

테스트 코드의 중요성에 대해서 한없이 강조한다. 리팩토링은 언제 하면 될까? 테스트 코드가 왜 필요할까? 에 대한 정답을 제시해준다. 테스트 코드를 제대로 만들지 않았기 때문에 내가 리팩토링을 두려워 했구나.. 너무 와닿는 말이다.

### 전산 분야 지식을 익혀라

    프로가 되고 싶다면 지식을 익혀 큼지막한 덩어리를 만든 다음 그 덩어리를 계속 키워야 한다.
    프로 소프트웨어 개발자라면 알아야 하는 최소한의 기술 목록
    - 디자인 패턴: 24가지 GOF 패턴을 설명할 수 있고, POSA 패턴을 실무에 적용할 수준으로 알아야 한다.
    - 설계 원칙: SOLID 객체지향 원칙을 알아야 하고 컴포넌트 개념을 충분히 이해해야 한다.
    - 방법론: XP, 스크럼, 린, 칸반, 폭포수, 구조적 분석, 구조적 설계 개념을 충분히 이해해야 한다.
    - 원칙: 테스트 주도 개발(TDD), 객체지향 설계, 구조적 프로그래밍, 지속적 통합, 짝 프로그래밍을 실천해야 한다.
    - 도구: UML, 데이터 흐름도(DFD), 구조 차트(Structure Chart), 페트리 넷(Petri Net), 상태전이 다이어그램과 테이블(State Transition Diagram and Table),
           흐름도(flow chart), 결정 테이블(decision table)을 어떻게 쓰는지 알아야 한다.
    -전산 분야 지식을 읽혀라(p.56)-

프로 개발자가 되기 위한 길을 멀고도 멀다. 하나하나 지식을 쌓아가자.


## 2장. 아니라고 말하기

프로라면 권위에 맞서 진실을 말해야 한다. 프로는 관리자에게 아니라고 말하는 용기를 가져야 한다. 프로는 아니라고 말해야 마땅하다. 아니라고 말하는 일이야 말로 맡은 작업을 완료하는 유일한 길이다.

### 코드 임파서블

일화를 통해 빠듯한 개발 일정 때문에 코드의 품질을 포기하고, 결과물을 내기 위해 노력하는 개발자의 상황을 소개하였다.

    "훌륭한 코드는 불가능한가?"라고 물었는데, 사실은 "프로답게 사는 것은 불가능한가?"라고 묻는 것이다.
    우리 모두 명심해야 할 일은 예라는 대답은 프로로서 가져야 할 원칙을 포기할 뿐 아니라, 문제 해결에도 도움이 안 된다는 사실이다.
    "좋은 코드는 불가능한가? 프로다운 일 처리는 불가능한가?" => 나의 답변은 다음과 같다 "아니요, 가능합니다."
    -코드 임파서블(p.87)-

처음부터 불가능한 개발 일정을 받았을 때, 고객을 위해, 상사를 위해 `"예"`라고 하는 것이 얼마나 잘못 되었는지 설명해준다. 코드의 품질을 떨어뜨리고 결과에만 치중한다고 해서, 좋은 프로그램이 나오지는 않는다.


## 3장. 예라고 말하기

### 노력의 또 다른 면

    문서 작업은 몇 시간 걸리니까 월요일에 끝낼 수도 있어요. 하지만 화요일까지 늦어질지도 몰라요.
    불확실성을 숨기면 오히려 감당하기 힘들다.
    -노력의 또 다른 면(p.100)-

명확하게 "언제까지 끝낼 수 있다."라고 말하는 것이 협업하는 사람들을 위한 개발자의 자세라고 생각했다. 내가 모호하게 말하면 함께 일하는 사람들이  답답해 하지 않을까? 현재의 나는 어떤 게 더 나은 방법인지 확신하지 못하겠다. 

### 원칙을 가지고 의사소통 하기

    여기가 바로 프로가 선을 그어야 할 자리다. 피터의 가정은 틀렸다.
    테스트를 작성하지 않아도 빨리 끝나지 않는다. 리팩토링을 하지 않아도 빨리 끝나지 않는다. 전체 회귀 테스트 묶음을 생략해도 빨리 끝나지 않는다.
    수년간의 경험을 통해 원칙을 깨면 느려질 뿐이라는 것을 알게 됐다.
    -원칙을 가지고 의사소통 하기(p.102)-

원칙을 깨고 일정을 맞추기 위해 테스트 코드, 리팩토링을 무시했던 경험을 반성하게 된다..

    프로는 모든 업무 요청에 예라고 대답할 필요가 없다.
    프로가 예라고 대답할 때는 약속을 뜻하는 언어를 사용해서 내뱉는 말에 모호한 부분이 없도록 해야 한다.
    -결론(p.103)-


## 4장. 코딩

4장에서는 밥 아저씨가 코드를 짤 때 행동과 기분, 태도에 대한 규칙과 원칙을 설명한다.

    오류를 느끼는 감각은 정말 중요하다. 
    오류 감각을 가진다는 사실은 피드백 루프를 재빨리 끝내 오류에서 배움을 얻는 일이 더 빨라진다는 뜻이다.

### 준비된 자세

    1. 코드는 반드시 동작해야 한다.
    2. 코드는 고객이 제시한 문제를 반드시 풀어야 한다.
    3. 코드는 기존 시스템에 잘 녹아들어야 한다. (코드는 견고한 객체지향 원칙을 따라야 한다.)
    4. 코드는 다른 프로그래머가 읽기 쉬워야 한다.
    -준비된 자세(p.107)-

### 새벽 3시에 짠 코드

    지쳤을 때는 코드를 만들지 마라. 
    헌신과 프로다운 모습은 무턱대고 많이 일하는 데서가 아니라 원칙을 지키는 모습에서 나온다.
    -코딩(p.109)-

컨디션 관리는 항상 하자. 하루 밤새고 코딩 한다고 실력이 늘거나, 좋은 결과물이 나오는 것이 아니다.

### 외부 방해

    사무실에서 코딩하는 자신의 모습을 그려보자. 다른 사람이 질문하면 어떻게 반응하는가?
    방해가 찾아오면(외부 요청), 다음 번에는 자신이 남을 방해할 필요가 있을지도 모른다는 사실을 기억하라.
    그래서 프로다운 태도로 예의 바르게 기꺼이 도와야 한다.
    -외부 방해(p.113)-

코딩에 한창 집중하고 있을 때, 누군가 옆에서 말을 걸거나 요청을 하면 나는 어떻게 행동했지? 항상 좋게 행동한 것은 아닐거다. 물론, 상대방의 상황을 봐가면서 도움을 요청하는 것이 우선이다.

### 창의적인 입력

    나는 이것저것 가리지 않고 닥치는 대로 읽는 편이다.
    소프트웨어, 정치, 생물학, 천문학, 물리학, 화학, 수학 외에도 여러 많은 분야의 글을 읽는다.
    -창의적인 입력(p.114)-

특정 분야를 가리지 않고 접하기 위해 노력해야 한다. 나는 창의적인 사람인가? 아닌 것 같다. 그럼에도 노력해야 한다.

### 디버깅 시간

    테스트 주도 개발 원칙을 받아들임으로써 엄청나게 디버깅 시간을 단축했다.
    마찬가지로 오류를 만드는 소프트웨어 개발자는 프로답지 않다.
    -디버깅 시간(p.118)-

지금 회사에서 개발을 하면서 오류를 냈던 경험을 떠올려 보자. 개발을 끝내고 이미 배포가 된 코드에서 작은 오류가 발견돼 수정을 한 경험이 몇 번 있다. 그런 상황이 왜 발생했는지 복기해봐야 한다. 꼼꼼하지 못해서 인가? 테스트 코드를 작성 했더라면 예외 케이스를 대비할 수 있지 않았을까?

### 일정을 못 지키다

    언젠가는 마감을 못 지키는 날이 온다.
    정기적으로 목표 대비 진척을 측정하고 사실을 바탕으로 한 세 가지 완료일자를 마련하라.
    최선의 경우, 최악의 경우, 명목 추정치(성공 가능성이 가장 높은)
    -일정을 못 지키다(p.120)-

`최선의 경우`, `최악의 경우`, `명목 추정치(성공 가능성이 가장 높은)`

### 질주

    이미 가능한 선택지를 모두 고려했고 일정을 개선할 유일한 길은 범위를 줄이는 방법뿐임을 상사에게 말하라.
    압박에 굴복해 허리띠를 졸라매고 마감일을 지키려 노력해 보겠다고 동의하는 형편없는 개발자를 두려워하라.
    개발자들은 손쉬운 길로만 가려 하고 초과 근무를 하며 기적을 바라는 헛된 희망을 가지게 되는데 이는 스스로와 팀, 이해 관계자들에게 잘못된 희망을 주기 때문에 재앙으로 가는 조리법이다.
    질주하는 방법은 없다. 스스로 코딩을 더 빨리 하게 만들 수 없다. 문제를 더 빨리 풀게 만들 수 없다.
    상사, 팀, 관계자에게 반드시 정확하게 대답해서 희망을 갖지 못하게 해야 한다.
    -질주(p.121)-

개발자에게 일정은 매우 중요하다. 주니어 때부터 개발 일정을 관리하는 연습을 해야 한다. 회사에서 일을 할 때 업무에 소요되는 예상 시간과, 실제 소요 시간을 매번 기록한다. 좀 더 세부적으로 업무를 쪼개고, 시간 관리를 구체적으로 기록하도록 노력해야 한다.