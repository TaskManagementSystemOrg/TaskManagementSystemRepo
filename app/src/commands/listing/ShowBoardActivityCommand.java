package commands.listing;

import Utils.ListingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.Scanner;

public class ShowBoardActivityCommand implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowBoardActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute() {
        if (taskManagementSystemRepository.getBoards().isEmpty()) {
            return "No boards created yet.";
        }
        Scanner scanner = new Scanner(System.in);
        String input = null;
        StringBuilder stringBuilder = new StringBuilder();
        if (taskManagementSystemRepository.getCurrentBoard() != null) {
            for (String activity : taskManagementSystemRepository.getCurrentBoard().getActivity()) {
                stringBuilder.append(activity);
                return stringBuilder.toString();
            }
        } else {
            System.out.println("Type help to see all options or enter board name:");
            input = scanner.nextLine();
            while (stringBuilder.isEmpty()) {
                if (input.equalsIgnoreCase("help")) {
                    System.out.println("====================");
                    System.out.println(ListingHelpers.elementsToString(taskManagementSystemRepository.getBoards()));
                    System.out.println("====================");
                    System.out.println("Type help to see all options or enter board name:");
                    input = scanner.nextLine();
                } else if (taskManagementSystemRepository.findBoardByName(input) != null) {
                    for (String activity : taskManagementSystemRepository.findBoardByName(input).getActivity()) {
                        stringBuilder.append(activity);
                        return stringBuilder.toString();
                    }
                } else {
                    System.out.println("Not a valid input. Try again:");
                    input = scanner.nextLine();
                }
            }
        }
        return "Done";
    }
}
