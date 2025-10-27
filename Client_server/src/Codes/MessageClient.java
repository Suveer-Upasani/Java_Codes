package Codes;

import java.io.*;
import java.net.*;

public class MessageClient {
    public static void main(String[] args) {
        try (Socket s = new Socket("localhost", 5051)) {
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.print("Enter message: ");
            String msg = userIn.readLine();
            out.println(msg);
            System.out.println(serverIn.readLine());
            userIn.close();
            out.close();
            serverIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
