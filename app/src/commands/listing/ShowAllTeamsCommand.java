package commands.listing;

import utils.ListingHelpers;
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
    public String execute() {
        if (teams.isEmpty()) {
            return "There are no teams created yet.";
        }
        return ListingHelpers.elementsToString(teams);
    }
}
