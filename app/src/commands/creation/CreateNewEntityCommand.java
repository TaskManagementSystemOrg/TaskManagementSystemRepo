package commands.creation;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.builders.EntityBuilder;

import java.util.List;

public abstract class CreateNewEntityCommand<T> implements Command {
    protected final TaskManagementSystemRepository repository;

    public CreateNewEntityCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> params) {
        EntityBuilder<T> builder = createEntityBuilder();
        T entity = builder.build();
        return String.format("%s created: %s", entity.getClass().getSimpleName());
    }

    protected abstract EntityBuilder<T> createEntityBuilder();
}
