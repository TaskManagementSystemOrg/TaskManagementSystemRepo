package commands.listing;

import Utils.ListingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Board;

import java.util.List;

public class ShowAllTeamBoardsCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowAllTeamBoardsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        List<Board> boards = taskManagementSystemRepository.getBoards(taskManagementSystemRepository.findTeamByName(name));
        if (boards.isEmpty()) {
            return "There are no boards in this team.";
        }
        return ListingHelpers.elementsToString(boards);
    }
}
