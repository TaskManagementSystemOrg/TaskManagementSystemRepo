package commands.modification.person;

import utils.ListingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Bug;
import models.contracts.Person;
import models.contracts.Story;
import models.contracts.Task;
import models.enums.TaskType;

import java.util.Scanner;

public class AssignTaskToAPerson implements Command {
    protected final TaskManagementSystemRepository repository;

    public AssignTaskToAPerson(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.repository = taskManagementSystemRepository;
    }

    @Override
    public String execute() {
        if (repository.getTasks().isEmpty() || repository.getPeople().isEmpty()) {
            return "No tasks and/or people created yet.";
        }
        Scanner scanner = new Scanner(System.in);
        Person person = null;

        System.out.println("Type help to see all options or enter person: ");
        String input = scanner.nextLine();

        while (true) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println("====================");
                System.out.println(ListingHelpers.elementsToString(repository.getPeople()));
                System.out.println("====================");
                System.out.println("Type help to see all options or enter person: ");
                input = scanner.nextLine();
            } else if (repository.getPeople().contains(repository.findPersonByName(input))) {
                person = repository.findPersonByName(input);
                break;
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }
        }

        System.out.println("Type help to see all options or enter a task: ");
        input = scanner.nextLine();

        while (true) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println("====================");
                System.out.println(ListingHelpers.elementsToString(repository.getTasks()));
                System.out.println("====================");
                System.out.println("Type help to see all options or enter task: ");
                input = scanner.nextLine();
            } else if (repository.getTasks().contains(repository.findTaskByName(input))) {
                Task task = repository.findTaskByName(input);
                if (task.getType().equals(TaskType.BUG)){
                    Bug bug = (Bug) task;
                    bug.setAssignee(person.getName());
                    return String.format("Assigned %s to %s.", bug.toString(), person.toString());
                } else if (task.getType().equals(TaskType.STORY)) {
                    Story story = (Story) task;
                    story.setAssignee(person.getName());
                    return String.format("Assigned %s to %s.", story.toString(), person.toString());
                } else {
                    return "Task can't have an assignee.";
                }
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }
        }


    }
}
