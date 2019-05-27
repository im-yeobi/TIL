package ch7_oop_2.class_relation;

class DrawShape {
    public static void main(String[] args) {
        Point[] p = {
                new Point(100, 100),
                new Point(140, 50),
                new Point(200, 100)
            };

        Triangle t = new Triangle(p);
        Circle c = new Circle(new Point(150, 150), 50);

        t.draw();   // 삼각형을 그린다.
        c.draw();   // 원을 그린다.
    }
}

class Shape {   // 도형
    String color = "black";

    void draw() {
        System.out.printf("[color=%s]\n", color);
    }
}

class Point {   // 2차원 좌표에서의 점
    int x;
    int y;

    Point() {   // default constructor
        this(0 , 0);
    }
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    String getXY() {
        return  "(" + x + ", " + y + ")";   // x와 y값 문자열로 반환
    }
}

class Circle extends Shape {
    Point center;
    int r;

    Circle() {  // default constructor
        this(new Point(0, 0), 100);
    }
    Circle(Point center, int r) {
        this.center = center;
        this.r = r;
    }

    @Override   // 오버라이딩
    void draw() {
        System.out.printf("[center=(%d, %d), r=%d, color=%s]\n", center.x, center.y, r, color);
    }
}

class Triangle extends Shape {
    Point[] p;

    Triangle(Point[] p) {
        this.p = p;
    }

    @Override   // 오버라이딩
    void draw() {
        System.out.printf("[p1=%s, p2=%s, p3=%s, color=%s]\n", p[0].getXY(), p[1].getXY(), p[2].getXY(), color);
    }
}