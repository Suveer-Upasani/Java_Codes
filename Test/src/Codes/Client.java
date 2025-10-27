package Codes;

import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args){
        try{
            Socket s=new Socket("localhost",5005);
            DataInputStream dis=new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter message for server: ");
            String msg=br.readLine();
            dout.writeUTF(msg);
            dout.flush();
            String reply=dis.readUTF();
            System.out.println("Server says: "+reply);
            s.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
