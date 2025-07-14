package vehicle;

import java.util.ArrayList;

public class Vehicle {
    private String regNumber;
    private String type;
    private int mileage;
    private double fuelUsage;
    private String driverID;
    private ArrayList<String> maintenanceHistory;

    public Vehicle(String regNumber, String type, int mileage, double fuelUsage, String driverID) {
        this.regNumber = regNumber;
        this.type = type;
        this.mileage = mileage;
        this.fuelUsage = fuelUsage;
        this.driverID = driverID;
        this.maintenanceHistory = new ArrayList<String>();
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getType() {
        return type;
    }

    public int getMileage() {
        return mileage;
    }

    public double getFuelUsage() {
        return fuelUsage;
    }

    public String getDriverID() {
        return driverID;
    }

    public ArrayList<String> getMaintenanceHistory() {
        return maintenanceHistory;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setFuelUsage(double fuelUsage) {
        this.fuelUsage = fuelUsage;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public void addMaintenanceRecord(String record) {
        maintenanceHistory.add(record);
    }

    @Override
    public String toString() {
        return regNumber + " | "
             + type       + " | "
             + mileage    + " | "
             + fuelUsage  + " | "
             + driverID   + " | "
             + maintenanceHistory;
    }
}
