package Codes;
import java.util.Scanner;

class Student {
    String name;
    String course;
    String prn;
    int age;
    String branch;
    String year;
    double sgpa;
    double cgpa;
    static int prnCounter = 0;

    Student() {
        name = "Not Assigned";
        prn = "1012411" + prnCounter++;
        course = "Undeclared";
        age = 0;
        branch = "Not Assigned";
        year = "FY";
        sgpa = 0.0;
        cgpa = 0.0;
    }

    Student(String n) {
        name = n;
        prn = "1012411" + prnCounter++;
        course = "Undeclared";
        age = -1;
        branch = "Not Assigned";
        year = "FY";
        sgpa = 0.0;
        cgpa = 0.0;
    }

    Student(String n, String c, int a, String b, String y, double s, double cg) {
        name = n;
        prn = "1012411" + prnCounter++;
        course = c;
        age = a;
        branch = b;
        year = y;
        sgpa = s;
        cgpa = cg;
    }

    void displayForAdmin() {
        System.out.println("-----------------------");
        System.out.println("Name: " + name);
        System.out.println("PRN: " + prn);
        System.out.println("Course: " + course);
        System.out.println("Branch: " + branch);
        System.out.println("Year: " + year);
        System.out.println("Age: " + age);
        System.out.println("SGPA: " + sgpa);
        System.out.println("CGPA: " + cgpa);
    }

    void displayForStudent() {
        System.out.println("-----------------------");
        System.out.println("Name: " + name);
        System.out.println("PRN: " + prn);
        System.out.println("Course: " + course);
        System.out.println("Branch: " + branch);
        System.out.println("Year: " + year);

        if (!year.equalsIgnoreCase("FY")) {
            System.out.println("SGPA: " + sgpa);
            System.out.println("CGPA: " + cgpa);
        }
    }

    void displayFaculty() {
        if (course.equalsIgnoreCase("CSE")) {
            System.out.println("Faculty: Prof. Prajakta Khadkikar");
        } else if (course.equalsIgnoreCase("ECE")) {
            System.out.println("Faculty: Prof. B. Sharma");
        } else if (course.equalsIgnoreCase("BSC")) {
            System.out.println("Faculty: Prof. C. Patil");
        } else {
            System.out.println("Faculty: Not Assigned");
        }
    }
}

public class mainStu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student[] students = new Student[100];
        int count = 0;

        while (true) {
            System.out.println("\n--- Login Portal ---");
            System.out.print("Enter role (admin/student) or 'exit' to quit: ");
            String role = sc.nextLine();

            if (role.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            boolean isAdmin = false, isStudent = false;

            if (role.equalsIgnoreCase("admin") && password.equals("admin@123")) {
                isAdmin = true;
                System.out.println("Admin login successful.");
            } else if (role.equalsIgnoreCase("student") && password.equals("student@123")) {
                isStudent = true;
                System.out.println("Student login successful.");
            } else {
                System.out.println("Invalid login. Try again.");
                continue;
            }

            while (true) {
                if (isAdmin) {
                    System.out.println("\n--- Admin Panel ---");
                    System.out.println("1. Add Student (Full Info)");
                    System.out.println("2. Add Student (Only Name)");
                    System.out.println("3. Add Default Student");
                    System.out.println("4. Display All Students");
                    System.out.println("5. Search Student by PRN");
                    System.out.println("6. Display Faculty by PRN");
                    System.out.println("7. Logout");

                    System.out.print("Enter choice: ");
                    int ch = sc.nextInt();
                    sc.nextLine();

                    if (ch == 1) {
    System.out.print("Enter Name: ");
    String n = sc.nextLine();
    System.out.print("Enter Course: ");
    String c = sc.nextLine();
    System.out.print("Enter Age: ");
    int a = sc.nextInt();
    sc.nextLine();
    System.out.print("Enter Branch (BTech/BBA/BSc): ");
    String b = sc.nextLine();
    System.out.print("Enter Year (FY/SY/TY): ");
    String y = sc.nextLine();

    double sgpa = 0.0, cgpa = 0.0;

    if (!y.equalsIgnoreCase("FY")) {
        System.out.println("Enter marks for the following subjects (out of 100):");

        System.out.print("Java: ");
        double javaMarks = sc.nextDouble();

        System.out.print("EVS: ");
        double evsMarks = sc.nextDouble();

        System.out.print("DSA: ");
        double dsaMarks = sc.nextDouble();

        System.out.print("EE: ");
        double eeMarks = sc.nextDouble();

        System.out.print("DSV: ");
        double dsvMarks = sc.nextDouble();

        sc.nextLine();

        double totalMarks = javaMarks + evsMarks + dsaMarks + eeMarks + dsvMarks;
        sgpa = totalMarks / 50.0;
        cgpa = sgpa;
    }
    else {
        sgpa = 0.0;
        cgpa = 0.0;
    }

    students[count] = new Student(n, c, a, b, y, sgpa, cgpa);
    System.out.println("Student added with PRN: " + students[count].prn);
    count++;
}

                    else if (ch == 2) {
                        System.out.print("Enter Name: ");
                        String n = sc.nextLine();
                        students[count] = new Student(n);
                        System.out.println("Student added with PRN: " + students[count].prn);
                        count++;
                    }
                    else if (ch == 3) {
                        students[count] = new Student();
                        System.out.println("Default student added with PRN: " + students[count].prn);
                        count++;
                    }
                    else if (ch == 4) {
                        if (count == 0) {
                            System.out.println("No students to display.");
                        } else {
                            for (int i = 0; i < count; i++) {
                                students[i].displayForAdmin();
                            }
                        }
                    }
                    else if (ch == 5) {
                        System.out.print("Enter PRN to search: ");
                        String target = sc.nextLine();
                        boolean found = false;
                        for (int i = 0; i < count; i++) {
                            if (students[i].prn.equalsIgnoreCase(target)) {
                                students[i].displayForAdmin();
                                found = true;
                                break;
                            }
                        }
                        if (!found) System.out.println("Student not found.");
                    }
                    else if (ch == 6) {
                        System.out.print("Enter PRN to check faculty: ");
                        String target = sc.nextLine();
                        boolean found = false;
                        for (int i = 0; i < count; i++) {
                            if (students[i].prn.equalsIgnoreCase(target)) {
                                students[i].displayFaculty();
                                found = true;
                                break;
                            }
                        }
                        if (!found) System.out.println("Student not found.");
                    }
                    else if (ch == 7) {
                        System.out.println("Logged out.");
                        break;
                    }
                    else {
                        System.out.println("Invalid choice.");
                    }

                } else if (isStudent) {
                    System.out.println("\n--- Student Panel ---");
                    System.out.println("1. View My Data");
                    System.out.println("2. Logout");
                    System.out.print("Enter choice: ");
                    int ch = sc.nextInt();
                    sc.nextLine();

                    if (ch == 1) {
                        System.out.print("Enter your PRN: ");
                        String target = sc.nextLine();
                        boolean found = false;
                        for (int i = 0; i < count; i++) {
                            if (students[i].prn.equalsIgnoreCase(target)) {
                                students[i].displayForStudent();
                                found = true;
                                break;
                            }
                        }
                        if (!found) System.out.println("Student not found.");
                    }
                    else if (ch == 2) {
                        System.out.println("Logged out.");
                        break;
                    }
                    else {
                        System.out.println("Invalid choice.");
                    }
                }
            }
        }

        sc.close();
    }
}
