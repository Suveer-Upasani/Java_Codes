package Codes;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args){
        try(ServerSocket ss=new ServerSocket(5005)){
            System.out.println("Server Started");

            try(Socket s=ss.accept();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream()))
            {
                System.out.println("Client Connected");
                String msg=dis.readUTF();
                System.out.println("Client says: "+msg);

                dout.writeUTF("Message found");
                System.out.println("Response sent.");
            }
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
