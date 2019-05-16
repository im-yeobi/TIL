package ch6_oop_1.static_instance;

public class MyMathTest2 {
    public static void main(String[] args) {
        // 클래스 메서드 호출
        System.out.println(MyMath2.add(200L, 100L));
        System.out.println(MyMath2.subtract(200L, 100L));
        System.out.println(MyMath2.multiply(200L, 100L));
        System.out.println(MyMath2.divide(200.0, 100.0));

        MyMath2 mm = new MyMath2();
        mm.a = 200L;
        mm.b = 100L;
        System.out.println(mm.add());
        System.out.println(mm.subtract());
        System.out.println(mm.divide());
        System.out.println(mm.multiply());
    }
}
