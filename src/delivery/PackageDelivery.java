package delivery;

public class PackageDelivery {
    String packageId;
    String origin;
    String destination;
    String assignedVehicle;
    String assignedDriver;
    int eta;
    String status;

    public PackageDelivery(String packageId, String origin, String destination,
            String vehicle, String driver, int eta) {
        this.packageId = packageId;
        this.origin = origin;
        this.destination = destination;
        this.assignedVehicle = vehicle;
        this.assignedDriver = driver;
        this.eta = eta;
        this.status = "Pending";
    }

    public void updateRoute(String newDestination, int newETA) {
        this.destination = newDestination;
        this.eta = newETA;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    @Override
    public String toString() {
        return "[" + packageId + "] " + origin + " to" + destination +
                " | Vehicle: " + assignedVehicle +
                " | Driver: " + assignedDriver +
                " | ETA: " + eta + " mins | Status: " + status;
    }
}
