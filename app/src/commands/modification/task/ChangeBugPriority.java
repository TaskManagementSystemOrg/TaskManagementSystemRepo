package commands.modification.task;

import Utils.ListingHelpers;
import Utils.ParsingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Bug;
import models.contracts.Story;
import models.contracts.Task;
import models.enums.Priority;
import models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChangeBugPriority implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    protected final TaskManagementSystemRepository repository;

    public ChangeBugPriority(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.repository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        Scanner scanner = new Scanner(System.in);
        if (repository.getTasks().stream()
                .noneMatch(task -> task.getType().equals(TaskType.BUG))) {
            return "No bugs created yet.";
        }
        List<Bug> bugs = new ArrayList<>();
        for (Task task : repository.getTasks()) {
            if (task.getType().equals(TaskType.BUG)) {
                bugs.add((Bug) task);
            }
        }


        Bug bug = null;
        System.out.println("Type help to see all options or enter a task: ");
        String input = scanner.nextLine();

        while (true) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println("====================");
                System.out.println(ListingHelpers.elementsToString(bugs));
                System.out.println("====================");
                System.out.println("Type help to see all options or enter task: ");
                input = scanner.nextLine();
            } else if (bugs.contains(repository.findTaskByName(input))) {
                bug = (Bug) repository.findTaskByName(input);
                break;
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }
        }

        System.out.println("Enter new priority(HIGH/MEDIUM/LOW): ");
        input = scanner.nextLine();

        while (true) {
            if (input.equalsIgnoreCase("high") || input.equalsIgnoreCase("medium") || input.equalsIgnoreCase("low")) {
                bug.setPriority(ParsingHelpers.tryParseEnum(input, Priority.class, "Invalid enum."));
                return String.format("%s set to %s priority.", bug.getTitle(), bug.getPriority());
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }
        }



    }
}
