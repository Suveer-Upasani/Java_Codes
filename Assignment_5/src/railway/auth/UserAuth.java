package railway.auth;

import java.util.Scanner;

public class UserAuth {
    private String username;
    private String password;

    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Username: ");
        username = sc.nextLine();
        System.out.print("Enter Password: ");
        password = sc.nextLine();

        // Simple check (can be extended)
        if(username.equals("admin") && password.equals("1234")) {
            System.out.println("Login Successful!\n");
            return true;
        } else {
            System.out.println("Invalid credentials.\n");
            return false;
        }
    }
}
