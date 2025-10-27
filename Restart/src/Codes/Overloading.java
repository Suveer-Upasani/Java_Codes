package Codes;
import java.util.Scanner;

class Student_2{
	Scanner sc = new Scanner(System.in);
	
	String name;
	int roll_number;
	
	Student_2(String name, int roll_number){
		System.out.println("Prinitng both name and Roll Number");
		this.name = name;
		this.roll_number = roll_number;
		
		System.out.println("name:" + name);
		System.out.println("roll_number:" + roll_number);
	}
	
	Student_2(int roll_number){
		System.out.println("Printing just roll number");
		this.roll_number = roll_number;
		
		System.out.println("roll_number:" + roll_number);
	}
	

	Student_2(String name){
		System.out.println("Printing just name");
		this.name = name;
		
		System.out.println("name:" + name);
	}
}

public class Overloading {
	public static void main(String args[]) {
		Student_2 s1 = new Student_2("Suveer", 12);
		
		Student_2 s2 = new Student_2(12);
		
		Student_2 s3 = new Student_2("Suveer");
	}
}
