package filehandlercli;

import java.util.Scanner;

public class CLI {
    private Scanner scanner = new Scanner(System.in);

    public void showMainMenu() {
        System.out.println("=== Adom Logistics System ===");
        System.out.println("[1] Manage Vehicles");
        System.out.println("[2] Assign Drivers");
        System.out.println("[3] Track Deliveries");
        System.out.println("[4] Schedule Maintenance");
        System.out.println("[5] Fuel Efficiency Report");
        System.out.println("[0] Exit");
        System.out.print("Enter choice: ");
    }

    // This method keeps the CLI running until the user exits
    public void start() {
        boolean running = true;
        while (running) {
            showMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    // manageVehicles();
                    System.out.println("Manage Vehicles selected.");
                    break;
                case "2":
                    // assignDrivers();
                    System.out.println("Assign Drivers selected.");
                    break;
                case "3":
                    // trackDeliveries();
                    System.out.println("Track Deliveries selected.");
                    break;
                case "4":
                    // scheduleMaintenance();
                    System.out.println("Schedule Maintenance selected.");
                    break;
                case "5":
                    // showFuelEfficiencyReport();
                    System.out.println("Fuel Efficiency Report selected.");
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }
}