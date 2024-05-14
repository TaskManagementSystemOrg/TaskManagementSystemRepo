package commands.creation;

import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.builders.TeamBuilder;
import models.contracts.Team;

import java.util.List;

public class CreateNewTeamCommand extends CreateNewEntityCommand<Team> {
    public CreateNewTeamCommand(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    protected TeamBuilder createEntityBuilder() {
        return new TeamBuilder(repository);
    }
}
