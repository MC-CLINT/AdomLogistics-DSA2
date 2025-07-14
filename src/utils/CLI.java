package utils;

import vehicle.Vehicle;
import java.util.List;
import java.util.Scanner;

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
      System.out.println("--- Manage Vehicles ---");
      System.out.println("[1] Add Vehicle");
      System.out.println("[2] List Vehicles");
      System.out.println("[3] Remove Vehicle");
      System.out.println("[4] Update Vehicle");
      System.out.println("[0] Back to Main Menu");
      System.out.print("Enter choice: ");
      String choice = scanner.nextLine().trim();
      switch (choice) {
        case "1":
          System.out.println("Add Vehicle selected.");
          // TODO: Integrate with Vehicle logic (e.g., FileHandler.addVehicle(scanner))
          break;
        case "2":
          System.out.println("List Vehicles selected.");
          List<Vehicle> vehicles = fileHandler.readVehiclesFromFile("../data/vehicle.txt");
          if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
          } else {
            for (Vehicle v : vehicles) {
              System.out.println(v); // Uses Vehicle's toString()
            }
          }
          break;
        case "3":
          System.out.println("Remove Vehicle selected.");
          // TODO: Integrate with Vehicle logic (e.g., FileHandler.removeVehicle(scanner))
          break;
        case "4":
          System.out.println("Update Vehicle selected.");
          // TODO: Integrate with Vehicle logic (e.g., FileHandler.updateVehicle(scanner))
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

  // The rest of your menu methods below remain unchanged
  private void assignDriversMenu() {
    boolean inMenu = true;
    while (inMenu) {
      System.out.println("--- Assign Drivers ---");
      System.out.println("[1] Add Driver");
      System.out.println("[2] List Drivers");
      System.out.println("[3] Remove Driver");
      System.out.println("[4] Update Driver");
      System.out.println("[0] Back to Main Menu");
      System.out.print("Enter choice: ");
      String choice = scanner.nextLine().trim();
      switch (choice) {
        case "1":
          System.out.println("Add Driver selected.");
          // TODO: Integrate with Driver logic
          break;
        case "2":
          System.out.println("List Drivers selected.");
          // TODO: Integrate with Driver logic
          break;
        case "3":
          System.out.println("Remove Driver selected.");
          // TODO: Integrate with Driver logic
          break;
        case "4":
          System.out.println("Update Driver selected.");
          // TODO: Integrate with Driver logic
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
          System.out.println("Add Delivery selected.");
          // TODO: Integrate with Delivery logic
          break;
        case "2":
          System.out.println("List Deliveries selected.");
          // TODO: Integrate with Delivery logic
          break;
        case "3":
          System.out.println("Remove Delivery selected.");
          // TODO: Integrate with Delivery logic
          break;
        case "4":
          System.out.println("Update Delivery selected.");
          // TODO: Integrate with Delivery logic
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
          // TODO: Integrate with Maintenance logic
          break;
        case "2":
          System.out.println("List Maintenance Records selected.");
          // TODO: Integrate with Maintenance logic
          break;
        case "3":
          System.out.println("Remove Maintenance Record selected.");
          // TODO: Integrate with Maintenance logic
          break;
        case "4":
          System.out.println("Update Maintenance Record selected.");
          // TODO: Integrate with Maintenance logic
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
    System.out.println("--- Fuel Efficiency Report ---");
    // TODO: Integrate with Report logic (e.g., FileHandler.fuelEfficiencyReport())
  }
}