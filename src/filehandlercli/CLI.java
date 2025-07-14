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
          // TODO: Integrate with Vehicle logic (e.g., FileHandler.listVehicles())
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

  private void assignDriversMenu() {
    System.out.println("--- Assign Drivers ---");
    // TODO: Integrate with Driver logic (e.g., FileHandler.assignDriver(scanner))
    // TODO: Add submenu options if needed
  }

  private void trackDeliveriesMenu() {
    System.out.println("--- Track Deliveries ---");
    // TODO: Integrate with Delivery logic (e.g., FileHandler.trackDeliveries())
    // TODO: Add submenu options if needed
  }

  private void scheduleMaintenanceMenu() {
    System.out.println("--- Schedule Maintenance ---");
    // TODO: Integrate with Maintenance logic (e.g.,
    // FileHandler.scheduleMaintenance(scanner))
    // TODO: Add submenu options if needed
  }

  private void fuelEfficiencyReportMenu() {
    System.out.println("--- Fuel Efficiency Report ---");
    // TODO: Integrate with Report logic (e.g., FileHandler.fuelEfficiencyReport())
  }
}