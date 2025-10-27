package Codes;

class Outer_Class {
    void display() {
        System.out.println("Outer Class created ");
    }

    class Inner_Class {
        void display() {
            System.out.println("Inner Class created");
        }
    }
}

public class Inner_class_test {
    public static void main(String[] args) {
        Outer_Class outer = new Outer_Class();
        outer.display();
        Outer_Class.Inner_Class inner = outer.new Inner_Class();
        inner.display();
    }
}
