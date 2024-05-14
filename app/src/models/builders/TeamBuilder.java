package models.builders;

import core.contracts.TaskManagementSystemRepository;
import models.TeamImpl;
import models.contracts.Team;

public class TeamBuilder extends EntityBuilder<Team> {

    public TeamBuilder(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public Team build() {
        collectCommonAttributes();
        return new TeamImpl(name);
    }
}
