package org.example;

import org.example.Entity.Enum.MaintenanceType;
import org.example.Entity.Enum.PersonnelType;
import org.example.Entity.MaintenanceAction;
import org.example.Entity.MaintenanceTask;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a Maintenance Task
        MaintenanceTask task = new MaintenanceTask("Task1", "Inspection",
                4.0, PersonnelType.TECHNICIAN, 50.0, 2);

        // Modify task details using setters
        task.setDuration(3.5);
        task.setHourlyRate(65.0);

        // Verify changes
        System.out.println("Updated Duration: " + task.getDuration());
        System.out.println("Updated Hourly Rate: " + task.getHourlyRate());

        MaintenanceAction action = new MaintenanceAction("Action1", "ABC Service",
                MaintenanceType.SERVICE, 500, List.of(task));

        action.setName("Annual Service");
        System.out.println("Updated Action Name: " + action.getName());
        System.out.println("total cost: " + action.calculateTotalCost());
    }
}
