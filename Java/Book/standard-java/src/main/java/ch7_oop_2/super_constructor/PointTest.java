package ch7_oop_2.super_constructor;

public class PointTest {
    public static void main(String[] args) {
        Point3D p3 = new Point3D();
        System.out.println("p3.x = " + p3.x);
        System.out.println("p3.y = " + p3.y);
        System.out.println("p3.z = " + p3.z);
    }
}

class Point {
    int x = 10;
    int y = 20;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    String getLocation() {
        return "x : " + x + ", y : " + y;
    }
}

class Point3D extends Point {
    int z = 30;

    Point3D() {
        this(100, 200, 300);
    }

    Point3D(int x, int y, int z) {
        super(x, y);  // super(x, y)를 명시하지 않으면 컴파일러는 자동적으로 super() 생성자를 첫 줄에 넣어준다. 하지만 Point class는 default 생성자가 없기 때문에 오류가 발생한다.
        this.z = z;
    }

    String getLocation() {
        return super.getLocation() + ", z : " + z;
    }
}
