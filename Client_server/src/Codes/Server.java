package Codes;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket s = null;
        try {
            ss = new ServerSocket(5005);
            System.out.println("Server started. Waiting for client...");

            s = ss.accept();
            System.out.println("Client connected.");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            String clientMessage = dis.readUTF();
            System.out.println("Client says: " + clientMessage);

            String serverMessage = "Hello Client, message received: " + clientMessage;
            dout.writeUTF(serverMessage);
            dout.flush();

        } catch (BindException be) {
            System.out.println("Port 5005 is already in use. Please use another port.");
        } catch (IOException ioe) {
            System.out.println("I/O error: " + ioe.getMessage());
        } finally {
            try {
                if (s != null) s.close();
                if (ss != null) ss.close();
                System.out.println("Server closed.");
            } catch (IOException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
