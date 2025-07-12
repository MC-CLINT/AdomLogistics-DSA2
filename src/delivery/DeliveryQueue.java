package delivery;

public class DeliveryQueue {
    private Node front;
    private Node rear;

    public DeliveryQueue() {
        this.front = this.rear = null;
    }

    public void enqueue(PackageDelivery pkg) {
        Node newNode = new Node(pkg);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    public PackageDelivery dequeue() {
        if (front == null)
            return null;
        PackageDelivery pkg = front.data;
        front = front.next;
        if (front == null)
            rear = null;
        return pkg;
    }

    public void showDeliveries() {
        Node current = front;
        if (current == null) {
            System.out.println("No pending deliveries.");
            return;
        }
        System.out.println("Pending Deliveries:");
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
    public void getPackageDetails(String packageId) {
        Node current = front;
        while (current != null) {
            if (current.data.packageId.equals(packageId)) {
                System.out.println(current.data);
                return;
            }
            current = current.next;
        }
        System.out.println("Package not found.");
    }

    public boolean reroute(String packageId, String newDestination, int newETA) {
        Node current = front;
        while (current != null) {
            if (current.data.packageId.equals(packageId)) {
                current.data.updateRoute(newDestination, newETA);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean updateStatus(String packageId, String newStatus) {
        Node current = front;
        while (current != null) {
            if (current.data.packageId.equals(packageId)) {
                current.data.updateStatus(newStatus);
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
