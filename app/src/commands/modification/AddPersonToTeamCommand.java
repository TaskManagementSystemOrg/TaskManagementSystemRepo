package commands.modification;

import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class AddPersonToTeamCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public AddPersonToTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String personName = parameters.get(0);
        String teamName = parameters.get(1);
        taskManagementSystemRepository.findTeamByName(teamName).addMember(taskManagementSystemRepository.findPersonByName(personName));
        return String.format("Added %s to %s", personName, teamName);
    }
}
