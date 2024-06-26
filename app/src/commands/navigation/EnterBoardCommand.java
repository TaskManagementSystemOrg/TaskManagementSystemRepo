package commands.navigation;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Board;
import models.contracts.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnterBoardCommand implements Command {
    private final TaskManagementSystemRepository repository;

    public EnterBoardCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.repository = taskManagementSystemRepository;
    }

    @Override
    public String execute() {
        if (repository.getBoards().isEmpty() || repository.getTeams().isEmpty()) {
            return "Create a board and team first.";
        }
        String input;
        List<String> boards = new ArrayList<>();
        List<String> teams = new ArrayList<>();
        for (Team team : repository.getTeams()) {
            teams.add(team.getName());
        }
        Scanner scanner = new Scanner(System.in);
        Board oldBoard = repository.getCurrentBoard();
        Team oldTeam = repository.getCurrentTeam();

        if (repository.getCurrentTeam() == null) {
            System.out.print("You need to enter a team first. Type help to see all options or enter a team name: ");
            input = scanner.nextLine();
            while (repository.getCurrentTeam() == oldTeam) {
                if (input.equalsIgnoreCase("help")) {
                    System.out.println(repository.getTeams());
                    System.out.print("Type help to see all options or enter a team name: ");
                    input = scanner.nextLine();
                } else if (teams.contains(input)) {
                    repository.setCurrentTeam(repository.findTeamByName(input));
                } else {
                    System.out.println("Not a valid team. Type help to see all options or enter a team name: ");
                    input = scanner.nextLine();
                }
            }
        }
        for (String boardName : repository.getCurrentTeam().getBoards()) {
            Board board = repository.findBoardByName(boardName);
            boards.add(board.getName());
        }


        System.out.print("Type help to see all options or enter a board name to go into: ");
        input = scanner.nextLine();
        while (repository.getCurrentBoard() == oldBoard) {
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
        return String.format("Entered board %s in team %s.", repository.getCurrentBoard().getName(), repository.getCurrentTeam().getName());
    }
}
