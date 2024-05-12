package commands.task_modification;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class ChangeStoryPriority implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeStoryPriority(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return "";
    }
}
