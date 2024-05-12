package commands.creation;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class CreateNewBoardInATeam implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;
    public CreateNewBoardInATeam(TaskManagementSystemRepository taskManagementSystemRepository) {this.taskManagementSystemRepository = taskManagementSystemRepository;}

    @Override
    public String execute(List<String> parameters) {
        return "";
    }
}
