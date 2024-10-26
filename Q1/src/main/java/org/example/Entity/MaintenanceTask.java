package org.example.Entity;

import org.example.Entity.Enum.PersonnelType;

public class MaintenanceTask {
    private String taskId;      // Unique identifier for the task
    private String name;        // Descriptive name
    private double duration;    // Duration in hours
    private PersonnelType personnelType; // Type of personnel needed
    private double hourlyRate;  // Hourly rate for the personnel
    private int numberOfPersonnel; // Number of personnel required

    // Constructor
    public MaintenanceTask(String taskId, String name, double duration,
                           PersonnelType personnelType, double hourlyRate,
                           int numberOfPersonnel) {
        this.taskId = taskId;
        this.name = name;
        this.duration = duration;
        this.personnelType = personnelType;
        this.hourlyRate = hourlyRate;
        this.numberOfPersonnel = numberOfPersonnel;
    }

    // Getters and Setters
    public String getTaskId() { return taskId; }
    public String getName() { return name; }
    public double getDuration() { return duration; }
    public PersonnelType getPersonnelType() { return personnelType; }
    public double getHourlyRate() { return hourlyRate; }
    public int getNumberOfPersonnel() { return numberOfPersonnel; }

    // Setters
    public void setTaskId(String taskId) { this.taskId = taskId; }
    public void setName(String name) { this.name = name; }
    public void setDuration(double duration) { this.duration = duration; }
    public void setPersonnelType(PersonnelType personnelType) { this.personnelType = personnelType; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }
    public void setNumberOfPersonnel(int numberOfPersonnel) { this.numberOfPersonnel = numberOfPersonnel; }
}
