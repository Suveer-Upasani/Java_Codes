package railway.booking;

import railway.model.Train;
import java.util.Scanner;

public class Reservation {
    private Train train;

    public Reservation(Train train) {
        this.train = train;
    }

    public void bookTicket() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Passenger Name: ");
        String passenger = sc.nextLine();

        System.out.print("Enter Number of Seats: ");
        // Using Wrapper Class Integer
        Integer seats = Integer.valueOf(sc.nextInt());

        // Using Wrapper Class Double
        Double fare = train.calculateFare(seats);

        System.out.println("\n🎫 Ticket Booked Successfully!");
        System.out.println("Passenger: " + passenger);
        System.out.println("Train: " + train.getTrainName() + " (" + train.getTrainNumber() + ")");
        System.out.println("Seats: " + seats);
        System.out.println("Total Fare: ₹" + fare);
    }
}
