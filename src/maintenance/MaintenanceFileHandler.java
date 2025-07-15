package maintenance;

public class MaintenanceFileHandler {
    private static final int MAX_SIZE = 1000;
    private static String[] fileLines = new String[MAX_SIZE];
    private static int lineCount = 0;

    // Simulate file write
    public static void saveRecord(String line) {
        if (lineCount < MAX_SIZE) {
            fileLines[lineCount++] = line;
        }
    }

    // Simulate file read
    public static String[] readAllRecords() {
        String[] lines = new String[lineCount];
        for (int i = 0; i < lineCount; i++) {
            lines[i] = fileLines[i];
        }
        return lines;
    }
}
