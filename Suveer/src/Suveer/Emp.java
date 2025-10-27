package Suveer;

import java.util.Scanner;
class User {
    String username, password;
    User(String u, String p) {
        username = u;
        password = p;
    }
}

class Employee extends User {
    int id;
    String name;
    double salary;
    String[] issues = new String[10];
    int issueCount = 0;
    Employee(int id, String name, String u, String p, double salary) {
        super(u, p);
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    void postIssue(String msg) {
        if (issueCount < issues.length) {
            issues[issueCount++] = msg;
        }
    }
}

class Admin extends User {
    Admin(String u, String p) {
        super(u, p);
    }
}

class AdminRights extends Admin {
    Employee[] employees = new Employee[10];
    int empCount = 0;
    boolean hasNewIssues = false;

    AdminRights(String u, String p) {
        super(u, p);
    }

    void addEmployee(String name, String u, String p, double sal) {
        if (empCount < employees.length) {
            employees[empCount] = new Employee(empCount + 1, name, u, p, sal);
            empCount++;
        }
    }

    void viewEmployees() {
        for (int i = 0; i < empCount; i++) {
            System.out.println("ID: " + employees[i].id + ", Name: " + employees[i].name + ", Salary: " + employees[i].salary);
        }
    }

    void editSalary(int empId, double newSalary) {
        for (int i = 0; i < empCount; i++) {
            if (employees[i].id == empId) {
                employees[i].salary = newSalary;
                break;
            }
        }
    }

    void viewIssues() {
        for (int i = 0; i < empCount; i++) {
            if (employees[i].issueCount > 0) {
                System.out.println("Issues by " + employees[i].name + ":");
                for (int j = 0; j < employees[i].issueCount; j++) {
                    System.out.println("- " + employees[i].issues[j]);
                }
            }
        }
        hasNewIssues = false;
    }

    Employee getEmployee(String uname, String pwd) {
        for (int i = 0; i < empCount; i++) {
            if (employees[i].username.equals(uname) && employees[i].password.equals(pwd)) {
                return employees[i];
            }
        }
        return null;
    }

    void checkNewIssuesFlag() {
        for (int i = 0; i < empCount; i++) {
            if (employees[i].issueCount > 0) {
                hasNewIssues = true;
                break;
            }
        }
    }
}

public class Emp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Set Admin Username: ");
        String adminU = sc.nextLine();
        System.out.print("Set Admin Password: ");
        String adminP = sc.nextLine();
        AdminRights admin = new AdminRights(adminU, adminP);

        while (true) {
            System.out.println("\n1.Admin Login 2.Employee Login 3.Exit");
            int ch = sc.nextInt(); sc.nextLine();
            if (ch == 1) {
                System.out.print("Username: ");
                String u = sc.nextLine();
                System.out.print("Password: ");
                String p = sc.nextLine();
                if (u.equals(admin.username) && p.equals(admin.password)) {
                    admin.checkNewIssuesFlag();
                    if (admin.hasNewIssues) {
                        System.out.println("🔔 You have new issues raised by employees.");
                    }
                    while (true) {
                        System.out.println("\n1.Add Employee 2.View Employees 3.Edit Salary 4.View Issues 5.Logout");
                        int op = sc.nextInt(); sc.nextLine();
                        if (op == 1) {
                            System.out.print("Name: ");
                            String name = sc.nextLine();
                            System.out.print("Username: ");
                            String eu = sc.nextLine();
                            System.out.print("Password: ");
                            String ep = sc.nextLine();
                            System.out.print("Salary: ");
                            double sal = sc.nextDouble(); sc.nextLine();
                            admin.addEmployee(name, eu, ep, sal);
                        } else if (op == 2) {
                            admin.viewEmployees();
                        } else if (op == 3) {
                            System.out.print("Enter Employee ID: ");
                            int id = sc.nextInt(); sc.nextLine();
                            System.out.print("New Salary: ");
                            double ns = sc.nextDouble(); sc.nextLine();
                            admin.editSalary(id, ns);
                        } else if (op == 4) {
                            admin.viewIssues();
                        } else break;
                    }
                } else {
                    System.out.println("Invalid admin credentials.");
                }
            } else if (ch == 2) {
                System.out.print("Username: ");
                String u = sc.nextLine();
                System.out.print("Password: ");
                String p = sc.nextLine();
                Employee emp = admin.getEmployee(u, p);
                if (emp != null) {
                    while (true) {
                        System.out.println("\n1.View My Details 2.Raise Issue 3.Logout");
                        int ep = sc.nextInt(); sc.nextLine();
                        if (ep == 1) {
                            System.out.println("ID: " + emp.id + ", Name: " + emp.name + ", Salary: " + emp.salary);
                        } else if (ep == 2) {
                            System.out.print("Enter issue: ");
                            String msg = sc.nextLine();
                            emp.postIssue(msg);
                            admin.hasNewIssues = true;
                        } else break;
                    }
                } else {
                    System.out.println("Invalid employee credentials.");
                }
            } else break;
        }
        sc.close();
    }
}
