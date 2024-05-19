package commands.listing.complex;

import Utils.FilteringHelpers;
import Utils.ListingHelpers;
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
    public String execute(List<String> parameters) {
        if (repository.getTasks().isEmpty()) {
            return "No tasks created.";
        }
        Scanner scanner = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();
        for (Task task : repository.getTasks()) {
            tasks.add(task.toString());
        }
        System.out.println("Enter filter criteria or an empty line for an unfiltered list: ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("")) {
            System.out.println(tasks);
            return "Done!";
        } else {
            List<String> filteredTasks = FilteringHelpers.filter(tasks, input);
            System.out.println(filteredTasks);
        }
        return "Done!";
    }
}
