package Codes;

import java.awt.*;
import java.awt.event.*;

class TicketCounter {
    private int tickets = 5;

    public synchronized boolean bookTicket(String user) {
        if (tickets > 0) {
            System.out.println(user + " booked ticket number " + tickets);
            tickets--;
            return true;
        } else {
            System.out.println(user + " failed, no tickets left");
            return false;
        }
    }
}

// Using Runnable
class BookingRunnable implements Runnable {
    private TicketCounter counter;
    private String user;
    private MovieBookingDemo gui;

    BookingRunnable(TicketCounter counter, String user, MovieBookingDemo gui) {
        this.counter = counter;
        this.user = user;
        this.gui = gui;
    }

    public void run() {
        boolean success = counter.bookTicket(user);
        if (success) {
            gui.showMessage(user + " successfully booked a ticket!");
        } else {
            gui.showMessage(user + " failed. Tickets sold out!");
        }
    }
}

// Using Thread class
class BookingThread extends Thread {
    private TicketCounter counter;
    private String user;
    private MovieBookingDemo gui;

    BookingThread(TicketCounter counter, String user, MovieBookingDemo gui) {
        this.counter = counter;
        this.user = user;
        this.gui = gui;
    }

    public void run() {
        boolean success = counter.bookTicket(user);
        if (success) {
            gui.showMessage(user + " successfully booked a ticket!");
        } else {
            gui.showMessage(user + " failed. Tickets sold out!");
        }
    }
}

public class MovieBookingDemo extends Frame implements ActionListener {
    Button user1, user2, user3;
    TicketCounter counter = new TicketCounter();

    MovieBookingDemo() {
        setLayout(new FlowLayout());

        user1 = new Button("User 1 - Book Ticket");
        user2 = new Button("User 2 - Book Ticket");
        user3 = new Button("User 3 - Book Ticket");

        add(user1);
        add(user2);
        add(user3);

        user1.addActionListener(this);
        user2.addActionListener(this);
        user3.addActionListener(this);

        setSize(400, 200);
        setTitle("Advanced Movie Ticket Booking");
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == user1) {
            new BookingRunnable(counter, "User 1", this).run();  // Runnable
        } else if (e.getSource() == user2) {
            new BookingThread(counter, "User 2", this).start(); // Thread class
        } else if (e.getSource() == user3) {
            new Thread(new BookingRunnable(counter, "User 3", this)).start(); // Runnable in Thread
        }
    }

    void showMessage(String msg) {
        Dialog d = new Dialog(this, "Booking Status", true);
        d.setLayout(new FlowLayout());
        Label l = new Label(msg);
        d.add(l);
        Button ok = new Button("OK");
        ok.addActionListener(e -> d.setVisible(false));
        d.add(ok);
        d.setSize(300, 120);
        d.setVisible(true);
    }

    public static void main(String[] args) {
        new MovieBookingDemo();
    }
}
