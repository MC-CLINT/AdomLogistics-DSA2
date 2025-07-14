package FuelReportAnalyzer;
import java.util.ArrayList;
import vehicle.Vehicle;

public class FuelReport {

    public static double calculateAverageFuelUsage(ArrayList<Vehicle> vehicles) {
        double total = 0;
        for (Vehicle v : vehicles) {
            total += v.getFuelUsage();
        }
        return vehicles.size() > 0 ? total / vehicles.size() : 0;
    }

    public static ArrayList<Vehicle> flagOutliers(ArrayList<Vehicle> vehicles) {
        double avg = calculateAverageFuelUsage(vehicles);
        double threshold = avg * 1.25;
        ArrayList<Vehicle> flagged = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.getFuelUsage() > threshold) {
                flagged.add(v);
            }
        }
        return flagged;
    }
}
