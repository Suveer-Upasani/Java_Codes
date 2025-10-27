package Codes;

import java.util.*;

public class Inventory_System {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> rawMaterials = new ArrayList<>();
        LinkedList<String> finishedGoods = new LinkedList<>();
        HashSet<String> tools = new HashSet<>();
        TreeSet<String> chemicals = new TreeSet<>();

        while (true) {
            System.out.println("\nFactory Inventory Menu:");
            System.out.println("1. Add Raw Material");
            System.out.println("2. Remove Raw Material");
            System.out.println("3. Show Raw Materials");
            System.out.println("4. Add Finished Good");
            System.out.println("5. Remove Finished Good");
            System.out.println("6. Show Finished Goods");
            System.out.println("7. Add Tool");
            System.out.println("8. Remove Tool");
            System.out.println("9. Show Tools");
            System.out.println("10. Add Chemical");
            System.out.println("11. Remove Chemical");
            System.out.println("12. Show Chemicals");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Raw Material: ");
                    rawMaterials.add(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Enter Raw Material to Remove: ");
                    rawMaterials.remove(sc.nextLine());
                    break;
                case 3:
                    for (String item : rawMaterials) System.out.println("Raw Material: " + item);
                    break;
                case 4:
                    System.out.print("Enter Finished Good: ");
                    finishedGoods.add(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Enter Finished Good to Remove: ");
                    finishedGoods.remove(sc.nextLine());
                    break;
                case 6:
                    for (String item : finishedGoods) System.out.println("Finished Good: " + item);
                    break;
                case 7:
                    System.out.print("Enter Tool: ");
                    tools.add(sc.nextLine());
                    break;
                case 8:
                    System.out.print("Enter Tool to Remove: ");
                    tools.remove(sc.nextLine());
                    break;
                case 9:
                    for (String item : tools) System.out.println("Tool: " + item);
                    break;
                case 10:
                    System.out.print("Enter Chemical: ");
                    chemicals.add(sc.nextLine());
                    break;
                case 11:
                    System.out.print("Enter Chemical to Remove: ");
                    chemicals.remove(sc.nextLine());
                    break;
                case 12:
                    for (String item : chemicals) System.out.println("Chemical: " + item);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
            }
        }
    }
}
