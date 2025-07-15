package utils;

import vehicle.Vehicle;
import delivery.PackageDelivery;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

// Fuel report analyzer imports
import FuelReportAnalyzer.FuelReport;
import FuelReportAnalyzer.PerformanceFilter;
import FuelReportAnalyzer.ReportHelper;
import FuelReportAnalyzer.SortAlgorithms;

public class CLI {
    private Scanner scanner = new Scanner(System.in);
    private FileHandler fileHandler = new FileHandler();

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

    public void start() {
        boolean running = true;
        while (running) {
            showMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    manageVehiclesMenu();
                    break;
                case "2":
                    assignDriversMenu();
                    break;
                case "3":
                    trackDeliveriesMenu();
                    break;
                case "4":
                    scheduleMaintenanceMenu();
                    break;
                case "5":
                    fuelEfficiencyReportMenu();
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

    

    private void manageVehiclesMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n=== Manage Vehicles ===");
            System.out.println("[1] Add Vehicle");
            System.out.println("[2] List Vehicles");
            System.out.println("[3] Remove Vehicle");
            System.out.println("[4] Update Vehicle");
            System.out.println("[0] Back to Main Menu");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("\n--- Add New Vehicle ---");
                    System.out.println("Note: Registration number must be unique and contain only letters/numbers.");
                    System.out.print("Enter registration number (e.g., REG123): ");
                    String regNumber = scanner.nextLine().trim();
                    System.out.print("Enter vehicle type (e.g., Truck, Van, Car): ");
                    String type = scanner.nextLine().trim();
                    System.out.print("Enter mileage (as a whole number, e.g., 120000): ");
                    int mileage = getValidIntInput();
                    System.out.print("Enter fuel usage (as a decimal number, e.g., 12.5): ");
                    double fuelUsage = getValidDoubleInput();
                    System.out.print("Enter driver ID (e.g., DR001): ");
                    String driverID = scanner.nextLine().trim();

                    Vehicle newVehicle = new Vehicle(regNumber, type, mileage, fuelUsage, driverID);

                    boolean success = fileHandler.addVehicleToFile(newVehicle);
                    if (success)
                        System.out.println("Vehicle added successfully!");
                    else
                        System.out.println("Failed to add vehicle. Please ensure the vehicle registration number is unique and the data directory exists.");
                    break;
                case "2":
                    System.out.println("List Vehicles selected.");
                    List<Vehicle> vehicles = fileHandler.readVehiclesFromFile();
                    if (vehicles.isEmpty()) {
                        System.out.println("No vehicles found.");
                    } else {
                        for (Vehicle v : vehicles) {
                            System.out.println(v); // Uses Vehicle's toString()
                        }
                    }
                    break;
                case "3":
                    System.out.println("--- Remove Vehicle ---");
                    System.out.print("Enter registration number of vehicle to remove (e.g., REG123): ");
                    String regNumRemove = scanner.nextLine().trim();
                    boolean removed = fileHandler.removeVehicleFromFile(regNumRemove);
                    if (removed)
                        System.out.println("Vehicle removed successfully!");
                    else
                        System.out.println("Vehicle not found.");
                    break;
                case "4":
                    System.out.println("--- Update Vehicle ---");
                    System.out.print("Enter registration number of vehicle to update (e.g., REG123): ");
                    String regNumUpdate = scanner.nextLine().trim();
                    List<Vehicle> allVehicles = fileHandler.readVehiclesFromFile();
                    Vehicle vehicleToUpdate = null;
                    for (Vehicle v : allVehicles) {
                        if (v.getRegNumber().equalsIgnoreCase(regNumUpdate)) {
                            vehicleToUpdate = v;
                            break;
                        }
                    }
                    if (vehicleToUpdate == null) {
                        System.out.println("Vehicle not found.");
                        break;
                    }
                    System.out.println("Current details: " + vehicleToUpdate);
                    System.out.print("Enter new vehicle type (leave blank to keep current): ");
                    String newType = scanner.nextLine().trim();
                    System.out.print("Enter new mileage (leave blank to keep current): ");
                    String newMileageStr = scanner.nextLine().trim();
                    System.out.print("Enter new fuel usage (leave blank to keep current): ");
                    String newFuelUsageStr = scanner.nextLine().trim();
                    System.out.print("Enter new driver ID (leave blank to keep current): ");
                    String newDriverID = scanner.nextLine().trim();

                    String updatedType = newType.isEmpty() ? vehicleToUpdate.getType() : newType;
                    int updatedMileage = newMileageStr.isEmpty() ? vehicleToUpdate.getMileage() : parseOrDefaultInt(newMileageStr, vehicleToUpdate.getMileage());
                    double updatedFuelUsage = newFuelUsageStr.isEmpty() ? vehicleToUpdate.getFuelUsage()
                            : parseOrDefaultDouble(newFuelUsageStr, vehicleToUpdate.getFuelUsage());
                    String updatedDriverID = newDriverID.isEmpty() ? vehicleToUpdate.getDriverID() : newDriverID;

                    Vehicle updatedVehicle = new Vehicle(
                            vehicleToUpdate.getRegNumber(),
                            updatedType,
                            updatedMileage,
                            updatedFuelUsage,
                            updatedDriverID);
                    for (String record : vehicleToUpdate.getMaintenanceHistory()) {
                        updatedVehicle.addMaintenanceRecord(record);
                    }

                    boolean isUpdated = fileHandler.updateVehicleInFile(regNumUpdate, updatedVehicle);
                    if (isUpdated)
                        System.out.println("Vehicle updated successfully!");
                    else
                        System.out.println("Failed to update vehicle.");
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private void assignDriversMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n=== Assign Drivers ===");
            System.out.println("[1] Add Driver");
            System.out.println("[2] List Drivers");
            System.out.println("[3] Remove Driver");
            System.out.println("[4] Update Driver");
            System.out.println("[0] Back to Main Menu");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("\n--- Add New Driver ---");
                    System.out.println("Format: ID (e.g., DR001), Name, License (e.g., GH-DL-1234)");
                    
                    break;
                case "2":
                    System.out.println("List Drivers selected.");
                   
                    break;
                case "3":
                    System.out.println("Remove Driver selected.");
                    System.out.print("Enter driver ID to remove (e.g., DR001): ");
              
                    break;
                case "4":
                    System.out.println("Update Driver selected.");
                    System.out.print("Enter driver ID to update (e.g., DR001): ");
             
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private void trackDeliveriesMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("--- Track Deliveries ---");
            System.out.println("[1] Add Delivery");
            System.out.println("[2] List Deliveries");
            System.out.println("[3] Remove Delivery");
            System.out.println("[4] Update Delivery");
            System.out.println("[0] Back to Main Menu");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("--- Add Delivery ---");
                    System.out.println("Format: Package ID (e.g., PKG001), Origin (city), Destination (city), Assigned Vehicle (e.g., REG123), Assigned Driver (e.g., DR001), ETA (minutes, integer)");
                    System.out.print("Enter package ID (e.g., PKG001): ");
                    String packageId = scanner.nextLine().trim();
                    System.out.print("Enter origin: ");
                    String origin = scanner.nextLine().trim();
                    System.out.print("Enter destination: ");
                    String destination = scanner.nextLine().trim();
                    System.out.print("Enter assigned vehicle registration number (e.g., REG123): ");
                    String assignedVehicle = scanner.nextLine().trim();
                    System.out.print("Enter assigned driver ID (e.g., DR001): ");
                    String assignedDriver = scanner.nextLine().trim();
                    System.out.print("Enter ETA (minutes, as a whole number): ");
                    int eta = getValidIntInput();

                    PackageDelivery newDelivery = new PackageDelivery(packageId, origin, destination, assignedVehicle, assignedDriver, eta);
                    boolean deliveryAdded = fileHandler.addDeliveryToFile(newDelivery);
                    if (deliveryAdded)
                        System.out.println("Delivery added successfully!");
                    else
                        System.out.println("Failed to add delivery.");
                    break;
                case "2":
                    System.out.println("--- List Deliveries ---");
                    List<PackageDelivery> deliveries = fileHandler.readDeliveriesFromFile();
                    if (deliveries.isEmpty()) {
                        System.out.println("No deliveries found.");
                    } else {
                        for (PackageDelivery pd : deliveries) {
                            System.out.println(pd);
                        }
                    }
                    break;
                case "3":
                    System.out.println("--- Remove Delivery ---");
                    System.out.print("Enter package ID to remove (e.g., PKG001): ");
                    String pkgRemove = scanner.nextLine().trim();
                    boolean deliveryRemoved = fileHandler.removeDeliveryFromFile(pkgRemove);
                    if (deliveryRemoved)
                        System.out.println("Delivery removed successfully!");
                    else
                        System.out.println("Delivery not found.");
                    break;
                case "4":
                    System.out.println("--- Update Delivery ---");
                    System.out.print("Enter package ID to update (e.g., PKG001): ");
                    String pkgUpdate = scanner.nextLine().trim();
                    List<PackageDelivery> allDeliveries = fileHandler.readDeliveriesFromFile();
                    PackageDelivery deliveryToUpdate = null;
                    for (PackageDelivery pd : allDeliveries) {
                        if (pd.getPackageId().equalsIgnoreCase(pkgUpdate)) {
                            deliveryToUpdate = pd;
                            break;
                        }
                    }
                    if (deliveryToUpdate == null) {
                        System.out.println("Delivery not found.");
                        break;
                    }
                    System.out.println("Current details: " + deliveryToUpdate);
                    System.out.print("Enter new destination (leave blank to keep current): ");
                    String newDest = scanner.nextLine().trim();
                    System.out.print("Enter new ETA (leave blank to keep current): ");
                    String newEtaStr = scanner.nextLine().trim();
                    System.out.print("Enter new status (leave blank to keep current): ");
                    String newStatus = scanner.nextLine().trim();

                    String updatedDestination = newDest.isEmpty() ? deliveryToUpdate.getDestination() : newDest;
                    int updatedEta = newEtaStr.isEmpty() ? deliveryToUpdate.getEta() : parseOrDefaultInt(newEtaStr, deliveryToUpdate.getEta());
                    String updatedStatus = newStatus.isEmpty() ? deliveryToUpdate.getStatus() : newStatus;

                    PackageDelivery updatedDelivery = new PackageDelivery(
                            deliveryToUpdate.getPackageId(),
                            deliveryToUpdate.getOrigin(),
                            updatedDestination,
                            deliveryToUpdate.getAssignedVehicle(),
                            deliveryToUpdate.getAssignedDriver(),
                            updatedEta);
                    updatedDelivery.updateStatus(updatedStatus);

                    boolean deliveryUpdated = fileHandler.updateDeliveryInFile(pkgUpdate, updatedDelivery);
                    if (deliveryUpdated)
                        System.out.println("Delivery updated successfully!");
                    else
                        System.out.println("Failed to update delivery.");
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private void scheduleMaintenanceMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("--- Schedule Maintenance ---");
            System.out.println("[1] Add Maintenance Record");
            System.out.println("[2] List Maintenance Records");
            System.out.println("[3] Remove Maintenance Record");
            System.out.println("[4] Update Maintenance Record");
            System.out.println("[0] Back to Main Menu");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("Add Maintenance Record selected.");
                    System.out.println("Format: Vehicle Registration (e.g., REG123), Date (YYYY-MM-DD), Description (e.g., Oil Filter), Cost (e.g., 50.0)");
                    // TODO: Integrate with Maintenance logic and prompt for correct format
                    break;
                case "2":
                    System.out.println("List Maintenance Records selected.");
                    
                    break;
                case "3":
                    System.out.println("Remove Maintenance Record selected.");
                    System.out.print("Enter vehicle registration (e.g., REG123) and maintenance date (YYYY-MM-DD): ");
               
                    break;
                case "4":
                    System.out.println("Update Maintenance Record selected.");
                    System.out.print("Enter vehicle registration (e.g., REG123) and maintenance date (YYYY-MM-DD): ");
                  
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private void fuelEfficiencyReportMenu() {
        System.out.println("\n=== Fuel Efficiency Report ===");
        List<Vehicle> vehicles = fileHandler.readVehiclesFromFile();
        ArrayList<Vehicle> fleet = new ArrayList<>(vehicles);

        if (fleet.isEmpty()) {
            System.out.println("No vehicles found for reporting.");
            return;
        }

        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n[1] Show all vehicles");
            System.out.println("[2] Show vehicles sorted by mileage");
            System.out.println("[3] Show fuel usage outliers");
            System.out.println("[4] Show efficient fleet (by criteria)");
            System.out.println("[0] Back to Main Menu");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    ReportHelper.printTitle("Original Fleet");
                    ReportHelper.displayVehicleList(fleet);
                    break;
                case "2":
                    ReportHelper.printTitle("Sorted by Mileage");
                    ArrayList<Vehicle> sorted = new ArrayList<>(fleet);
                    SortAlgorithms.insertionSortByMileage(sorted);
                    ReportHelper.displayVehicleList(sorted);
                    break;
                case "3":
                    ReportHelper.printTitle("Fuel Outliers (above 125% avg)");
                    ArrayList<Vehicle> outliers = FuelReport.flagOutliers(fleet);
                    if (outliers.isEmpty()) {
                        System.out.println("No outliers found.");
                    } else {
                        ReportHelper.displayVehicleList(outliers);
                    }
                    break;
                case "4":
                    try {
                        System.out.print("Enter minimum mileage (as a whole number): ");
                        int minMileage = getValidIntInput();
                        System.out.print("Enter maximum fuel usage (as a decimal number): ");
                        double maxFuel = getValidDoubleInput();
                        ReportHelper.printTitle("Efficient Fleet");
                        ArrayList<Vehicle> efficient = PerformanceFilter.filterEfficientFleet(fleet, minMileage, maxFuel);
                        if (efficient.isEmpty()) {
                            System.out.println("No vehicles match the efficiency criteria.");
                        } else {
                            ReportHelper.displayVehicleList(efficient);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid numbers.");
                    }
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Helper methods for input validation
    private int getValidIntInput() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid whole number: ");
            }
        }
    }

    private double getValidDoubleInput() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid decimal number: ");
            }
        }
    }

    private int parseOrDefaultInt(String input, int defaultValue) {
        if (input == null || input.isEmpty()) return defaultValue;
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private double parseOrDefaultDouble(String input, double defaultValue) {
        if (input == null || input.isEmpty()) return defaultValue;
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private String getValidRegNumber() {
        while (true) {
            System.out.print("Enter registration number (e.g., AS12345623): ");
            String regNumber = scanner.nextLine().trim();
            // Ghanaian format: 2 letters, 4 digits, 2 digits (year)
            if (!regNumber.matches("^[A-Za-z]{2}\d{4}\d{2}$")) {
                System.out.println("Invalid format. Registration number must be two letters, four digits, and two digits for the year (e.g., AS123423). Try again.");
                continue;
            }
            List<Vehicle> vehicles = fileHandler.readVehiclesFromFile();
            boolean exists = false;
            for (Vehicle v : vehicles) {
                if (v.getRegNumber().equalsIgnoreCase(regNumber)) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                System.out.println("Registration number already exists. Please enter a unique value.");
                continue;
            }
            return regNumber;
        }
    }
}