package commands.creation;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

public abstract class CreateNewTaskCommand implements Command {

    protected final TaskManagementSystemRepository repository;

    public CreateNewTaskCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.repository = taskManagementSystemRepository;
    }

    @Override
    public String execute() {
        return "";
    }
}
