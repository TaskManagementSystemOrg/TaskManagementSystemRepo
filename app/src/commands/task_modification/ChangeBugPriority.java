package commands.task_modification;

import Utils.ParsingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.BugImpl;
import models.contracts.Bug;
import models.contracts.Task;
import models.enums.Priority;

import java.util.List;

public class ChangeBugPriority implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeBugPriority(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String bugName = parameters.get(0);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class, "Not a valid input.");
        Bug bug = (Bug) taskManagementSystemRepository.findTaskByName(bugName);
        Priority oldPriority = bug.getPriority();
        bug.setPriority(priority);
        return String.format("Priority changed from %s to %s", oldPriority, priority);
    }
}
