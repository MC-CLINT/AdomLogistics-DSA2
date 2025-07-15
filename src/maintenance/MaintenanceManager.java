package maintenance;

public interface MaintenanceManager {
    void scheduleService(String vehicleId, String date, String[] parts);
    MaintenanceRecord getNextDueService();
    MaintenanceRecord[] getVehicleHistory(String vehicleId);
}
