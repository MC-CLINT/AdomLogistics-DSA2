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

  // Stub methods for each main menu option:
  private void manageVehiclesMenu() {
    System.out.println("--- Manage Vehicles ---");
    // Add submenu logic here (e.g., add/list/remove vehicles)
  }

  private void assignDriversMenu() {
    System.out.println("--- Assign Drivers ---");
    // Add submenu logic here (e.g., assign/list drivers)
  }

  private void trackDeliveriesMenu() {
    System.out.println("--- Track Deliveries ---");
    // Add submenu logic here (e.g., track/view deliveries)
  }

  private void scheduleMaintenanceMenu() {
    System.out.println("--- Schedule Maintenance ---");
    // Add submenu logic here (e.g., schedule/list maintenance)
  }

  private void fuelEfficiencyReportMenu() {
    System.out.println("--- Fuel Efficiency Report ---");
    // Add report logic here (e.g., calculate/view reports)
  }
}