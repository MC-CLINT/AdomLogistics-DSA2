package utils;

import vehicle.Vehicle;
import java.io.*;
import java.util.*;

public class FileHandler {

    public List<Vehicle> readVehiclesFromFile(String filename) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    // Split the line into parts. There may be extra " | " inside the maintenanceHistory, so limit to 6 splits.
                    String[] parts = line.split(" \\| ", 6);
                    if (parts.length >= 6) {
                        String regNumber = parts[0].trim();
                        String type = parts[1].trim();
                        int mileage = Integer.parseInt(parts[2].trim());
                        double fuelUsage = Double.parseDouble(parts[3].trim());
                        String driverID = parts[4].trim();
                        String maintenanceRaw = parts[5].trim();

                        Vehicle vehicle = new Vehicle(regNumber, type, mileage, fuelUsage, driverID);

                        // Parse maintenanceHistory: entries like [2025-06-01: Oil Filter, $50], [2025-08-14: Brake Pad, $75]
                        ArrayList<String> maintenanceHistory = new ArrayList<>();
                        // Find all [ ... ] blocks
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
                        // Add each record
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

    public void writeVehiclesToFile(List<Vehicle> vehicles, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Vehicle v : vehicles) {
                StringBuilder sb = new StringBuilder();
                sb.append(v.getRegNumber()).append(" | ")
                  .append(v.getType()).append(" | ")
                  .append(v.getMileage()).append(" | ")
                  .append(v.getFuelUsage()).append(" | ")
                  .append(v.getDriverID()).append(" | ");

                // Format maintenanceHistory
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
}