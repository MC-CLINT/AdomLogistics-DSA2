package driver;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    private String driverID;
    private String name;
    private int yearsOfExperience;
    private String regionTag;
    private String currentAssignment;
    private List<String> infractions;
    private List<String> routeHistory;

    public Driver(String driverID, String name, int yearsOfExperience, String regionTag) {
        this.driverID = driverID;
        this.name = name;
        this.yearsOfExperience = yearsOfExperience;
        this.regionTag = regionTag;
        this.currentAssignment = "";
        this.infractions = new ArrayList<>();
        this.routeHistory = new ArrayList<>();
    }

    public String getDriverID() { return driverID; }
    public String getName() { return name; }
    public int getYearsOfExperience() { return yearsOfExperience; }
    public String getRegionTag() { return regionTag; }
    public String getCurrentAssignment() { return currentAssignment; }

    public void setCurrentAssignment(String assignment) {
        this.currentAssignment = assignment;
        if (!assignment.isEmpty()) {
            routeHistory.add(assignment);
        }
    }

    public List<String> getInfractions() { return infractions; }
    public List<String> getRouteHistory() { return routeHistory; }

    @Override
    public String toString() {
        return String.join(" | ",
            driverID,
            name,
            yearsOfExperience + "yrs",
            regionTag,
            "[" + String.join(", ", routeHistory) + "]",
            "[" + String.join(", ", infractions) + "]"
        );
    }
}
