package commands.listing;

import utils.ListingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.Scanner;

public class ShowAllTeamBoardsCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowAllTeamBoardsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute() {
        if (taskManagementSystemRepository.getBoards().isEmpty()) {
            return "Create a team and board first";
        }
        if (taskManagementSystemRepository.getCurrentTeam() != null) {
            return ListingHelpers.elementsToString(taskManagementSystemRepository.getBoards(taskManagementSystemRepository.getCurrentTeam()));
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
                if (taskManagementSystemRepository.getBoards(taskManagementSystemRepository.findTeamByName(input)).isEmpty()) {
                    return "No boards created in this team yet.";
                }
                return ListingHelpers.elementsToString(taskManagementSystemRepository.getBoards(taskManagementSystemRepository.findTeamByName(input)));
//                return ListingHelpers.elementsToString(taskManagementSystemRepository.getBoards(taskManagementSystemRepository.findTeamByName(input)));
            } else {
                System.out.println("Not a valid input. Try again.");
                input = scanner.nextLine();
            }
        }
    }
}
