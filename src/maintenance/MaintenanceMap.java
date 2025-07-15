package maintenance;

public class MaintenanceMap {
    private static class Entry {
        String key; // Vehicle ID
        Entry next; // For collisions
        String[] dates; // Service dates
        String[][] partsHistory; // Parts per date

        Entry(String key) {
            this.key = key;
            this.dates = new String[10]; // Fixed initial size
            this.partsHistory = new String[10][];
        }
    }

    private Entry[] table;
    private int capacity;

    public MaintenanceMap() {
        this.capacity = 16;
        this.table = new Entry[capacity];
    }

    // Add parts for a vehicle on a specific date
    public void addParts(String vehicleId, String date, String[] parts) {
        int index = Math.abs(vehicleId.hashCode()) % capacity;
        Entry current = table[index];

        // Find or create entry
        if (current == null) {
            current = new Entry(vehicleId);
            table[index] = current;
        } else {
            while (!current.key.equals(vehicleId) && current.next != null) {
                current = current.next;
            }
            if (!current.key.equals(vehicleId)) {
                current.next = new Entry(vehicleId);
                current = current.next;
            }
        }

        // Add date and parts
        for (int i = 0; i < current.dates.length; i++) {
            if (current.dates[i] == null) {
                current.dates[i] = date;
                current.partsHistory[i] = parts;
                return;
            }
        }
        // Handle resizing if needed
    }

    // Get all maintenance records for a vehicle
    public MaintenanceRecord[] getRecords(String vehicleId) {
        int index = Math.abs(vehicleId.hashCode()) % capacity;
        Entry current = table[index];

        while (current != null && !current.key.equals(vehicleId)) {
            current = current.next;
        }

        if (current == null) return null;

        int count = 0;
        while (count < current.dates.length && current.dates[count] != null) {
            count++;
        }

        MaintenanceRecord[] records = new MaintenanceRecord[count];
        for (int i = 0; i < count; i++) {
            records[i] = new MaintenanceRecord(
                    vehicleId,
                    current.dates[i],
                    current.partsHistory[i],
                    calculateTotal(current.partsHistory[i])
            );
        }
        return records;
    }

    private double calculateTotal(String[] parts) {
        double total = 0;
        for (int i = 1; i < parts.length; i += 2) {
            total += Double.parseDouble(parts[i]);
        }
        return total;
    }
}
