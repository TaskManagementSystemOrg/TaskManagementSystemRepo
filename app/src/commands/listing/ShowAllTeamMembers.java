package commands.listing;

import Utils.ListingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.Scanner;

public class ShowAllTeamMembers implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowAllTeamMembers(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute() {
        if (taskManagementSystemRepository.getTeams().isEmpty()) {
            return "No teams created yet.";
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter team name or type help to see all teams: ");
        String input = scanner.nextLine();
        while (true) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println(ListingHelpers.elementsToString(taskManagementSystemRepository.getTeams()));
                System.out.println("Enter team name or type help to see all teams: ");
                input = scanner.nextLine();
            } else if (taskManagementSystemRepository.getTeams().contains(taskManagementSystemRepository.findTeamByName(input))) {
                if (taskManagementSystemRepository.findTeamByName(input).getMembers().isEmpty()) {
                    return "No members in team yet.";
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (String string: taskManagementSystemRepository.findTeamByName(input).getMembers()) {
                    stringBuilder.append("====================\n");
                    stringBuilder.append("Name: ");
                    stringBuilder.append(string);
                    stringBuilder.append("\n");
                    stringBuilder.append("====================");
                }
                return stringBuilder.toString();
            } else {
                System.out.println("Not a valid input. Try again.");
                input = scanner.nextLine();
            }
        }
    }
}
