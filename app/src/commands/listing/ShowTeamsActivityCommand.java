package commands.listing;

import utils.ListingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.Scanner;

public class ShowTeamsActivityCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowTeamsActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute() {
        if (taskManagementSystemRepository.getTeams().isEmpty()) {
            return "No teams created yet.";
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type help to see all options or enter a team name: ");
        String input = scanner.nextLine();
        while (true) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println(ListingHelpers.elementsToString(taskManagementSystemRepository.getTeams()));
                System.out.println("Type help to see all options or enter a team name: ");
                input = scanner.nextLine();
            } else if (taskManagementSystemRepository.getTeams().contains(taskManagementSystemRepository.findTeamByName(input))) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("====================\n");
                for (String string : taskManagementSystemRepository.findTeamByName(input).getActivity()) {
                    stringBuilder.append(string);
                    stringBuilder.append("\n");
                }
                stringBuilder.append("====================");
                return stringBuilder.toString();
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }

        }

    }
}
