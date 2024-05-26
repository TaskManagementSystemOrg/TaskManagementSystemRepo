package commands.navigation;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

public class ExitBoardCommand implements Command {
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ExitBoardCommand(TaskManagementSystemRepository repository) {
        this.taskManagementSystemRepository = repository;
    }

    @Override
    public String execute() {
        if (taskManagementSystemRepository.getCurrentBoard() == null) {
            return "Not in a board.";
        }
        taskManagementSystemRepository.setCurrentBoard(null);
        return "Exited current board.";
    }
}
