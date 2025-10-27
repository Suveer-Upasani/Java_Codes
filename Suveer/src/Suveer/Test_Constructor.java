package Suveer;

import java.util.Scanner;

enum Dept {
    CS, IT, ECE
}

enum School {
    ENGINEERING, MANAGEMENT, ARTS, SCIENCE
}

enum Year {
    FY, SY, TY
}

public class Test_Constructor {
    int id;
    String nm;
    Dept dept;
    School school;
    Year year;
    long prn;
    int java;
    int ds;
    int dsv;
    int evs;
    double sgpa;
    double cgpa;

    Test_Constructor() {
        this.id = 0;
        this.nm = "Default";
        this.dept = Dept.CS;
        this.school = School.ENGINEERING;
        this.year = Year.FY;
        this.prn = genPRN();
        this.java = 0;
        this.ds = 0;
        this.dsv = 0;
        this.evs = 0;
        this.sgpa = 0.0;
        this.cgpa = 0.0;
    }

    Test_Constructor(int id, String nm, Dept dept, School school, Year year) {
        this.id = id;
        this.nm = nm;
        this.dept = dept;
        this.school = school;
        this.year = year;
        this.prn = genPRN();
        this.java = 0;
        this.ds = 0;
        this.dsv = 0;
        this.evs = 0;
        this.sgpa = 0.0;
        this.cgpa = 0.0;
    }

    long genPRN() {
        return (long)(1000000000L + Math.random() * 9000000000L);
    }

    void calculateSGPA() {
        double total = java + ds + dsv + evs;
        this.sgpa = total / 40.0;
    }

    void calculateCGPA() {
        this.cgpa = this.sgpa;
    }

    void show() {
        System.out.println("\n--- Student ---");
        System.out.println("ID: " + id);
        System.out.println("Name: " + nm);
        System.out.println("School: " + school);
        
        if (year == Year.FY) {
            System.out.println("Year: " + year);
            System.out.println("Branch: " + dept);
        } else {
            System.out.println("Year: " + year);
            System.out.println("Dept: " + dept);
            System.out.println("SGPA: " + sgpa);
            System.out.println("CGPA: " + cgpa);
        }
        
        System.out.println("PRN: " + prn);
        System.out.println("Marks - Java: " + java + ", DS: " + ds + ", DSV: " + dsv + ", EVS: " + evs);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Test_Constructor[] stu = new Test_Constructor[100];
        int cnt = 0;

        while (true) {
            System.out.println("\n=== Login Menu ===");
            System.out.println("1. Admin");
            System.out.println("2. Student");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    while (true) {
                        System.out.println("\n=== Admin Panel ===");
                        System.out.println("1. Add Student");
                        System.out.println("2. Set Marks");
                        System.out.println("3. View All Students");
                        System.out.println("4. Back to Main Menu");
                        System.out.print("Choice: ");
                        int fch = sc.nextInt();

                        if (fch == 1) {
                            if (cnt >= 100) {
                                System.out.println("Student limit reached!");
                                break;
                            }

                            System.out.print("\nHow many students to add? ");
                            int n = sc.nextInt();
                            sc.nextLine(); 

                            for (int i = 0; i < n; i++) {
                                System.out.println("\nStudent " + (cnt + 1) + ":");
                                System.out.print("ID: ");
                                int id = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Name: ");
                                String nm = sc.nextLine();
                                
                                System.out.print("School (ENGINEERING/MANAGEMENT/ARTS/SCIENCE): ");
                                String s = sc.nextLine().toUpperCase();
                                School school;
                                try {
                                    school = School.valueOf(s);
                                } catch (Exception e) {
                                    school = School.ENGINEERING;
                                }
                                
                                System.out.print("Year (FY/SY/TY): ");
                                String y = sc.nextLine().toUpperCase();
                                Year year;
                                try {
                                    year = Year.valueOf(y);
                                } catch (Exception e) {
                                    year = Year.FY;
                                }
                                
                                System.out.print("Branch/Dept (CS/IT/ECE): ");
                                String d = sc.nextLine().toUpperCase();
                                Dept dept;
                                try {
                                    dept = Dept.valueOf(d);
                                } catch (Exception e) {
                                    dept = Dept.CS;
                                }

                                stu[cnt] = new Test_Constructor(id, nm, dept, school, year);
                                System.out.println("Student added successfully! PRN: " + stu[cnt].prn);
                                cnt++;
                            }
                        } 
                        else if (fch == 2) {
                            if (cnt == 0) {
                                System.out.println("No students found");
                                break;
                            }

                            System.out.print("\nEnter PRN: ");
                            long p = sc.nextLong();
                            boolean found = false;

                            for (int i = 0; i < cnt; i++) {
                                if (stu[i].prn == p) {
                                    System.out.print("Java marks (out of 10): ");
                                    stu[i].java = sc.nextInt();
                                    System.out.print("DS marks (out of 10): ");
                                    stu[i].ds = sc.nextInt();
                                    System.out.print("DSV marks (out of 10): ");
                                    stu[i].dsv = sc.nextInt();
                                    System.out.print("EVS marks (out of 10): ");
                                    stu[i].evs = sc.nextInt();
                                    
                                    stu[i].calculateSGPA();
                                    stu[i].calculateCGPA();
                                    
                                    System.out.println("Marks updated successfully!");
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) System.out.println("Student not found");
                        }
                        else if (fch == 3) {
                            if (cnt == 0) {
                                System.out.println("No students found");
                            } else {
                                for (int i = 0; i < cnt; i++) {
                                    stu[i].show();
                                }
                            }
                        }
                        else if (fch == 4) {
                            break;
                        }
                        else {
                            System.out.println("Invalid choice");
                        }
                    }
                    break;

                case 2:
                    System.out.print("\nEnter your PRN: ");
                    long p = sc.nextLong();
                    boolean found = false;

                    for (int i = 0; i < cnt; i++) {
                        if (stu[i].prn == p) {
                            stu[i].show();
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Student not found");
                    break;

                case 3:
                    System.out.println("Exiting system...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}