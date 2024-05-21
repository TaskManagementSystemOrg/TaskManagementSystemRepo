package commands.comments;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Person;
import models.contracts.Task;

import java.util.List;
import java.util.Scanner;

public class AddCommentCommand implements Command {
    TaskManagementSystemRepository repository;

    public AddCommentCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (repository.getCurrentBoard() == null) {
            return "Enter a board first.";
        }
        Scanner scanner = new Scanner(System.in);
        Task taskToComment = null;
        Person author = null;
        String content;
        System.out.println("Type help to see all tasks or enter a task to comment: ");
        String input = scanner.nextLine();
        boolean k = true;
        boolean n = true;
        while (k) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println(repository.getTasks(repository.getCurrentBoard()));
                input = scanner.nextLine();
            } else if (repository.getTasks().contains(repository.findTaskByName(input))) {
                taskToComment = repository.findTaskByName(input);
                k = false;
            } else {
                System.out.println("Invalid name. Type help to see all tasks or enter a task to comment: ");
                input = scanner.nextLine();
            }
        }

        System.out.println("Enter comment content: ");
        input = scanner.nextLine();
        content = input;

        System.out.println("Type help or enter author:");
        input = scanner.nextLine();
        while (n) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println(repository.getPeople());
                input = scanner.nextLine();
            } else if (repository.getPeople().contains(repository.findPersonByName(input))) {
                author = repository.findPersonByName(input);
                n = false;
            } else {
                System.out.println("Invalid name try again.");
                input = scanner.nextLine();
            }
        }
        taskToComment.addComment(repository.createComment(author, content));
        return "Done.";
    }
}
