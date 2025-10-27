package Codes;

import java.util.Scanner;
class Student{
	String name;
	String branch;
	int roll_number;
	
	void input() {
		java.util.Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter name:");
		name=sc.nextLine();
		
		System.out.println("Enter Branch:");
		branch=sc.nextLine();
		
		System.out.println("Enter Roll_Number:");
		roll_number=sc.nextInt();
		
	}
	void display(){	
		System.out.println("Name:" + name);
		System.out.println("Branch:" + branch);
		System.out.println("Roll number:" + roll_number);


	}
}
public class Refrence_Pointer {
	public static void main(String args[]) {
		
	
	Student s1 = new Student();
	
	s1.input();
	s1.display();
	
	System.out.println("Refrencing s1 to s2 via assigmnet refrencing");

	Student s2 = s1;
	
	s2.display();
	
	System.out.println("Changing s2.name");
	s2.name = "Appa";
	
	s1.display();
	s2.display();
	
	
	}
	
	
}
