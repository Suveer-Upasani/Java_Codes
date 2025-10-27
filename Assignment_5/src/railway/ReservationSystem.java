package railway;

import railway.auth.UserAuth;
import railway.booking.Reservation;
import java.util.Scanner;

public class ReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserAuth auth = new UserAuth();

        if(!auth.login()) {
            System.out.println("Exiting System...");
            return;
        }

        System.out.println("Enter Source Station: ");
        String from = sc.nextLine();

        System.out.println("Enter Destination Station: ");
        String to = sc.nextLine();

        // Show trains (hardcoded for demo)
        System.out.println("\nAvailable Trains:");
        System.out.println("1. Shatabdi Express");
        System.out.println("2. Rajdhani Express");
        System.out.println("3. Duronto Express");
        System.out.print("Select Train (1-3): ");
        int trainChoice = sc.nextInt();
        sc.nextLine(); // consume newline

        String trainName = "";
        switch(trainChoice) {
            case 1: trainName = "Shatabdi Express"; break;
            case 2: trainName = "Rajdhani Express"; break;
            case 3: trainName = "Duronto Express"; break;
            default: trainName = "Shatabdi Express";
        }

        Reservation res = new Reservation(trainName, from, to);
        res.showTrainDetails();
        res.bookTicket();

        System.out.println("\nThank you for using IRCTSC System!");
    }
}
