package railway;

import railway.auth.UserAuth;
import railway.booking.Reservation;
import railway.model.Train;

import java.util.Scanner;

public class ReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserAuth auth = new UserAuth();

        if(!auth.login()) {
            System.out.println("Exiting System...");
            return;
        }

        // Anonymous class extending Abstract Train
        Train express = new Train("SuperFast Express", 12345) {
            @Override
            public void displayTrainDetails() {
                System.out.println("Train: " + trainName + " | Number: " + trainNumber);
            }

            @Override
            public double calculateFare(int seats) {
                double ratePerSeat = 150.50; // Using double
                return seats * ratePerSeat;
            }
        };

        express.displayTrainDetails();

        Reservation res = new Reservation(express);
        res.bookTicket();
    }
}
