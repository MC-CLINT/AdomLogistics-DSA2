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

    // --- Getters for use in other packages ---
    public String getPackageId() { return packageId; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getAssignedVehicle() { return assignedVehicle; }
    public String getAssignedDriver() { return assignedDriver; }
    public int getEta() { return eta; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "[" + packageId + "] " + origin + " to " + destination +
                " | Vehicle: " + assignedVehicle +
                " | Driver: " + assignedDriver +
                " | ETA: " + eta + " mins | Status: " + status;
    }
}