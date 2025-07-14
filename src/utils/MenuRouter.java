package filehandlercli;

public class MenuRouter {

  public void route(int choice) {
    switch (choice) {
      case 1:
        // Call vehicle management menu
        System.out.println("Routing to Vehicle Management...");
        break;
      case 2:
        // Call driver assignment menu
        System.out.println("Routing to Driver Assignment...");
        break;
      case 3:
        // Call delivery tracking menu
        System.out.println("Routing to Delivery Tracking...");
        break;
      case 4:
        // Call maintenance scheduling menu
        System.out.println("Routing to Maintenance Scheduling...");
        break;
      case 5:
        // Call fuel efficiency report menu
        System.out.println("Routing to Fuel Efficiency Report...");
        break;
      case 0:
        System.out.println("Exiting system. Goodbye!");
        break;
      default:
        System.out.println("Invalid menu choice.");
    }
  }
}