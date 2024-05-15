package models.builders;

import Utils.ValidationHelpers;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Board;
import models.contracts.Person;
import models.contracts.Bug;
import models.enums.BugStatus;
import models.enums.Priority;
import models.enums.Severity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class BugBuilder extends TaskBuilder<Bug> {
    private List<String> stepsToReproduce;
    private Priority priority;
    private Severity severity;
    private BugStatus status;

    public BugBuilder(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public Bug build() {
        if (repository.getTeams().isEmpty() || repository.getPeople().isEmpty() || repository.getBoards().isEmpty()) {
            return null;
        }
        collectCommonAttributes();

        stepsToReproduce = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter steps to reproduce (end with an empty line): ");
        String step;
        while (!(step = scanner.nextLine()).isEmpty()) {
            stepsToReproduce.add(step);
        }

        System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
        String priorityInput = scanner.nextLine().toUpperCase();
        while (!isValidPriority(priorityInput)) {
            System.out.print("Invalid priority. Enter priority (HIGH, MEDIUM, LOW): ");
            priorityInput = scanner.nextLine().toUpperCase();
        }
        priority = Priority.valueOf(priorityInput);

        System.out.print("Enter severity (CRITICAL, MAJOR, MINOR): ");
        String severityInput = scanner.nextLine().toUpperCase();
        while (!isValidSeverity(severityInput)) {
            System.out.print("Invalid severity. Enter severity (CRITICAL, MAJOR, MINOR): ");
            severityInput = scanner.nextLine().toUpperCase();
        }
        severity = Severity.valueOf(severityInput);

        status = BugStatus.ACTIVE;  // Default status

        return repository.createBug(title, description, stepsToReproduce, priority, severity, status, assignee, board);
    }

    private boolean isValidPriority(String priority) {
        try {
            Priority.valueOf(priority);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isValidSeverity(String severity) {
        try {
            Severity.valueOf(severity);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}