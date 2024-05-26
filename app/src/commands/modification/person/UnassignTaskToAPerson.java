package commands.modification.person;

import Utils.ListingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Bug;
import models.contracts.Person;
import models.contracts.Story;
import models.contracts.Task;
import models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UnassignTaskToAPerson implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    protected final TaskManagementSystemRepository repository;

    public UnassignTaskToAPerson(TaskManagementSystemRepository taskManagementSystemRepository) {
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


        List<Bug> bugs = new ArrayList<>();
        List<Story> stories = new ArrayList<>();
        for (Task task : repository.getTasks()) {
            if (task.getType().equals(TaskType.BUG)) {
                bugs.add((Bug) task);
            } else if (task.getType().equals(TaskType.STORY)) {
                stories.add((Story) task);
            }
        }
        Person finalPerson = person;
        bugs = bugs.stream()
                        .filter(bug -> bug.getAssignee().equalsIgnoreCase(finalPerson.getName()))
                        .collect(Collectors.toList());
        stories = stories.stream()
                        .filter(story -> story.getAssignee().equalsIgnoreCase(finalPerson.getName()))
                        .collect(Collectors.toList());
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.addAll(bugs);
        tasks.addAll(stories);


        System.out.println("Type help to see all options or enter a task: ");
        input = scanner.nextLine();

        while (true) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println("====================");
                System.out.println(ListingHelpers.elementsToString(tasks));
                System.out.println("====================");
                System.out.println("Type help to see all options or enter task: ");
                input = scanner.nextLine();
            } else if (tasks.contains(repository.findTaskByName(input))) {

                Task task = repository.findTaskByName(input);
                if (task.getType().equals(TaskType.BUG)){
                    Bug bug = (Bug) task;
                    bug.setAssignee("UNASSIGNED");
                    return String.format("Unassigned %s from %s.", bug.toString(), person.toString());
                } else if (task.getType().equals(TaskType.STORY)) {
                    Story story = (Story) task;
                    story.setAssignee("UNASSIGNED");
                    return String.format("Unassigned %s from %s.", story.toString(), person.toString());
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
