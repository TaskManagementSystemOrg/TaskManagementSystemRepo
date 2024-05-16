package commands.navigation;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;
import java.util.Scanner;

public class EnterBoardCommand implements Command {
    private final TaskManagementSystemRepository repository;

    public EnterBoardCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.repository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type help to see all options or enter a board name to go into: ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("help")) {
            System.out.println(repository.getBoards());
        }
        return "be";
    }
}
