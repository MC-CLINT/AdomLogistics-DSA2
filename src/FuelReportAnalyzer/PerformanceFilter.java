package FuelReportAnalyzer;
import java.util.ArrayList;
import vehicle.Vehicle;

public class PerformanceFilter {

    public static ArrayList<Vehicle> filterByMileage(ArrayList<Vehicle> vehicles, int minMileage) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.getMileage() >= minMileage) {
                result.add(v);
            }
        }
        return result;
    }

    public static ArrayList<Vehicle> filterEfficientFleet(ArrayList<Vehicle> vehicles, int minMileage, double maxFuelUsage) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.getMileage() >= minMileage && v.getFuelUsage() <= maxFuelUsage) {
                result.add(v);
            }
        }
        return result;
    }
}

