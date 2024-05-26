package commands.navigation;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class ExitTeamCommand implements Command {
    private final TaskManagementSystemRepository repository;

    public ExitTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.repository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (repository.getCurrentTeam() == null) {
            return "Not in a team.";
        }
        repository.setCurrentTeam(null);
        repository.setCurrentBoard(null);
        return "Exited current team and board.";
    }
}
