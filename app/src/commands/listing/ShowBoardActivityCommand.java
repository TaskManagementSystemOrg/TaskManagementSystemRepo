package commands.listing;

import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;
import java.util.Scanner;

public class ShowBoardActivityCommand implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowBoardActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
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
                    System.out.println(taskManagementSystemRepository.getBoards());
                    System.out.println("Type help to see all options or enter board name:");
                    input = scanner.nextLine();
                } else if (taskManagementSystemRepository.findBoardByName(input) != null) {
                    for (String activity : taskManagementSystemRepository.findBoardByName(input).getActivity()) {
                        stringBuilder.append(activity);
                        return stringBuilder.toString();
                    }
                } else {
                    System.out.println("Not a valid board. Try again:");
                    input = scanner.nextLine();
                }
            }
        }
        return "Done";
    }
}
