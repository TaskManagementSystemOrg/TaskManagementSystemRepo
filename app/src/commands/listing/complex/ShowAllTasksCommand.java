package commands.listing.complex;

import utils.FilteringHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShowAllTasksCommand implements Command {
    TaskManagementSystemRepository repository;

    public ShowAllTasksCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute() {
        if (repository.getTasks().isEmpty()) {
            return "No tasks created.";
        }

        Scanner scanner = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();
        for (Task task : repository.getTasks()) {
            tasks.add(task.getTitle());
        }

        System.out.println("Enter filter criteria or an empty line for an unfiltered list: ");
        String input = scanner.nextLine();
        List<String> filteredTasks;
        if (input.equalsIgnoreCase("")) {
            filteredTasks = new ArrayList<>(tasks);
        } else {
            filteredTasks = FilteringHelpers.filter(tasks, input);
        }

        System.out.println("Do you want to sort the tasks? yes/no");
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("yes")) {
            filteredTasks.sort(String::compareToIgnoreCase);
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==========\n");
        for (String taskTitle : filteredTasks) {
            stringBuilder.append(repository.findTaskByName(taskTitle).toString());
            stringBuilder.append("\n");
        }
        stringBuilder.append("==========");

        return stringBuilder.toString();
    }
}
