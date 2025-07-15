package maintenance;

public class MaintenanceTester {
    public static void main(String[] args) {
        // 1. Initialize components
        MaintenanceMap map = new MaintenanceMap();
        MaintenanceScheduler scheduler = new MaintenanceScheduler(10);

        // 2. Create sample parts data
        String[] oilChangeParts = {"Oil Filter", "30", "Synthetic Oil", "45"};
        String[] brakeParts = {"Brake Pads", "75", "Brake Fluid", "20"};

        // 3. Add records to Map and Scheduler
        map.addParts("TRUCK01", "2025-06-15", oilChangeParts);
        map.addParts("TRUCK01", "2025-07-20", brakeParts);

        MaintenanceRecord oilChange = new MaintenanceRecord("TRUCK01", "2025-06-15", oilChangeParts, 75.0);
        MaintenanceRecord brakeService = new MaintenanceRecord("TRUCK01", "2025-07-20", brakeParts, 95.0);

        scheduler.schedule(oilChange);
        scheduler.schedule(brakeService);

        // 4. Test MaintenanceMap
        System.out.println("--- Maintenance Records for TRUCK01 ---");
        MaintenanceRecord[] records = map.getRecords("TRUCK01");
        for (MaintenanceRecord r : records) {
            if (r != null) {
                System.out.println(r.toFileString());
                MaintenanceFileHandler.saveRecord(r.toFileString()); // Save to simulated file
            }
        }

        // 5. Test Scheduler
        System.out.println("\n--- Next Service Due ---");
        MaintenanceRecord next = scheduler.getNextDue();
        System.out.println(next.toFileString());

        // 6. Test FileHandler
        System.out.println("\n--- Saved Records ---");
        String[] savedLines = MaintenanceFileHandler.readAllRecords();
        for (String line : savedLines) {
            System.out.println(line);
        }
    }
}
