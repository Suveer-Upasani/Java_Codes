package Codes;

class Animal{
	Animal(){
	System.out.println("I am an Animal");
}
}

class Dog extends Animal {
	Dog(){
	System.out.println("I am a Dog");
}
}

class Puppy extends Dog {
	Puppy(){
	System.out.println("I am a baby dog");
}
}

public class ConstructorDemo {
	public static void main(String args[]) {
		Puppy p1 = new Puppy();
	}
}
