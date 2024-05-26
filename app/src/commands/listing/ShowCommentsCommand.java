package commands.listing;

import utils.ListingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.Scanner;

public class ShowCommentsCommand implements Command {
    TaskManagementSystemRepository repository;

    public ShowCommentsCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type help or enter task name: ");
        String input = scanner.nextLine();
        boolean notDone = true;
        while (notDone) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println("====================");
                System.out.println(ListingHelpers.elementsToString(repository.getTasks()));
                System.out.println("====================");
                System.out.print("Type help or enter task name: ");
                input = scanner.nextLine();
            } else if (repository.getTasks().contains(repository.findTaskByName(input))) {
                if (repository.findTaskByName(input).getComments().isEmpty()) {
                    return "No comments for this task yet.";
                }
                System.out.println("====================");
                System.out.println(ListingHelpers.elementsToString(repository.findTaskByName(input).getComments()));
                System.out.println("====================");
                notDone = false;
            }
        }
        return "Done.";

    }
}
