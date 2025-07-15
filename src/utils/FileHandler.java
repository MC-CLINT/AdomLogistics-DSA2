package utils;

import vehicle.Vehicle;
import delivery.PackageDelivery;
import java.io.*;
import java.util.*;

public class FileHandler {

    // File path constants
    private static final String VEHICLE_FILE = "data/vehicle.txt";
    private static final String DELIVERY_FILE = "data/delivery.txt";
    // Add more as needed (e.g., DRIVERS_FILE, MAINTENANCE_FILE)

    // Assuming you have a List<Delivery> deliveriesList;
    private List<PackageDelivery> deliveriesList;

    public PackageDelivery getDelivery(String deliveryId) {
        for (PackageDelivery delivery : deliveriesList) {
            if (delivery.getPackageId().equals(deliveryId)) {
                return delivery;
            }
        }
        return null; // Not found
    }

    // === VEHICLE METHODS ===

    public List<Vehicle> readVehiclesFromFile() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(VEHICLE_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(" \\| ", 6);
                    if (parts.length >= 6) {
                        String regNumber = parts[0].trim();
                        String type = parts[1].trim();
                        int mileage = Integer.parseInt(parts[2].trim());
                        double fuelUsage = Double.parseDouble(parts[3].trim());
                        String driverID = parts[4].trim();
                        String maintenanceRaw = parts[5].trim();

                        Vehicle vehicle = new Vehicle(regNumber, type, mileage, fuelUsage, driverID);

                        ArrayList<String> maintenanceHistory = new ArrayList<>();
                        int i = 0;
                        while (i < maintenanceRaw.length()) {
                            int start = maintenanceRaw.indexOf('[', i);
                            if (start == -1) break;
                            int end = maintenanceRaw.indexOf(']', start);
                            if (end == -1) break;
                            String record = maintenanceRaw.substring(start + 1, end).trim();
                            maintenanceHistory.add(record);
                            i = end + 1;
                        }
                        for (String record : maintenanceHistory) {
                            vehicle.addMaintenanceRecord(record);
                        }

                        vehicles.add(vehicle);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading vehicles: " + e.getMessage());
        }
        return vehicles;
    }

    public void writeVehiclesToFile(List<Vehicle> vehicles) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(VEHICLE_FILE))) {
            for (Vehicle v : vehicles) {
                StringBuilder sb = new StringBuilder();
                sb.append(v.getRegNumber()).append(" | ")
                  .append(v.getType()).append(" | ")
                  .append(v.getMileage()).append(" | ")
                  .append(v.getFuelUsage()).append(" | ")
                  .append(v.getDriverID()).append(" | ");

                ArrayList<String> history = v.getMaintenanceHistory();
                for (int i = 0; i < history.size(); i++) {
                    sb.append("[").append(history.get(i)).append("]");
                    if (i < history.size() - 1) sb.append(", ");
                }

                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing vehicles: " + e.getMessage());
        }
    }

    public boolean addVehicleToFile(Vehicle vehicle) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(VEHICLE_FILE, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(vehicle.getRegNumber()).append(" | ")
              .append(vehicle.getType()).append(" | ")
              .append(vehicle.getMileage()).append(" | ")
              .append(vehicle.getFuelUsage()).append(" | ")
              .append(vehicle.getDriverID()).append(" | ");

            ArrayList<String> history = vehicle.getMaintenanceHistory();
            for (int i = 0; i < history.size(); i++) {
                sb.append("[").append(history.get(i)).append("]");
                if (i < history.size() - 1) sb.append(", ");
            }
            bw.write(sb.toString());
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error appending vehicle: " + e.getMessage());
            return false;
        }
    }

    public boolean removeVehicleFromFile(String regNumber) {
        List<Vehicle> vehicles = readVehiclesFromFile();
        boolean removed = vehicles.removeIf(v -> v.getRegNumber().equalsIgnoreCase(regNumber));
        if (removed) {
            writeVehiclesToFile(vehicles);
        }
        return removed;
    }

    public boolean updateVehicleInFile(String regNumber, Vehicle updatedVehicle) {
        List<Vehicle> vehicles = readVehiclesFromFile();
        boolean updated = false;
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            if (v.getRegNumber().equalsIgnoreCase(regNumber)) {
                vehicles.set(i, updatedVehicle);
                updated = true;
                break;
            }
        }
        if (updated) {
            writeVehiclesToFile(vehicles);
        }
        return updated;
    }

    // === DELIVERY METHODS ===

    public List<PackageDelivery> readDeliveriesFromFile() {
        List<PackageDelivery> deliveries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DELIVERY_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(" \\| ");
                    if (parts.length >= 7) {
                        String packageId = parts[0].trim();
                        String origin = parts[1].trim();
                        String destination = parts[2].trim();
                        String assignedVehicle = parts[3].trim();
                        String assignedDriver = parts[4].trim();
                        int eta = Integer.parseInt(parts[5].trim());
                        String status = parts[6].trim();

                        PackageDelivery pd = new PackageDelivery(packageId, origin, destination, assignedVehicle, assignedDriver, eta);
                        pd.updateStatus(status);
                        deliveries.add(pd);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading deliveries: " + e.getMessage());
        }
        return deliveries;
    }

    public void writeDeliveriesToFile(List<PackageDelivery> deliveries) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DELIVERY_FILE))) {
            for (PackageDelivery pd : deliveries) {
                bw.write(pd.getPackageId() + " | " + pd.getOrigin() + " | " + pd.getDestination() + " | " +
                         pd.getAssignedVehicle() + " | " + pd.getAssignedDriver() + " | " + pd.getEta() + " | " + pd.getStatus());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing deliveries: " + e.getMessage());
        }
    }

    public boolean addDeliveryToFile(PackageDelivery pd) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DELIVERY_FILE, true))) {
            bw.write(pd.getPackageId() + " | " + pd.getOrigin() + " | " + pd.getDestination() + " | " +
                     pd.getAssignedVehicle() + " | " + pd.getAssignedDriver() + " | " + pd.getEta() + " | " + pd.getStatus());
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error adding delivery: " + e.getMessage());
            return false;
        }
    }

    public boolean removeDeliveryFromFile(String packageId) {
        List<PackageDelivery> deliveries = readDeliveriesFromFile();
        boolean removed = deliveries.removeIf(pd -> pd.getPackageId().equalsIgnoreCase(packageId));
        if (removed) {
            writeDeliveriesToFile(deliveries);
        }
        return removed;
    }

    public boolean updateDeliveryInFile(String packageId, PackageDelivery updatedPd) {
        List<PackageDelivery> deliveries = readDeliveriesFromFile();
        boolean updated = false;
        for (int i = 0; i < deliveries.size(); i++) {
            if (deliveries.get(i).getPackageId().equalsIgnoreCase(packageId)) {
                deliveries.set(i, updatedPd);
                updated = true;
                break;
            }
        }
        if (updated) {
            writeDeliveriesToFile(deliveries);
        }
        return updated;
    }
}