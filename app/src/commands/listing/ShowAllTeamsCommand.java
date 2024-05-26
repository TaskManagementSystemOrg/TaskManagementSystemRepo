package commands.listing;

import Utils.ListingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Team;

import java.util.List;

public class ShowAllTeamsCommand implements Command {
    private final List<Team> teams;

    public ShowAllTeamsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        teams = taskManagementSystemRepository.getTeams();
    }

    @Override
    public String execute(List<String> parameters) {
        if (teams.isEmpty()) {
            return "There are no teams created yet.";
        }
        return "====================\n" +
                ListingHelpers.elementsToString(teams) +
                "\n====================";
    }
}
