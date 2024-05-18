package commands.navigation;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Board;
import models.contracts.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnterTeamCommand implements Command {
    private final TaskManagementSystemRepository repository;

    public EnterTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.repository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (repository.getTeams().isEmpty()) {
            return "Create a team first.";
        }
        if (repository.getCurrentTeam() != null) {
            return "Exit current team first, then enter a new one";
        }
        String input;
        List<String> teams = new ArrayList<>();
        for (Team team : repository.getTeams()) {
            teams.add(team.getName());
        }
        Scanner scanner = new Scanner(System.in);
        Team oldTeam = repository.getCurrentTeam();
        System.out.print("Type help to see all options or enter a team name: ");

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

        return String.format("Entered team %s",repository.getCurrentTeam());
    }
}
