package Codes;

import java.io.*;
import java.util.*;

public class CRUD {

    static String path = "/Users/suveer/eclipse-workspace/Assignments/src/Codes/Suveer.txt";

    void addData(String data) throws Exception{
        FileWriter fw = new FileWriter(path);
        fw.write(data + "\n");
        fw.close();
        System.out.println("Data added!");

    }
    void  show(String data) throws Exception{
        Scanner file = new Scanner(new File(path));
        System.out.println("File contents:");
        while(file.hasNextLine())
            System.out.println(file.nextLine());
        file.close();
    }
    public static void main(String[] args) throws Exception {
        CRUD crud = new CRUD();
        crud.addData("Hello, World!");
    }
}