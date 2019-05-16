package ch6_oop_1.static_instance;

public class MyMath2 {
    long a, b;

    // 인스턴스 메서드
    long add() { return a + b; }
    long subtract() { return a - b; }
    double divide() { return a / b; }
    long multiply() { return a * b; }

    // 클래스 메서드
    static long add(long a, long b) { return a + b; }
    static long subtract(long a, long b) { return a - b; }
    static double divide(double a, double b) { return a / b; }
    static long multiply(long a, long b) { return a * b; }
}
