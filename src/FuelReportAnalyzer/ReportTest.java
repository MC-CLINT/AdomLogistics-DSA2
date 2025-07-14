package FuelReportAnalyzer;
import java.util.ArrayList;
import vehicle.Vehicle;
import vehicle.VehicleManager;

public class ReportTest {

    public static void main(String[] args) {
        VehicleManager manager = new VehicleManager();

        manager.addVehicle(new Vehicle("GT1234", "Truck", 12000, 760, "D001"));
        manager.addVehicle(new Vehicle("GR5678", "Van", 8300, 520, "D002"));
        manager.addVehicle(new Vehicle("GT9012", "Truck", 15000, 980, "D003"));
        manager.addVehicle(new Vehicle("GE2345", "Van", 4000, 380, "D004"));
        manager.addVehicle(new Vehicle("GC1111", "Truck", 7000, 500, "D005"));

        ArrayList<Vehicle> fleet = manager.getAllVehicles();

        ReportHelper.printTitle("Original Fleet");
        ReportHelper.displayVehicleList(fleet);

        ReportHelper.printTitle("Sorted by Mileage");
        SortAlgorithms.insertionSortByMileage(fleet);
        ReportHelper.displayVehicleList(fleet);

        ReportHelper.printTitle("Fuel Outliers");
        ArrayList<Vehicle> outliers = FuelReport.flagOutliers(fleet);
        ReportHelper.displayVehicleList(outliers);

        ReportHelper.printTitle("Efficient Fleet");
        ArrayList<Vehicle> efficientFleet = PerformanceFilter.filterEfficientFleet(fleet, 8000, 600);
        ReportHelper.displayVehicleList(efficientFleet);
    }
}
