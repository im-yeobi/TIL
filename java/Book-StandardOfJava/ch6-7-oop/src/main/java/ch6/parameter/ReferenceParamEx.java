package ch6.parameter;

public class ReferenceParamEx {
    public static void main(String[] args) {
        Data d = new Data();
        d.x = 10;
        System.out.println("main() : x = " + d.x);

        change(d);  // 값이 저장된 주소를 넘겨줌. main 메서드의 참조변수 d와 change 메서드의 참조변수 d는 같은 객체를 가리킨다.
        System.out.println("After change(d)");
        System.out.println("main() : x = " + d.x);
    }

    static void change(Data d) {
        d.x = 1000;
        System.out.println("change() : x = " + d.x);
    }
}
