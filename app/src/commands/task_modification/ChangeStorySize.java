package commands.task_modification;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class ChangeStorySize implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeStorySize(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }
}
