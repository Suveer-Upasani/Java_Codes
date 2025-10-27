package Codes;
import java.util.Scanner;

public class Book_1 {
	
	String Title;
	String Author;
	float price;
	
	Scanner sc = new Scanner(System.in);
	void input() {
	System.out.println("Enter Title:");
	Title=sc.nextLine();
	
	System.out.println("Enter name of authour:");
	Author=sc.nextLine();
	
	System.out.println("Enter Price:");
	price=sc.nextFloat();
	}
	
	void display() {
		System.out.println("Title:" + Title);
		System.out.println("Author:" + Author);
		System.out.println("Price:" + price);
	}
	
	public static void main(String args[]) {
		Book_1 instance_1=new Book_1();
		Book_1 instance_2=new Book_1();
		
		instance_1.input();
		instance_2.input();
		instance_1.display();
		instance_2.display();
		
	}

}
