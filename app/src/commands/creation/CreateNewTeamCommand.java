package commands.creation;

import core.contracts.TaskManagementSystemRepository;
import models.builders.TeamBuilder;
import models.contracts.Team;

public class CreateNewTeamCommand extends CreateNewEntityCommand<Team> {
    public CreateNewTeamCommand(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    protected TeamBuilder createEntityBuilder() {
        return new TeamBuilder(repository);
    }
}
