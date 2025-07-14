package vehicle;
import java.util.List;
import java.util.ArrayList;
public class VehicleUtils {
  public static void sortByMileage(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.size() <= 1) return;
        mergeSortMileage(vehicles, 0, vehicles.size() - 1);
    }
    private static void mergeSortMileage(List<Vehicle> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortMileage(list, left, mid);
            mergeSortMileage(list, mid + 1, right);
            mergeMileage(list, left, mid, right);
        }
    }

    private static void mergeMileage(List<Vehicle> list, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        List<Vehicle> leftList = new ArrayList<Vehicle>();
        List<Vehicle> rightList = new ArrayList<Vehicle>();

        for (int i = 0; i < n1; i++) {
            leftList.add(list.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightList.add(list.get(mid + 1 + j));
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftList.get(i).getMileage() <= rightList.get(j).getMileage()) {
                list.set(k++, leftList.get(i++));
            } else {
                list.set(k++, rightList.get(j++));
            }
        }
        while (i < n1) {
            list.set(k++, leftList.get(i++));
        }
        while (j < n2) {
            list.set(k++, rightList.get(j++));
        }
    }

    public static void sortByFuelUsage(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.size() <= 1) return;
        mergeSortFuel(vehicles, 0, vehicles.size() - 1);
    }

    private static void mergeSortFuel(List<Vehicle> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortFuel(list, left, mid);
            mergeSortFuel(list, mid + 1, right);
            mergeFuel(list, left, mid, right);
        }
    }

    private static void mergeFuel(List<Vehicle> list, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        List<Vehicle> leftList = new ArrayList<Vehicle>();
        List<Vehicle> rightList = new ArrayList<Vehicle>();

        for (int i = 0; i < n1; i++) {
            leftList.add(list.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightList.add(list.get(mid + 1 + j));
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftList.get(i).getFuelUsage() <= rightList.get(j).getFuelUsage()) {
                list.set(k++, leftList.get(i++));
            } else {
                list.set(k++, rightList.get(j++));
            }
        }
        while (i < n1) {
            list.set(k++, leftList.get(i++));
        }
        while (j < n2) {
            list.set(k++, rightList.get(j++));
        }
    }
}
