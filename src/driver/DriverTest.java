package driver;

public class DriverTest {
    public static void main(String[] args) {
        DriverManager dm = new DriverManager();

        Driver d1 = new Driver("DR001", "Kwesi Mensah", 5, "REG123");
        Driver d2 = new Driver("DR002", "Ama Serwaa", 3, "REG456");

        dm.addDriver(d1);
        dm.addDriver(d2);

        Driver assigned = dm.assignDriver();
        System.out.println("Assigned: " + (assigned != null ? assigned.getDriverID() : "None"));

        dm.trackDelay("DR002", "Tema Loop");
        dm.recordInfraction("DR002", "Speeding");

        DriverTracker tracker = new DriverTracker(dm);
        tracker.viewDriverHistory("DR002");
    }
}
