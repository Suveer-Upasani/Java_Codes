package Suveer;
import java.util.Scanner;

interface Vehicle {
    void start();
    void stop();
    int getSpeed();
    String getFuelType();
}

interface Maintenance {
    void doMaintenance();
    boolean isServiceDue();
}

class Car implements Vehicle, Maintenance {
    private int speed;
    private String fuelType;
    private int kmDriven;

    public Car(int speed, String fuelType, int kmDriven) {
        this.speed = speed;
        this.fuelType = fuelType;
        this.kmDriven = kmDriven;
    }

    public void start() { System.out.println("Car started"); }
    public void stop() { System.out.println("Car stopped"); }
    public int getSpeed() { return speed; }
    public String getFuelType() { return fuelType; }

    public void doMaintenance() {
        System.out.println("Car maintenance done");
        kmDriven = 0;
    }

    public boolean isServiceDue() {
        return kmDriven >= 5000;
    }
}

class Bus implements Vehicle, Maintenance {
    private int speed;
    private String fuelType;
    private int kmDriven;

    public Bus(int speed, String fuelType, int kmDriven) {
        this.speed = speed;
        this.fuelType = fuelType;
        this.kmDriven = kmDriven;
    }

    public void start() { System.out.println("Bus started"); }
    public void stop() { System.out.println("Bus stopped"); }
    public int getSpeed() { return speed; }
    public String getFuelType() { return fuelType; }

    public void doMaintenance() {
        System.out.println("Bus maintenance done");
        kmDriven = 0;
    }

    public boolean isServiceDue() {
        return kmDriven >= 5000;
    }
}

class Motorcycle implements Vehicle {
    private int speed;
    private String fuelType;

    public Motorcycle(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    public void start() { System.out.println("Motorcycle started"); }
    public void stop() { System.out.println("Motorcycle stopped"); }
    public int getSpeed() { return speed; }
    public String getFuelType() { return fuelType; }
}

public class TestFleet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("===== Fleet Management System =====");
        System.out.println("1. Add Car");
        System.out.println("2. Add Bus");
        System.out.println("3. Add Motorcycle");
        System.out.println("4. Test All Vehicles");
        System.out.println("5. Exit");
        
        Vehicle[] vehicles = new Vehicle[3];
        int vehicleCount = 0;
        
        while (true) {
            System.out.print("\nEnter your choice (1-5): ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    if (vehicleCount < 3) {
                        System.out.println("\n=== Enter Car Details ===");
                        vehicles[vehicleCount++] = createCar(scanner);
                        System.out.println("Car added successfully!");
                    } else {
                        System.out.println("Maximum vehicles reached!");
                    }
                    break;
                    
                case 2:
                    if (vehicleCount < 3) {
                        System.out.println("\n=== Enter Bus Details ===");
                        vehicles[vehicleCount++] = createBus(scanner);
                        System.out.println("Bus added successfully!");
                    } else {
                        System.out.println("Maximum vehicles reached!");
                    }
                    break;
                    
                case 3:
                    if (vehicleCount < 3) {
                        System.out.println("\n=== Enter Motorcycle Details ===");
                        vehicles[vehicleCount++] = createMotorcycle(scanner);
                        System.out.println("Motorcycle added successfully!");
                    } else {
                        System.out.println("Maximum vehicles reached!");
                    }
                    break;
                    
                case 4:
                    if (vehicleCount > 0) {
                        System.out.println("\n=== Testing All Vehicles ===");
                        for (int i = 0; i < vehicleCount; i++) {
                            System.out.println("\nTesting Vehicle " + (i+1) + ":");
                            testVehicle(vehicles[i]);
                        }
                    } else {
                        System.out.println("No vehicles added yet!");
                    }
                    break;
                    
                case 5:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice! Please enter 1-5.");
            }
        }
    }
    
    private static Car createCar(Scanner scanner) {
        System.out.print("Enter speed (km/h): ");
        int speed = scanner.nextInt();
        System.out.print("Enter fuel type (Petrol/Diesel): ");
        String fuel = scanner.next();
        System.out.print("Enter kilometers driven: ");
        int km = scanner.nextInt();
        return new Car(speed, fuel, km);
    }
    
    private static Bus createBus(Scanner scanner) {
        System.out.print("Enter speed (km/h): ");
        int speed = scanner.nextInt();
        System.out.print("Enter fuel type (Petrol/Diesel): ");
        String fuel = scanner.next();
        System.out.print("Enter kilometers driven: ");
        int km = scanner.nextInt();
        return new Bus(speed, fuel, km);
    }
    
    private static Motorcycle createMotorcycle(Scanner scanner) {
        System.out.print("Enter speed (km/h): ");
        int speed = scanner.nextInt();
        System.out.print("Enter fuel type (Petrol): ");
        String fuel = scanner.next();
        return new Motorcycle(speed, fuel);
    }
    
    private static void testVehicle(Vehicle vehicle) {
        System.out.println("Vehicle Type: " + vehicle.getClass().getSimpleName());
        vehicle.start();
        System.out.println("Current Speed: " + vehicle.getSpeed() + " km/h");
        System.out.println("Fuel Type: " + vehicle.getFuelType());
        vehicle.stop();
        
        if (vehicle instanceof Maintenance) {
            Maintenance m = (Maintenance) vehicle;
            if (m.isServiceDue()) {
                System.out.println("MAINTENANCE ALERT: Service is due!");
                System.out.print("Perform maintenance now? (y/n): ");
                Scanner sc = new Scanner(System.in);
                String response = sc.next();
                if (response.equalsIgnoreCase("y")) {
                    m.doMaintenance();
                    System.out.println("Maintenance completed!");
                }
            } else {
                System.out.println("Maintenance status: OK");
            }
        }
    }
}
