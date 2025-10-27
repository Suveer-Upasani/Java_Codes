package Codes;
import java.util.Scanner;

class Tickets {
    private int availableTickets;

    public Tickets(int total) {
        this.availableTickets = total;
    }

    public synchronized void bookTicket(String client, int count) {
        try {
            System.out.println(client + " is trying to book " + count + " ticket(s).");
            if (count <= availableTickets) {
                System.out.println(client + " successfully booked " + count + " ticket(s).");
                availableTickets -= count;
            } else {
                System.out.println("Not enough tickets for " + client + ". Remaining: " + availableTickets);
            }
            System.out.println("Tickets left: " + availableTickets + "\n");
        } catch (Exception e) {
            System.out.println("Error while booking for " + client + ": " + e.getMessage());
        }
    }
}

class ClientThread extends Thread {
    private Tickets tickets;
    private String client;
    private int count;

    public ClientThread(Tickets tickets, String client, int count) {
        this.tickets = tickets;
        this.client = client;
        this.count = count;
    }

    public void run() {
        tickets.bookTicket(client, count);
    }
}

class ClientRunnable implements Runnable {
    private Tickets tickets;
    private String client;
    private int count;

    public ClientRunnable(Tickets tickets, String client, int count) {
        this.tickets = tickets;
        this.client = client;
        this.count = count;
    }

    public void run() {
        tickets.bookTicket(client, count);
    }
}

public class ThreadsDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total number of tickets: ");
        int totalTickets = sc.nextInt();
        Tickets tickets = new Tickets(totalTickets);

        while (true) {
            System.out.println("\n===== Client Ticket Booking Menu =====");
            System.out.println("1. Client booking using Thread class");
            System.out.println("2. Client booking using Runnable interface");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 3) {
                System.out.println("Exiting... Thank you for using the system!");
                break;
            }

            System.out.print("Enter Client Name: ");
            String client = sc.next();
            System.out.print("Enter number of tickets to book: ");
            int count = sc.nextInt();

            switch (choice) {
                case 1:
                    ClientThread c1 = new ClientThread(tickets, client, count);
                    c1.start();
                    break;
                case 2:
                    Thread c2 = new Thread(new ClientRunnable(tickets, client, count));
                    c2.start();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }
}
