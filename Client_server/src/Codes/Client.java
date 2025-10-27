package Codes;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        Socket s = null;
        try {
            s = new Socket("localhost", 5005);
            System.out.println("Connected to server.");

            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());

            String message = "Hello Server, this is Client!";
            dout.writeUTF(message);
            dout.flush();
            System.out.println("Sent to server: " + message);

            String response = dis.readUTF();
            System.out.println("Server responds: " + response);

        } catch (ConnectException ce) {
            System.out.println("Cannot connect to server. Is the server running?");
        } catch (IOException ioe) {
            System.out.println("I/O error: " + ioe.getMessage());
        } finally {
            try {
                if (s != null) s.close();
                System.out.println("Client closed.");
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}
