package FuelReportAnalyzer;
import java.util.ArrayList;
import vehicle.Vehicle;

public class SortAlgorithms {

    public static void insertionSortByMileage(ArrayList<Vehicle> list) {
        for (int i = 1; i < list.size(); i++) {
            Vehicle key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getMileage() > key.getMileage()) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    public static void quickSortByReg(ArrayList<Vehicle> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSortByReg(list, low, pi - 1);
            quickSortByReg(list, pi + 1, high);
        }
    }

    private static int partition(ArrayList<Vehicle> list, int low, int high) {
        Vehicle pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getRegNumber().compareTo(pivot.getRegNumber()) < 0) {
                i++;
                Vehicle temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Vehicle temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }
}
