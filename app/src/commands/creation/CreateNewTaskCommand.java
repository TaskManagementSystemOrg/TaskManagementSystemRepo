package commands.creation;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public abstract class CreateNewTaskCommand implements Command {

    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public CreateNewTaskCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return "";
    }
}
