package driver;

import java.util.List;

public class DriverTracker {
    private DriverManager manager;

    public DriverTracker(DriverManager manager) {
        this.manager = manager;
    }

    public void trackDelay(String driverID, String route) {
        manager.trackDelay(driverID, route);
    }

    public void recordInfraction(String driverID, String note) {
        manager.recordInfraction(driverID, note);
    }

    public void viewDriverHistory(String driverID) {
        List<String> history = manager.viewDriverHistory(driverID);
        System.out.println("History for " + driverID + ":");
        history.forEach(System.out::println);
    }
}
