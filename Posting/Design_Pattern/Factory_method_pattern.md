하나의 디자인 패턴을 1~2주 동안 공부하고, 내용을 정리하여 꾸준히 포스팅 할 예정입니다. 부족하거나 틀린 부분에 대한 피드백은 언제든 환영합니다. 🙆‍♂️ 

부디 목표한 곳까지 갈 수 있기를..🏄‍♂️

## 팩토리가 대체 뭘까 ?

팩토리란 말 그대로 공장을 의미합니다. 프로그래밍에서 공장이란 특정 객체의 `인스턴스 생성을 담당하는 객체`를 의미합니다. 

자바에서는 new 연산자를 이용해 객체의 인스턴스를 생성하지요. 팩토리는 클라이언트(팩토리에게 인스턴스 생성을 요청한 객체)에서 사용할 `객체를 직접 만들어 반환`하는 역할을 수행합니다.

클라이언트에서는 사용 할 객체가 구체적으로 어떤 구상 클래스(구상 클래스란 인터페이스의 구현체를 의미하기도 하고, 추상 클래스를 확장한 서브 클래스를 의미하기도 한다. )인지 정확히 알지 못하고, 알 필요도 없습니다. 팩토리로부터 받은 객체를 이용해 추가 작업을 하기만 하면 되니까요.

팩토리를 이용해 캡슐화 된 객체 생성부는 클라이언트 코드와 명확하게 구분됩니다. 이렇게 팩토리를 만들어 놓으면 여러 개의 클라이언트에서 사용할 수 있습니다. 또한, 인스턴스를 생성해야 할 객체가 늘어나도 팩토리만 수정하면 되기 때문에, 변경에 유연한 구조가 됩니다.

예제를 활용해 앞에서 설명한 팩토리를 직접 구현 해보겠습니다.

## 선물 주문

주문 받은 선물을 발송하기 위해 몇 가지 작업이 필요합니다. 

- (1) 선물 종류별로 포장, 검수, 발송 방법이 모두 다릅니다. 우선 종류에 따라 선물을 창고에서 가져와야(인스턴스 생성) 합니다.
- (2) 가져온 선물을 검수하고, 포장한 뒤 발송하는 프로세스로 선물 주문이 마무리 됩니다.

### 선물 종류 확인 후 창고에서 가져오기

    Gift gift;
    
    if (type.equals("jewelry") {
        gift = JewelryGift();
    } else if (type.equals("toy") {
        gift = ToyGift();
    } else if (type.equals("electronic") {
        gift = ElectronicGift();
    }

### 가져온 선물 검수, 포장, 발송

    if (!gift.check()) {
        // 선물에 하자가 있으면
        return null;
    }
    gift.wrap(); // 포장
    gift.send(); // 발송

### [1 단계] 클라이언트 코드 안에 객체 생성부가 있는 경우

앞에 작성한 두 코드를 합쳐 보겠습니다. 

    public class GiftSender {
    
        public Gift send(String type) {
            // 상품 종류 확인 후 창고에서 선물 가져오기
            Gift gift;
    
            // 객체 생성부
            if (type.equals("jewelry") {
                gift = new JewelryGift();
            } else if (type.equals("toy") {
                gift = new ToyGift();
            } else if (type.equals("electronic") {
                gift = new ElectronicGift();
            } else {
                // type에 맞는 선물 없는 경우
                return null;
            }
    
            if (!gift.check()) {
                // 상품 하자가 있으면
                return null;
            }
            gift.wrap(); // 포장
            gift.send(); // 발송
        }
    
    }

창고에서 선물 가져오기(객체 생성부), 검수/포장/발송(클라이언트 코드)가 함께 있습니다.

이렇게 객체 생성부와 클라이언트 코드가 함께 있는 경우에 어떤 문제가 있는지 고민 해봅시다.

저는 크게 두 가지 문제가 있다고 생각합니다.

- 첫째, `변경에 대하여 열려있다`.
    - toy 타입의 선물이 많이 나가지 않아 더 이상 주문을 받지 않는다고 해보죠.
    - 다음 예시처럼 구상 클래스에 변경이 일어날 때마다 GiftSender의 구현을 수정해야 합니다.
    - 비단 GiftSender뿐 아니라 다른 클라이언트에서 Gift의 구상 클래스 생성부가 존재하면 똑같이 수정 작업이 필요합니다.

    > ~~else if (type.equals("toy") {~~
    >
    >    ~~gift = new ToyGift();~~
    >     
    > ~~}~~

- 둘째, 다른 클라이언트에서도 Gift의 인스턴스가 필요할 때 `중복이 발생`합니다.
    
    - Gift의 구상 클래스 인스턴스 생성을 위한 코드가 중복되기 때문에 문제가 발생합니다.

### [2 단계] 그렇다면 어떻게

디자인 원칙에 따라 바뀔 수 있는 부분을 찾아 `바뀌지 않는 부분과 분리`시켜야 합니다. 예제에서 바뀔 가능성이 있는 부분은 `창고에서 선물 가져오기`(객체 생성부)겠죠. 해당 부분을 팩토리로 분리 해보겠습니다.

    public class GiftSender {
    
        private GiftFactory giftFactory;
    
        // 생성자 주입
        public GiftSender(GiftFactory giftFactory);
    
        public Gift send(String type) {
            // 팩토리로부터 인스턴스 받아오기
            Gift gift = giftFactory.getGift(type);
    
            if (!gift.check()) {
                // 상품 하자가 있으면
                return null;
            }
            gift.wrap(); // 포장
            gift.send(); // 발송
        }
    
    }


​    
    public class GiftFactory {
    
        public Gift getGift(String type) {
            // 객체 생성부
            if (type.equals("jewelry") {
                gift = new JewelryGift();
            } else if (type.equals("toy") {
                gift = new ToyGift();
            } else if (type.equals("electronic") {
                gift = new ElectronicGift();
            } else {
                // type에 맞는 선물 없는 경우
                return null;
            }
        }
    
    }

Gift의 구상 클래스 인스턴스 생성부를 GiftFactory라는 팩토리 객체로 분리하였습니다. 클라이언트 코드에서는 팩토리로부터 구상 클래스의 인스턴스를 받아 본래 절차 그대로 검수/포장/발송을 진행합니다.

이제 1 단계의 두 가지 문제점은 해결 할 수 있습니다.

- 변경에 열려있다.
    - 구상 클래스의 변경 및 추가가 발생해도, 클라이언트 코드인 GiftSender는 바뀌지 않습니다. 팩토리만 수정하면 되기 때문입니다.
- 인스턴스 생성 코드 중복
    - 여러 클라이언트에서 Gift의 인스턴스가 필요한 경우, 객체 생성부를 직접 구현하지 않고 팩토리에게 인스턴스를 요청 하기 때문에 코드 중복이 발생하지 않습니다.
