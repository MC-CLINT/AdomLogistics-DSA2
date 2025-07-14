package Main;

import java.util.Scanner;
import vehicle.Vehicle;
import vehicle.VehicleManager;

public class Main {
    public static void main(String[] args) {
        VehicleManager vehicleManager = new VehicleManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Fleet Management System ===");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Search Vehicle");
            System.out.println("3. Remove Vehicle");
            System.out.println("4. Display All Vehicles");
            System.out.println("0. Exit");
            System.out.print("Select an option (or type 'cancel' to stop): ");

            String choiceInput = scanner.nextLine();
            if (choiceInput.equalsIgnoreCase("cancel")) break;
            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please enter a number or 'cancel'.");
                continue;
            }

            switch (choice) {
                case 1:
                    String reg = promptString(scanner, "Enter registration number");
                    if (reg == null) break;
                    String type = promptString(scanner, "Enter type (Truck/Van)");
                    if (type == null) break;
                    Integer mileage = promptInt(scanner, "Enter mileage");
                    if (mileage == null) break;
                    Double fuel = promptDouble(scanner, "Enter fuel usage");
                    if (fuel == null) break;
                    String driver = promptString(scanner, "Enter driver ID");
                    if (driver == null) break;
                    Vehicle newVehicle = new Vehicle(reg, type, mileage, fuel, driver);
                    vehicleManager.addVehicle(newVehicle);
                    System.out.println("Vehicle added.");
                    break;

                case 2:
                    String searchReg = promptString(scanner, "Enter registration number to search");
                    if (searchReg == null) break;
                    Vehicle found = vehicleManager.searchVehicle(searchReg);
                    System.out.println(found != null ? "Found: " + found : "Vehicle not found.");
                    break;

                case 3:
                    String removeReg = promptString(scanner, "Enter registration number to remove");
                    if (removeReg == null) break;
                    boolean removed = vehicleManager.removeVehicle(removeReg);
                    System.out.println(removed ? "Vehicle removed." : "No such vehicle.");
                    break;

                case 4:
                    System.out.println("\nAll Vehicles:");
                    vehicleManager.displayAllVehicles();
                    break;

                case 0:
                    running = false;
                    System.out.println("Exiting system. Goodbye.");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    private static String promptString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " (or 'cancel'): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("cancel")) return null;
            if (!input.trim().isEmpty()) return input.trim();
            System.out.println("Input cannot be empty.");
        }
    }

    private static Integer promptInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " (or 'cancel'): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("cancel")) return null;
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again or type 'cancel'.");
            }
        }
    }

    private static Double promptDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " (or 'cancel'): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("cancel")) return null;
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again or type 'cancel'.");
            }
        }
    }
}
