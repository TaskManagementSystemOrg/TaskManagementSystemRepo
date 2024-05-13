package commands.listing;

import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class ShowTeamsActivityCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowTeamsActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        StringBuilder stringBuilder = new StringBuilder();
        String name = parameters.get(0);
        List<String> activity = taskManagementSystemRepository.findTeamByName(name).getActivity();
        for (String activity1 : activity) {
            stringBuilder.append(activity1);
        }
        return stringBuilder.toString();
    }
}
