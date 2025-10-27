package railway.auth;
import java.util.Scanner;

public class UserAuth {
    private final String username = "admin";
    private final String password = "1234";

    public boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String user = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if(user.equals(username) && pass.equals(password)) {
            System.out.println("✅ Login Successful!");
            return true;
        } else {
            System.out.println("❌ Invalid Credentials!");
            return false;
        }
    }
}
