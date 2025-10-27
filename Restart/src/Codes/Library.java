package Codes;
import java.util.Scanner;

class Managment{
	Scanner sc = new Scanner(System.in);
	
	String book;

	void add_book() {
	System.out.println("Enter Book name:");
	book=sc.nextLine();
	}
	
	void show_book() {
		System.out.println("Book name is:" + book);
	}
}

public class Library {
	public static void main(String args[]) {
		Managment m1 = new Managment();
		m1.add_book();
		m1.show_book();
	}
}


