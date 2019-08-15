package ch7_oop_2.binding;

public class BindingTest {
    public static void main(String[] args) {
        Parent p = new Child(); // 다형성 부모참조변수가 자식 인스턴스 가리킨다.
        System.out.println(p.x);
        p.print();

        Child c = new Child();
        System.out.println(c.x);
        c.print();
    }
}

class Parent {
    int x = 100;

    void print() {
        System.out.println("Parent x = " + x);
    }
}

class Child extends Parent {
    int x = 10;

    @Override
    void print() {
        System.out.println("Child x = " + x);
    }
}
