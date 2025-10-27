
import java.io.*;
import java.util.*;

public class FileCRUD {
    private static final String FILE_PATH = "/Users/suveer/eclipse-workspace/Assignments/src/Codes/Suveer.txt";

    public static void create(String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(data);
            bw.newLine();
            System.out.println("Data added successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            System.out.println("File contents:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delete() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write("");
            System.out.println("All data deleted, file cleared!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- File CRUD Menu ---");
            System.out.println("1. Add Data");
            System.out.println("2. Display Data");
            System.out.println("3. Delete All Data");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter data to add: ");
                    String data = sc.nextLine();
                    create(data);
                    break;
                case 2:
                    read();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
