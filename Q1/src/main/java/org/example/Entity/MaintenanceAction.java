package org.example.Entity;
import org.example.Entity.Enum.MaintenanceType;

import java.util.List;
public class MaintenanceAction {
    private String actionId;          // Unique identifier
    private String name;              // Descriptive name
    private MaintenanceType type;     // Maintenance type (Repair, Replace, etc.)
    private double mtbm;              // Mean Time Between Maintenance (in hours)
    private List<MaintenanceTask> tasks;  // List of associated tasks


    // Constructor
    public MaintenanceAction(String actionId, String name, MaintenanceType type,
                             double mtbm, List<MaintenanceTask> tasks) {
        this.actionId = actionId;
        this.name = name;
        this.type = type;
        this.mtbm = mtbm;
        this.tasks = tasks;
    }

    // Getters and Setters
    public String getActionId() { return actionId; }
    public String getName() { return name; }
    public MaintenanceType getType() { return type; }
    public double getMtbm() { return mtbm; }
    public List<MaintenanceTask> getTasks() { return tasks; }

    public void setActionId(String actionId) { this.actionId = actionId; }
    public void setName(String name) { this.name = name; }
    public void setType(MaintenanceType type) { this.type = type; }
    public void setMtbm(double mtbm) { this.mtbm = mtbm; }
    public void setTasks(List<MaintenanceTask> tasks) { this.tasks = tasks; }

    // Add a task to the list
    public void addTask(MaintenanceTask task) {
        tasks.add(task);
    }

    // Calculate the total cost of all tasks
    public double calculateTotalCost() {
        return tasks.stream()
                .mapToDouble(task -> task.getHourlyRate() * task.getNumberOfPersonnel() * task.getDuration())
                .sum();
    }
}
