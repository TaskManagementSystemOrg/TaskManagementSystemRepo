package commands.creation;

import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class CreateNewTeamCommand implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String TEAM_CREATED_MESSAGE = "Team %s was created.";

    public CreateNewTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        taskManagementSystemRepository.createTeam(parameters.get(0));
        return String.format(TEAM_CREATED_MESSAGE,name);

    }
}
