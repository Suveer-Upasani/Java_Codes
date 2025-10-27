package railway.booking;

import java.util.Scanner;

public class Reservation extends Train {

    private String quota;
    private Integer passengers;  // Using Wrapper class Integer

    public Reservation(String trainName, String from, String to) {
        super(trainName, from, to);
    }

    @Override
    public void showTrainDetails() {
        System.out.println("Train Selected: " + trainName + " (" + from + " → " + to + ")");
    }

    public void bookTicket() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nSelect Quota:");
        System.out.println("1. Reservation");
        System.out.println("2. General");
        System.out.println("3. Tatkaal");
        System.out.println("4. Ladies");
        int choice = sc.nextInt();

        switch(choice) {
            case 1: quota = "Reservation"; break;
            case 2: quota = "General"; break;
            case 3: quota = "Tatkaal"; break;
            case 4: quota = "Ladies"; break;
            default: quota = "General"; 
        }

        System.out.print("Enter number of passengers: ");
        passengers = sc.nextInt();  // Wrapper class Integer in use

        System.out.println("\n✅ Booking Confirmed!");
        System.out.println("Train: " + trainName);
        System.out.println("From: " + from + " → To: " + to);
        System.out.println("Quota: " + quota);
        System.out.println("Passengers: " + passengers);
    }
}
