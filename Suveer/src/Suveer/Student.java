package Suveer;


enum Department{
    CS, IT, ECE

}
public class Student {
    int id;
    String name;
    Department dept;

    Student() {
        this.id = 0;
        this.name = "Default";
        this.dept = Department.CS;
    }

    Student(int id, String name, Department dept) {
        this.id = id;
        this.name = new String(name);
        this.dept = dept;
    }

    void display() {
        System.out.println("ID: " + id + " Name: " + name + " Department: " + dept);
    }
    
    public class New{
    	New(){
    		
    	}
    }
    public static void main(String[] args) {
    	
        Student s1 = new Student();
        Student s2 = new Student(2010, "Suveer", Department.IT);
        Student s3 = new Student(2009, "Tommy", Department.ECE);
        
        s1.display();
        s2.display();
        s3.display();
        
        }
    }
    


