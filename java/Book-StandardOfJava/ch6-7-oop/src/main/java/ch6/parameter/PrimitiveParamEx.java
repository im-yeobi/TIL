package ch6.parameter;

public class PrimitiveParamEx {
    public static void main(String[] args) {
        Data d = new Data();
        d.x = 10;
        System.out.println("main() : x = " + d.x);

        change(d.x);
        System.out.println("After change(d.x)");
        System.out.println("main() : x = " + d.x);  // 값 변하지 않는다.
    }

    static void change(int x) {
        x = 1000;
        System.out.println("change() : x = " + x);
    }
}
