package commands.listing;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class ShowBoardActivityCommand implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;
    public ShowBoardActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {this.taskManagementSystemRepository = taskManagementSystemRepository;}

    @Override
    public String execute(List<String> parameters) {
        return "";
    }
}
