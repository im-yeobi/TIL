package ch6_oop_1.parameter_constructor;

class Car {
    String color;
    String gearType;
    int door;

    Car() { }
    Car(String color) {
        this(color, "auto", 4);
    }
    Car(String c, String g, int d) {
        color = c;
        gearType = g;
        door = d;
    }

}

public class CardTest {
    public static void main(String[] args) {
        Car c1 = new Car();
        c1.color = "white";
        c1.gearType = "auto";
        c1.door = 4;

        Car c2 = new Car("white", "auto", 4);   // 매개변수를 갖는 생성자를 사용하는 것이 코드를 보다 간결하고 직관적으로 만든다.
        System.out.println("c1의 color = " + c1.color + ", gearType = " + c1.gearType + ", door = " + c1.door);
        System.out.println("c2의 color = " + c2.color + ", gearType = " + c2.gearType + ", door = " + c2.door);

        Car c3 = new Car("blue");
        System.out.println("c3의 color = " + c3.color + ", gearType = " + c3.gearType + ", door = " + c3.door);
    }
}
