package commands.listing.complex;

import utils.ParsingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Bug;
import models.contracts.Task;
import models.enums.BugStatus;
import models.enums.TaskType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShowAllBugsCommand implements Command {
    private TaskManagementSystemRepository repository;

    public ShowAllBugsCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute() {
        Scanner scanner = new Scanner(System.in);
        String input;
        List<Bug> bugs = new ArrayList<>();
        for (Task task : repository.getTasks()) {
            if (task.getType().equals(TaskType.BUG)) {
                bugs.add((Bug) task);
            }
        }
        if (bugs.isEmpty()) {
            return "====================\nNo bugs created yet.\n====================";
        }

        System.out.println("Filter by status (Active/Done) or enter an empty line: ");
        input = scanner.nextLine();

        if (input.equalsIgnoreCase("")) {
        } else if (input.equalsIgnoreCase("active") || input.equalsIgnoreCase("done")){
            bugs = bugs.stream()
                    .filter(bug -> bug.getStatus().equals(ParsingHelpers.tryParseEnum(input, BugStatus.class, "Not a valid enum.")))
                    .collect(Collectors.toList());
        } else {
            System.out.println("Not a valid filter. Will not filter.");
        }

        System.out.println("Filter by assignee (Type help to see all options) or enter an empty line: ");
        String input2 = scanner.nextLine();
        boolean k = true;
        boolean unsortedByAssignee = false;

        while (k) {
            if (input2.equalsIgnoreCase("")) {
                k = false;
                unsortedByAssignee = true;
            } else if (input2.equalsIgnoreCase("help")) {
                System.out.println(repository.getPeople());
                input2 = scanner.nextLine();
            } else if (repository.getPeople().contains(repository.findPersonByName(input2))) {
                String finalInput = input2;
                bugs = bugs.stream()
                        .filter(bug -> bug.getAssignee().equals(finalInput))
                        .collect(Collectors.toList());
                k = false;
            } else {
                System.out.println("Not a valid filter. Will not filter.");
                k = false;
                unsortedByAssignee = true;
            }
        }

        // title prio severity
        System.out.println("Enter sorting criteria (Title/Priority/Severity) or enter an empty line: ");
        input2 = scanner.nextLine();
        int sortType = 0;
        if (input2.equalsIgnoreCase("")) {
        } else if (input2.equalsIgnoreCase("title")) {
            bugs.sort(Comparator.comparing(Bug::getTitle, String::compareToIgnoreCase));
            sortType = 1;
        } else if (input2.equalsIgnoreCase("priority")) {
            bugs.sort(Comparator.comparing(Bug::getPriority));
            sortType = 2;
        } else if (input2.equalsIgnoreCase("severity")) {
            bugs.sort(Comparator.comparing(Bug::getSeverity));
            sortType = 3;
        } else {
            System.out.println("Not valid criteria. Will not sort.");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("====================\n");
        if (!unsortedByAssignee && !bugs.isEmpty()) {
            stringBuilder.append(String.format("Bugs by: %s\n", bugs.get(0).getAssignee()));
        }
        if (bugs.isEmpty()) {
            stringBuilder.append("No bugs fit criteria.");
        } else {
            for (Bug bug : bugs) {
                stringBuilder.append(bug.toString());
                if (unsortedByAssignee) {
                    stringBuilder.append(String.format(" Assignee: %s", bug.getAssignee()));
                }
                switch (sortType) {
                    case 0, 1:
                        break;
                    case 2:
                        stringBuilder.append(String.format(" Priority: %s", bug.getPriority()));
                        break;
                    case 3:
                        stringBuilder.append(String.format(" Severity: %s", bug.getSeverity()));
                        break;
                }
                stringBuilder.append("\n");
            }
        }
        stringBuilder.append("====================");
        sortType = 0;
        return stringBuilder.toString();
    }
}
