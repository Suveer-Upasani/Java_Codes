package Codes;

class A {
    final void show() {
        System.out.println("Final method in A");
    }
}

class B extends A {
    // void show() { }  // not allowed, will cause error
}

public class FinalMethodDemo {
    public static void main(String[] args) {
        B b = new B();
        b.show();
    }
}
