package vehicle;
import java.util.ArrayList;
public class VehicleManager {
  private VehicleTree vehicleTree;
  private ArrayList<Vehicle> vehicleList;public VehicleManager() {
        vehicleTree = new VehicleTree();
        vehicleList = new ArrayList<Vehicle>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleTree.addVehicle(vehicle);
        vehicleList.add(vehicle);
    }

    public Vehicle searchVehicle(String regNumber) {
        return vehicleTree.searchVehicle(regNumber);
    }

    public boolean removeVehicle(String regNumber) {
        Vehicle toRemove = searchVehicle(regNumber);
        if (toRemove != null) {
            vehicleTree.removeVehicle(regNumber);
            vehicleList.remove(toRemove);
            return true;
        }
        return false;
    }

    public void displayAllVehicles() {
        vehicleTree.displayVehicles();
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return vehicleList;
    }
}

