package ch7_oop_2.interface_sample1;

public class ObjectTest {
    public static void main(String[] args) {
        MyInterface myInterface = new MyClass();
        System.out.println(myInterface.toString()); // 인터페이스에 toString 구현되어 있지 않지만, 모든 객체는 Object클래스에 정의된 메서드를 가지고 있을 거이기 떄문에 허용된다.
    }
}

interface MyInterface {

}

class MyClass implements MyInterface {

}