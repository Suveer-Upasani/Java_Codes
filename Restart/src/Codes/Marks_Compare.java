package Codes;

public class Marks_Compare {
	float marks;
	String name;
	
	Marks_Compare(String name, float marks)
	{
		this.name=name;
		this.marks=marks;
		System.out.println(" Name: " + name);
		System.out.println(" Marks: " + marks);

	}
	void compare(Marks_Compare other)
	{
		if(this.marks > other.marks) {
			System.out.println(this.name + " has higher marks than " + other.name);
			
		}

		else if(this.marks < other.marks) {
			System.out.println(other.marks + " has higher marks than " + this.name);
			
		}
		else {
			System.out.println(other.marks + "has same marks as " + this.name);

		}
		
	}
	public static void main(String args[]) {
		Marks_Compare m1 = new Marks_Compare("Suveer", 85);
		Marks_Compare m2 = new Marks_Compare("Leo", 75);
		m1.compare(m2);

	
	}
	
	

}
