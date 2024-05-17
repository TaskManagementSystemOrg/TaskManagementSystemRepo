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
        repository.setCurrentTeam(null);
        return "Exited current team.";
    }
}
