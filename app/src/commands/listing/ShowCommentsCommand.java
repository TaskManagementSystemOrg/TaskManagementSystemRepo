package commands.listing;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;
import java.util.Scanner;

public class ShowCommentsCommand implements Command {
    TaskManagementSystemRepository repository;

    public ShowCommentsCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type help or enter task name: ");
        String input = scanner.nextLine();
        boolean k = true;
        while (k) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println(repository.getTasks());
                System.out.print("Type help or enter task name: ");
                input = scanner.nextLine();
            } else if (repository.getTasks().contains(repository.findTaskByName(input))) {
                System.out.println(repository.findTaskByName(input).getComments());
                k = false;
            }
        }
        return "Done.";

    }
}
