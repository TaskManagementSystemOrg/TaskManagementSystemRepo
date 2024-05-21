package commands.navigation.usermanagement;

import commands.contracts.Command;
import commands.enums.UserType;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;
import java.util.Scanner;

public class LogInCommand implements Command {
    TaskManagementSystemRepository repository;

    public LogInCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }


    @Override
    public String execute(List<String> parameters) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 'admin' or 'user' to log in as such:");
        String input = scanner.nextLine();
        while (repository.getCurrentUser().equals(UserType.NOT_LOGGED_IN)) {
            if (input.equalsIgnoreCase("admin")) {
                repository.setCurrentUser(UserType.ADMIN);
            } else if (input.equalsIgnoreCase("user")) {
                repository.setCurrentUser(UserType.NORMAL);
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }
        }
        return String.format("Logged in as %s", repository.getCurrentUser());
    }
}
