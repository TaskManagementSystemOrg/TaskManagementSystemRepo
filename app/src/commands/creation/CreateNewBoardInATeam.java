package commands.creation;

import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Team;

import java.util.List;

public class CreateNewBoardInATeam implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String TEAM_CREATED_MESSAGE = "Board %s was created.";


    public CreateNewBoardInATeam(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        taskManagementSystemRepository.createBoardInTeam(name,"");
        return String.format(TEAM_CREATED_MESSAGE,name);

    }
}
