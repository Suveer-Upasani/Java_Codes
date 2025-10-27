package Suveer;
import java.util.Scanner;

public class Library {
    static Scanner sc = new Scanner(System.in);
    static String[] books = new String[100];
    static int[] qty = new int[100];
    static int bookCount = 0;

    static String[] emails = new String[100];
    static String[] names = new String[100];
    static String[] divs = new String[100];
    static int[] lastNotifiedBookCount = new int[100];
    static int studentCount = 0;

    public static void main(String[] args) {
        while (true) {
            System.out.println("Login as: 1.Student 2.Faculty 3.Exit");
            int ch = sc.nextInt();
            sc.nextLine();
            if (ch == 1) studentLogin();
            else if (ch == 2) facultyLogin();
            else break;
        }
    }

    static void studentLogin() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        int index = findStudent(email);

        if (index == -1) {
            emails[studentCount] = email;
            System.out.print("Name: ");
            names[studentCount] = sc.nextLine();
            System.out.print("Div: ");
            divs[studentCount] = sc.nextLine();
            lastNotifiedBookCount[studentCount] = bookCount;
            index = studentCount;
            studentCount++;
        } else {
            if (bookCount > lastNotifiedBookCount[index]) {
                System.out.println("New books added:");
                for (int i = lastNotifiedBookCount[index]; i < bookCount; i++) {
                    System.out.println(books[i] + " (" + qty[i] + ")");
                }
                lastNotifiedBookCount[index] = bookCount;
            } else {
                System.out.println("No new books since last login.");
            }
        }

        while (true) {
            System.out.println("1.Issue 2.Return 3.Check 4.Back");
            int ch = sc.nextInt(); sc.nextLine();
            if (ch == 1) issueBook();
            else if (ch == 2) returnBook();
            else if (ch == 3) checkBook();
            else break;
        }
    }

    static void facultyLogin() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();

        while (true) {
            System.out.println("1.View Student 2.Add Book 3.Back");
            int ch = sc.nextInt(); sc.nextLine();
            if (ch == 1) viewStudent();
            else if (ch == 2) addBook();
            else break;
        }
    }

    static void issueBook() {
        System.out.print("Book name: ");
        String b = sc.nextLine();
        int i = findBook(b);
        if (i != -1 && qty[i] > 0) {
            qty[i]--;
            System.out.println("Book issued");
        } else {
            System.out.println("Not available");
        }
    }

    static void returnBook() {
        System.out.print("Book name: ");
        String b = sc.nextLine();
        int i = findBook(b);
        if (i != -1) {
            qty[i]++;
        } else {
            books[bookCount] = b;
            qty[bookCount] = 1;
            bookCount++;
        }
        System.out.println("Book returned");
    }

    static void checkBook() {
        System.out.print("Book name: ");
        String b = sc.nextLine();
        int i = findBook(b);
        if (i != -1 && qty[i] > 0)
            System.out.println("Available: " + qty[i]);
        else
            System.out.println("Not available");
    }

    static void viewStudent() {
        System.out.print("Enter student email: ");
        String e = sc.nextLine();
        int i = findStudent(e);
        if (i != -1) {
            System.out.println("Name: " + names[i]);
            System.out.println("Div: " + divs[i]);
        } else {
            System.out.println("Student not found");
        }
    }

    static void addBook() {
        System.out.print("Book name: ");
        String b = sc.nextLine();
        System.out.print("Quantity: ");
        int q = sc.nextInt(); sc.nextLine();
        int i = findBook(b);
        if (i != -1) {
            qty[i] += q;
        } else {
            books[bookCount] = b;
            qty[bookCount] = q;
            bookCount++;
        }
        System.out.println("Stock updated");
    }

    static int findBook(String name) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].equalsIgnoreCase(name)) return i;
        }
        return -1;
    }

    static int findStudent(String email) {
        for (int i = 0; i < studentCount; i++) {
            if (emails[i].equalsIgnoreCase(email)) return i;
        }
        return -1;
    }
}
