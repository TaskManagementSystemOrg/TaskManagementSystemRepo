package commands.modification.task;

import commands.helpers.GetListOf;
import utils.ListingHelpers;
import utils.ParsingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Bug;
import models.contracts.Task;
import models.enums.Severity;
import models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeBugSeverity implements Command {
    protected final TaskManagementSystemRepository repository;

    public ChangeBugSeverity(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.repository = taskManagementSystemRepository;
    }

    @Override
    public String execute() {
        Scanner scanner = new Scanner(System.in);
        if (repository.getTasks().stream()
                .noneMatch(task -> task.getType().equals(TaskType.BUG))) {
            return "No bugs created yet.";
        }

        List<Bug> bugs = GetListOf.bugs(repository);


        Bug bug = null;
        System.out.println("Type help to see all options or enter a task: ");
        String input = scanner.nextLine();

        while (true) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println(ListingHelpers.elementsToString(bugs));
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

        System.out.print("Enter new severity(CRITICAL/MAJOR/MINOR): ");
        input = scanner.nextLine();

        while (true) {
            if (input.equalsIgnoreCase(Severity.CRITICAL.toString()) || input.equalsIgnoreCase(Severity.MAJOR.toString())
                    || input.equalsIgnoreCase(Severity.MINOR.toString())) {
                bug.setSeverity(ParsingHelpers.tryParseEnum(input, Severity.class, "Invalid enum."));
                return String.format("%s severity set to %s.", bug.getTitle(), bug.getSeverity());
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }
        }


    }
}
