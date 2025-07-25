package driver;

import java.io.*;
import java.util.*;

public class DriverManager {
    private static final String DATA_FILE = "data/drivers.txt";
    private Map<String, Driver> drivers = new HashMap<>();
    private DriverQueue queue = new DriverQueue();
    private List<Driver> driversList = new ArrayList<>();

    public DriverManager() {
        loadDrivers();
    }

    public void addDriver(Driver d) {
        drivers.put(d.getDriverID(), d);
        driversList.add(d);
        queue.enqueue(d);
        saveDrivers();
    }

    public Driver assignDriver() {
        Driver d = queue.dequeue();
        if (d != null) {
            String assignment = "Assigned at " + new Date();
            d.setCurrentAssignment(assignment);
            saveDrivers();
        }
        return d;
    }

    public void trackDelay(String driverID, String route) {
        Driver d = drivers.get(driverID);
        if (d != null) {
            d.getRouteHistory().add("Delay: " + route + " - " + new Date());
            saveDrivers();
        }
    }

    public void recordInfraction(String driverID, String note) {
        Driver d = drivers.get(driverID);
        if (d != null) {
            d.getInfractions().add("Infraction: " + note + " - " + new Date());
            saveDrivers();
        }
    }

    public List<String> viewDriverHistory(String driverID) {
        Driver d = drivers.get(driverID);
        if (d != null) {
            List<String> history = new ArrayList<>();
            history.addAll(d.getRouteHistory());
            history.addAll(d.getInfractions());
            return history;
        }
        return Collections.emptyList();
    }

    public List<Driver> getDrivers() {
        return new ArrayList<>(driversList);
    }

    public boolean removeDriver(String id) {
        Driver removed = drivers.remove(id);
        if (removed != null) {
            driversList.removeIf(driver -> driver.getDriverID().equals(id));
            queue.removeDriver(id);
            saveDrivers();
            return true;
        }
        return false;
    }

    public Driver getDriver(String id) {
        return drivers.get(id);
    }

    public boolean replaceDriver(String id, Driver newDriver) {
        if (drivers.containsKey(id)) {
            drivers.put(id, newDriver);
            for (int i = 0; i < driversList.size(); i++) {
                if (driversList.get(i).getDriverID().equals(id)) {
                    driversList.set(i, newDriver);
                    break;
                }
            }
            queue.replaceDriver(id, newDriver);
            saveDrivers();
            return true;
        }
        return false;
    }

    private void loadDrivers() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s*\\|\\s*");
                if (parts.length < 6) continue;
                Driver d = new Driver(
                    parts[0],
                    parts[1],
                    Integer.parseInt(parts[2].replace("yrs", "")),
                    parts[3]
                );
                String routes = parts[4].replaceAll("[\\[\\]]", "");
                if (!routes.isEmpty()) {
                    for (String entry : routes.split(", ")) {
                        d.getRouteHistory().add(entry);
                    }
                }
                String infs = parts[5].replaceAll("[\\[\\]]", "");
                if (!infs.isEmpty()) {
                    for (String entry : infs.split(", ")) {
                        d.getInfractions().add(entry);
                    }
                }
                drivers.put(d.getDriverID(), d);
                driversList.add(d);
                queue.enqueue(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDrivers() {
        try {
            File dir = new File("data");
            if (!dir.exists()) dir.mkdirs();
            try (PrintWriter pw = new PrintWriter(new FileWriter(DATA_FILE))) {
                for (Driver d : drivers.values()) {
                    pw.println(d.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

