import delivery.DeliveryQueue;
import delivery.PackageDelivery;

public class Main {
    public static void main(String[] args) {
        // DeliveryQueue queue = new DeliveryQueue();

        // PackageDelivery p1 = new PackageDelivery("PKG001", "Accra", "Kumasi", "Truck-1", "Yaw", 120);
        // PackageDelivery p2 = new PackageDelivery("PKG002", "Tema", "Cape Coast", "Van-3", "Ama", 150);

        // queue.enqueue(p1);
        // queue.enqueue(p2);

        // queue.showDeliveries();

        // System.out.println("\n--- Processing First Delivery ---");
        // PackageDelivery current = queue.dequeue();
        // if (current != null) {
        //     current.updateStatus("In Transit");
        //     System.out.println("Now delivering: " + current);
        // }

        // System.out.println("\n--- Rerouting PKG002 ---");
        // if (queue.reroute("PKG002", "Takoradi", 180)) {
        //     System.out.println("Reroute successful.");
        // } else {
        //     System.out.println("Package not found.");
        // }

        // System.out.println("\n--- Updated Deliveries ---");
        // queue.showDeliveries();
    }
}
