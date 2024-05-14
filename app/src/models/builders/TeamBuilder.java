package models.builders;

import core.contracts.TaskManagementSystemRepository;
import models.contracts.Team;
import models.TeamImpl;

public class TeamBuilder extends EntityBuilder<Team> {
    public TeamBuilder(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public Team build() {
        collectCommonAttributes();
        return repository.createTeam(name);
    }
}
