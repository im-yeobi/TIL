package ch6_oop_1.tv;


public class TvTest {
    public static void main(String[] args) {
        Tv t;               // 참조변수 t 선언 => 메모리에 참조변수 t를 위한 공간 마련.
        t = new Tv();       // 인스턴스 생성 => 연산자 new로 인해 tv 클래스의 인스턴스가 메모리의 빈 공간에 생성.
        t.channel = 7;
        t.channelDown();
        System.out.println("현재 채널은 " + t.channel + " 입니다.");
    }
}
// 인서턴스는 참조변수를 통해서만 다룰 수 있으며, 참조변수의 타입은 인스턴스의 타입과 일치해야 한다.(추후 다형성을 통해 깊게 배움)
