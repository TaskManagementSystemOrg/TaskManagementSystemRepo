package commands.listing;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class ShowAllTeamBoardsCommand implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowAllTeamBoardsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return "";
    }
}
