package commands.task_modification;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class ChangeBugStatus implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeBugStatus(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return "";
    }
}
