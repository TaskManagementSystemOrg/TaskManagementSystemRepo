package commands.navigation;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class ExitBoardCommand implements Command {
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ExitBoardCommand(TaskManagementSystemRepository repository) {
        this.taskManagementSystemRepository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        taskManagementSystemRepository.setCurrentBoard(null);
        return "Exited board.";
    }
}
