package commands.listing;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class ShowTeamsActivityCommand implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;
    public ShowTeamsActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {this.taskManagementSystemRepository = taskManagementSystemRepository;}

    @Override
    public String execute(List<String> parameters) {
        return "";
    }
}
