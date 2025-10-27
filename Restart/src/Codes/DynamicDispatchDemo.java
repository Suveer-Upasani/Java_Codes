package Codes;

class Shape {
    void draw() {
        System.out.println("Drawing Shape");
    }
}

class Circle extends Shape {
    void draw() {
        System.out.println("Drawing Circle");
    }
}

class Square extends Shape {
    void draw() {
        System.out.println("Drawing Square");
    }
}

public class DynamicDispatchDemo {
    public static void main(String[] args) {
        Shape shape;

        shape = new Circle();   
        shape.draw();

        shape = new Square();   
        shape.draw();
    }
}
