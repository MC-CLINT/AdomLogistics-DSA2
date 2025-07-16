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
    private driver.DriverManager driverManager = new driver.DriverManager();
    public void showMainMenu() {
        System.out.println("\n╔═════════════════════════════════════════╗");
        System.out.println("║     Adom Logistics Dispatcher System    ║");
        System.out.println("╚═════════════════════════════════════════╝");
        System.out.println("\nA comprehensive fleet management solution for tracking");
        System.out.println("vehicles, drivers, deliveries, maintenance and fuel efficiency.\n");
        System.out.println("What would you like to do today?\n");
        System.out.println("    [1] Manage Vehicles");
        System.out.println("    [2] Assign Drivers"); 
        System.out.println("    [3] Track Deliveries");
        System.out.println("    [4] Schedule Maintenance");
        System.out.println("    [5] Fuel Efficiency Report");
        System.out.println("\n    [0] Exit");
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
                    System.out.print("Enter driver ID (e.g., DR001): ");
                    String driverId = scanner.nextLine().trim();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter years of experience: ");
                    int years = getValidIntInput();
                    System.out.print("Enter license/region tag: ");
                    String license = scanner.nextLine().trim();
                    driver.Driver newDriver = new driver.Driver(driverId, name, years, license);
                    driverManager.addDriver(newDriver);
                    System.out.println("Driver added successfully!");
                    break;
                case "2":
                    System.out.println("--- List Drivers ---");
                    List<driver.Driver> drivers = driverManager.getDrivers();
                    if (drivers.isEmpty()) System.out.println("No drivers found.");
                    else for (driver.Driver d : drivers) System.out.println(d);
                    break;
                case "3":
                    System.out.println("Remove Driver selected.");
                    System.out.print("Enter driver ID to remove (e.g., DR001): ");
                    String removeId = scanner.nextLine().trim();
                    boolean removed = driverManager.removeDriver(removeId);
                    if (removed) System.out.println("Driver removed successfully!");
                    else System.out.println("Driver not found.");
                    break;
                case "4":
                    System.out.println("Update Driver selected.");
                    System.out.print("Enter driver ID to update (e.g., DR001): ");
                    String updateId = scanner.nextLine().trim();
                    driver.Driver driverToUpdate = driverManager.getDriver(updateId);
                    if (driverToUpdate == null) {
                        System.out.println("Driver not found.");
                        break;
                    }
                    System.out.println("Current details: " + driverToUpdate);
                    System.out.print("Enter new name (leave blank to keep current): ");
                    String newName = scanner.nextLine().trim();
                    System.out.print("Enter new years of experience (leave blank to keep current): ");
                    String newYearsStr = scanner.nextLine().trim();
                    System.out.print("Enter new license/region tag (leave blank to keep current): ");
                    String newLicense = scanner.nextLine().trim();
                    String updatedName = newName.isEmpty() ? driverToUpdate.getName() : newName;
                    int updatedYears = newYearsStr.isEmpty() ? driverToUpdate.getYearsOfExperience() : parseOrDefaultInt(newYearsStr, driverToUpdate.getYearsOfExperience());
                    String updatedLicense = newLicense.isEmpty() ? driverToUpdate.getRegionTag() : newLicense;
                    driver.Driver updatedDriver = new driver.Driver(updateId, updatedName, updatedYears, updatedLicense);
                    boolean updated = driverManager.replaceDriver(updateId, updatedDriver);
                    if (updated) System.out.println("Driver updated successfully!");
                    else System.out.println("Failed to update driver.");
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
            System.out.println("\n--- Track Deliveries ---");
            System.out.println("[1] Add Delivery");
            System.out.println("[2] List Deliveries");
            System.out.println("[3] Remove Delivery");
            System.out.println("[4] Update Delivery");
            System.out.println("[0] Back to Main Menu");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("\n--- Add Delivery ---");
                    System.out.print("Enter package ID (e.g., PKG001): ");
                    String packageId = scanner.nextLine().trim();
                    System.out.print("Enter origin: ");
                    String origin = scanner.nextLine().trim();
                    System.out.print("Enter destination: ");
                    String destination = scanner.nextLine().trim();
                    System.out.print("Enter assigned vehicle (e.g., REG123): ");
                    String assignedVehicle = scanner.nextLine().trim();
                    System.out.print("Enter assigned driver (e.g., DR001): ");
                    String assignedDriver = scanner.nextLine().trim();
                    System.out.print("Enter ETA (minutes): ");
                    int eta = getValidIntInput();
                    PackageDelivery newDelivery = new PackageDelivery(packageId, origin, destination, assignedVehicle, assignedDriver, eta);
                    boolean added = fileHandler.addDeliveryToFile(newDelivery);
                    if (added) System.out.println("Delivery added successfully!");
                    else System.out.println("Failed to add delivery. Package ID may already exist.");
                    break;
                case "2":
                    System.out.println("\n--- List Deliveries ---");
                    List<PackageDelivery> deliveries = fileHandler.readDeliveriesFromFile();
                    if (deliveries.isEmpty()) System.out.println("No deliveries found.");
                    else for (PackageDelivery d : deliveries) System.out.println(d);
                    break;
                case "3":
                    System.out.println("\n--- Remove Delivery ---");
                    System.out.print("Enter vehicle registration number (e.g., REG123): ");
                    String regNumRemove = scanner.nextLine().trim();
                    System.out.print("Enter maintenance date (YYYY-MM-DD): ");
                    String dateRemove = scanner.nextLine().trim();
                    Vehicle vehicleRemove = null;
                    List<Vehicle> vehiclesRemove = fileHandler.readVehiclesFromFile();
                    for (Vehicle v : vehiclesRemove) {
                        if (v.getRegNumber().equalsIgnoreCase(regNumRemove)) {
                            vehicleRemove = v;
                            break;
                        }
                    }
                    if (vehicleRemove == null) {
                        System.out.println("Vehicle not found.");
                    } else {
                        boolean removed = vehicleRemove.removeMaintenanceRecordByDate(dateRemove);
                        if (removed) {
                            boolean updated = fileHandler.updateVehicleInFile(regNumRemove, vehicleRemove);
                            if (updated) System.out.println("Maintenance record removed successfully!");
                            else System.out.println("Failed to update vehicle file.");
                        } else {
                            System.out.println("Maintenance record not found for the given date.");
                        }
                    }
                    break;
                case "4":
                    System.out.println("\n--- Update Maintenance Record ---");
                    System.out.print("Enter vehicle registration number (e.g., REG123): ");
                    String regNumUpdate = scanner.nextLine().trim();
                    System.out.print("Enter maintenance date to update (YYYY-MM-DD): ");
                    String dateUpdate = scanner.nextLine().trim();
                    Vehicle vehicleUpdate = null;
                    List<Vehicle> vehiclesUpdate = fileHandler.readVehiclesFromFile();
                    for (Vehicle v : vehiclesUpdate) {
                        if (v.getRegNumber().equalsIgnoreCase(regNumUpdate)) {
                            vehicleUpdate = v;
                            break;
                        }
                    }
                    if (vehicleUpdate == null) {
                        System.out.println("Vehicle not found.");
                    } else {
                        boolean found = false;
                        List<String> records = vehicleUpdate.getMaintenanceHistory();
                        for (int i = 0; i < records.size(); i++) {
                            String[] parts = records.get(i).split("\\|");
                            if (parts.length > 0 && parts[0].equals(dateUpdate)) {
                                System.out.print("Enter new description (leave blank to keep current): ");
                                String newDesc = scanner.nextLine().trim();
                                System.out.print("Enter new cost (leave blank to keep current): ");
                                String newCostStr = scanner.nextLine().trim();
                                String desc = newDesc.isEmpty() ? parts[1] : newDesc;
                                double updatedCost = newCostStr.isEmpty() ? Double.parseDouble(parts[2]) : parseOrDefaultDouble(newCostStr, Double.parseDouble(parts[2]));
                                String updatedRecord = String.format("%s|%s|%.2f|%s", dateUpdate, desc, updatedCost, regNumUpdate);
                                records.set(i, updatedRecord);
                                found = true;
                                break;
                            }
                        }
                        if (found) {
                            boolean updated = fileHandler.updateVehicleInFile(regNumUpdate, vehicleUpdate);
                            if (updated) System.out.println("Maintenance record updated successfully!");
                            else System.out.println("Failed to update vehicle file.");
                        } else {
                            System.out.println("Maintenance record not found for the given date.");
                        }
                    }
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

    public String getValidRegNumber() {
        while (true) {
            System.out.print("Enter registration number (e.g., AS12345623): ");
            String regNumber = scanner.nextLine().trim();
            // Ghanaian format: 2 letters, 4 digits, 2 digits (year)
            if (!regNumber.matches("^[A-Za-z]{2}\\d{4}\\d{2}$")) {
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

    private void scheduleMaintenanceMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n=== Schedule Maintenance ===");
            System.out.println("[1] Add Maintenance Record");
            System.out.println("[2] List Maintenance Records");
            System.out.println("[3] Remove Maintenance Record");
            System.out.println("[4] Update Maintenance Record");
            System.out.println("[0] Back to Main Menu");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();
    
            switch (choice) {
                case "1":
                    addMaintenanceRecord();
                    break;
                case "2":
                    listMaintenanceRecords();
                    break;
                case "3":
                    removeMaintenanceRecord();
                    break;
                case "4":
                    updateMaintenanceRecord();
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


    private void addMaintenanceRecord() {
        System.out.println("\n--- Add Maintenance Record ---");
        System.out.print("Enter vehicle registration number (e.g., REG123): ");
        String regNumber = scanner.nextLine().trim();
    
        List<Vehicle> vehicles = fileHandler.readVehiclesFromFile();
        Vehicle targetVehicle = null;
    
        for (Vehicle v : vehicles) {
            if (v.getRegNumber().equalsIgnoreCase(regNumber)) {
                targetVehicle = v;
                break;
            }
        }
    
        if (targetVehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
    
        System.out.print("Enter maintenance date (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();
        System.out.print("Enter maintenance description: ");
        String description = scanner.nextLine().trim();
        System.out.print("Enter maintenance cost: ");
        double cost = getValidDoubleInput();
    
        String record = String.format("%s|%s|%.2f|%s", date, description, cost, regNumber);
        targetVehicle.addMaintenanceRecord(record);
    
        boolean updated = fileHandler.updateVehicleInFile(regNumber, targetVehicle);
        if (updated) {
            System.out.println("Maintenance record added successfully!");
        } else {
            System.out.println("Failed to update vehicle file.");
        }
    }

    private void listMaintenanceRecords() {
        System.out.println("\n--- List Maintenance Records ---");
        System.out.print("Enter vehicle registration number (e.g., REG123): ");
        String regNumber = scanner.nextLine().trim();
    
        List<Vehicle> vehicles = fileHandler.readVehiclesFromFile();
        Vehicle vehicle = null;
        for (Vehicle v : vehicles) {
            if (v.getRegNumber().equalsIgnoreCase(regNumber)) {
                vehicle = v;
                break;
            }
        }
    
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
    
        List<String> maintenanceRecords = vehicle.getMaintenanceHistory();
        if (maintenanceRecords.isEmpty()) {
            System.out.println("No maintenance records found for this vehicle.");
        } else {
            System.out.println("Maintenance Records for " + regNumber + ":");
            for (String record : maintenanceRecords) {
                System.out.println(record);
            }
        }
    }

    private void removeMaintenanceRecord() {
        System.out.println("\n--- Remove Maintenance Record ---");
        System.out.print("Enter vehicle registration number (e.g., REG123): ");
        String regNumber = scanner.nextLine().trim();
    
        List<Vehicle> vehicles = fileHandler.readVehiclesFromFile();
        Vehicle vehicle = null;
        for (Vehicle v : vehicles) {
            if (v.getRegNumber().equalsIgnoreCase(regNumber)) {
                vehicle = v;
                break;
            }
        }
    
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
    
        System.out.print("Enter maintenance date to remove (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();
    
        boolean removed = vehicle.removeMaintenanceRecordByDate(date);
        if (removed) {
            boolean updated = fileHandler.updateVehicleInFile(regNumber, vehicle);
            if (updated) {
                System.out.println("Maintenance record removed successfully.");
            } else {
                System.out.println("Failed to update vehicle file after removing record.");
            }
        } else {
            System.out.println("No maintenance record found for the given date.");
        }
    }
    private void updateMaintenanceRecord() {
        System.out.println("\n--- Update Maintenance Record ---");
        System.out.print("Enter vehicle registration number (e.g., REG123): ");
        String regNumber = scanner.nextLine().trim();
    
        List<Vehicle> vehicles = fileHandler.readVehiclesFromFile();
        Vehicle vehicle = null;
        for (Vehicle v : vehicles) {
            if (v.getRegNumber().equalsIgnoreCase(regNumber)) {
                vehicle = v;
                break;
            }
        }
    
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
    
        System.out.print("Enter maintenance date to update (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();
    
        boolean found = false;
        List<String> records = vehicle.getMaintenanceHistory();
    
        for (int i = 0; i < records.size(); i++) {
            String[] parts = records.get(i).split("\\|");
            if (parts.length > 0 && parts[0].equals(date)) {
                System.out.println("Current Description: " + parts[1]);
                System.out.print("Enter new description (leave blank to keep current): ");
                String newDesc = scanner.nextLine().trim();
    
                System.out.println("Current Cost: " + parts[2]);
                System.out.print("Enter new cost (leave blank to keep current): ");
                String newCostStr = scanner.nextLine().trim();
    
                String desc = newDesc.isEmpty() ? parts[1] : newDesc;
                double updatedCost = newCostStr.isEmpty() ? Double.parseDouble(parts[2]) : parseOrDefaultDouble(newCostStr, Double.parseDouble(parts[2]));
    
                String updatedRecord = String.format("%s|%s|%.2f|%s", date, desc, updatedCost, regNumber);
                records.set(i, updatedRecord);
                found = true;
                break;
            }
        }
    
        if (found) {
            boolean updated = fileHandler.updateVehicleInFile(regNumber, vehicle);
            if (updated) {
                System.out.println("Maintenance record updated successfully.");
            } else {
                System.out.println("Failed to update vehicle file after modifying record.");
            }
        } else {
            System.out.println("No maintenance record found for the given date.");
        }
    }
        
    
}