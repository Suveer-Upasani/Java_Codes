import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        try {
            GreetingService service = (GreetingService) Naming.lookup("rmi://localhost:1099/GreetingService");
            String response = service.greet("Suveer");
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
