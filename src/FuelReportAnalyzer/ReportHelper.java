package FuelReportAnalyzer;
import java.util.ArrayList;
import vehicle.Vehicle;

public class ReportHelper {

    public static void displayVehicleList(ArrayList<Vehicle> vehicles) {
        System.out.println("RegNo\tType\tMileage\tFuel\tDriver");
        System.out.println("----------------------------------------------");
        for (Vehicle v : vehicles) {
            System.out.printf("%s\t%s\t%d\t%.2f\t%s\n",
                v.getRegNumber(), v.getType(), v.getMileage(), v.getFuelUsage(), v.getDriverID());
        }
    }

    public static void printTitle(String title) {
        System.out.println("\n=== " + title.toUpperCase() + " ===");
    }
}
