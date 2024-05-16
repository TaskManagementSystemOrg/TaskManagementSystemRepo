package commands.navigation;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnterBoardCommand implements Command {
    private final TaskManagementSystemRepository repository;

    public EnterBoardCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.repository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        List<String> boards = new ArrayList<>();
        for (Board board : repository.getBoards()) {
            boards.add(board.getName());
        }
        Scanner scanner = new Scanner(System.in);
        Board old = repository.getCurrentBoard();
        System.out.print("Type help to see all options or enter a board name to go into: ");
        String input = scanner.nextLine();
        while (repository.getCurrentBoard() == old) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println(repository.getBoards());
                System.out.println("Type help to see all options or enter a board name to go into: ");
                input = scanner.nextLine();
            } else
            if (boards.contains(input)) {
                repository.setCurrentBoard(repository.findBoardByName(input));
            } else {
                System.out.println("Not a valid board. Type help to see all options or enter a board name to go into:");
                input = scanner.nextLine();
            }
        }
        return "Entered board.";
    }
}
