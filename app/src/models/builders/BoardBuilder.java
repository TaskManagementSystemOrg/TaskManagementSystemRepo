package models.builders;

import core.contracts.TaskManagementSystemRepository;
import models.contracts.Board;
import models.contracts.Team;

import java.util.Scanner;

public class BoardBuilder extends EntityBuilder<Board> {
    private String teamName;

    public BoardBuilder(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public Board build() {
        if (repository.getTeams().isEmpty()) {
            System.out.println("No teams created yet. To create a board you need a team to add it to.");
            return null;
        }
        collectCommonAttributes();
        return repository.createBoardInTeam(name, teamName);
    }

    @Override
    protected void collectCommonAttributes() {
        if (repository.getTeams().isEmpty()) {
            System.out.println("No teams created.");
            return;
        }
        super.collectCommonAttributes();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type help to see all teams or enter team name (5-15 characters): ");
        teamName = scanner.nextLine();
        while (!isValidName(teamName) || !repository.getTeams().contains(repository.findTeamByName(teamName))) {
            if (teamName.equalsIgnoreCase("help")) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Team team : repository.getTeams()) {
                    stringBuilder.append(team.toString());
                }
                System.out.println(stringBuilder);
                System.out.println("Enter team name (5-15 characters): ");
                teamName = scanner.nextLine();
                continue;
            }
            System.out.print("Invalid team name. Enter name (5-15 characters): ");
            teamName = scanner.nextLine();
        }
    }
}
