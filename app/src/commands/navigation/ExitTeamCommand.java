package commands.navigation;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

public class ExitTeamCommand implements Command {
    private final TaskManagementSystemRepository repository;

    public ExitTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.repository = taskManagementSystemRepository;
    }

    @Override
    public String execute() {
        if (repository.getCurrentTeam() == null) {
            return "Not in a team.";
        }
        repository.setCurrentTeam(null);
        repository.setCurrentBoard(null);
        return "Exited current team and board.";
    }
}
