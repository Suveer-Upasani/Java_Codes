package Codes;


import java.io.*;
import java.net.*;

public class MessageServer {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(5051)) {
            System.out.println("Server running...");
            while (true) {
                Socket s = ss.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                String msg = in.readLine();
                System.out.println("Client: " + msg);
                out.println("Received: " + msg);
                in.close();
                out.close();
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
