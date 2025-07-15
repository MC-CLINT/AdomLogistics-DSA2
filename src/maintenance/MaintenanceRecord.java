package maintenance;

public class MaintenanceRecord {
    public final String vehicleId;
    public final String serviceDate;
    public final String[] parts; // Format: ["Oil Filter", "50", "Brake Pad", "75"]
    public final double totalCost;

    public MaintenanceRecord(String vehicleId, String serviceDate,
                             String[] parts, double totalCost) {
        this.vehicleId = vehicleId;
        this.serviceDate = serviceDate;
        this.parts = parts;
        this.totalCost = totalCost;
    }

    // Format: REG123 | 2025-06-01 | [Oil Filter, $50], [Brake Pad, $75] | Total: $125
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(vehicleId).append(" | ")
                .append(serviceDate).append(" | ");

        for (int i = 0; i < parts.length; i += 2) {
            sb.append("[").append(parts[i]).append(", $").append(parts[i+1]).append("]");
            if (i < parts.length - 2) sb.append(", ");
        }

        sb.append(" | Total: $").append(totalCost);
        return sb.toString();
    }

    // Parse from file string
    public static MaintenanceRecord fromFileString(String line) {
        String[] segments = line.split(" \\| ");
        String[] parts = segments[2].replace("[", "").replace("]", "").split(", ");
        String[] partDetails = new String[parts.length * 2];

        for (int i = 0; i < parts.length; i++) {
            String[] pair = parts[i].split(", \\$");
            partDetails[i*2] = pair[0].trim();
            partDetails[i*2+1] = pair[1].trim();
        }

        double total = Double.parseDouble(segments[3].replace("Total: $", "").trim());
        return new MaintenanceRecord(
                segments[0].trim(),
                segments[1].trim(),
                partDetails,
                total
        );
    }
}
