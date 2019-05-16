package ch6_oop_1.tv;

public class TvTest2 {
    public static void main(String[] args) {
        Tv t1 = new Tv();
        Tv t2 = new Tv();
        System.out.println("t1의 channel 값은 " + t1.channel + " 입니다");
        System.out.println("t2의 channel 값은 " + t2.channel + " 입니다");

        t1.channel = 7;     // t1이 가리키고 있는 인스턴스의 멤버변수 channel의 값을 7로 변경
        System.out.println("t1의 channel 값은 7로 변경하였습니다.");

        System.out.println("t1의 channel 값은 " + t1.channel + " 입니다");
        System.out.println("t2의 channel 값은 " + t2.channel + " 입니다");
    }
}