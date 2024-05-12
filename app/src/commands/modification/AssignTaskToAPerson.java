package commands.modification;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class AssignTaskToAPerson implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;
    public AssignTaskToAPerson(TaskManagementSystemRepository taskManagementSystemRepository) {this.taskManagementSystemRepository = taskManagementSystemRepository;}

    @Override
    public String execute(List<String> parameters) {
        return "";
    }
}
