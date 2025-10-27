package Codes;

class  Employee{
	String name;
	int id;
	int hrs;
	Employee(String name, int id, int hrs){
		this.name=name;
		this.id=id;
		this.hrs=hrs;
	}
	void display(){
		System.out.println("Name: " + name);
		System.out.println("Id: " + id);
		System.out.println("Work hours daily: " + hrs);
	}
	void calculateSalary() {
        System.out.println("Base salary calculation for Employee.");
    }
}
class Manager extends Employee {
	Manager(String name, int id, int hrs){
		super(name, id, hrs);
	}
	void calculateSalary() {
		if (hrs <= 3) {
			System.out.println("Salary is 20,000 ");
		}
		else if (hrs <= 6) {
			System.out.println("Salary is 40,000 ");
		}
		else {
			System.out.println("Salary is 60,000 ");
		}
		
	}
}
public class Office {
	public static void main(String args[]) {
		Manager m1 = new Manager("Suveer", 12345, 4);
		m1.display();
		m1.calculateSalary();
	}

}
