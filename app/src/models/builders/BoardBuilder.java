package models.builders;

import core.contracts.TaskManagementSystemRepository;
import models.contracts.Board;
import models.BoardImpl;

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
        return repository.createBoardInTeam(name,name);
    }

    @Override
    protected void collectCommonAttributes() {
        if (repository.getTeams().isEmpty()) {
            System.out.println("No teams created.");
            return;
        }
        super.collectCommonAttributes();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type help to see all teams.\nEnter team name (5-15 characters): ");
        teamName = scanner.nextLine();
        if (teamName.equalsIgnoreCase("help")) {
            System.out.println(repository.getTeams().toString());
        }
        while (!isValidName(teamName)) {
            System.out.print("Invalid team name. Enter name (5-15 characters): ");
            teamName = scanner.nextLine();
        }
    }
}
