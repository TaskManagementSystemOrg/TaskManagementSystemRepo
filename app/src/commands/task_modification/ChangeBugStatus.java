package commands.task_modification;

import Utils.ParsingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Bug;
import models.enums.BugStatus;
import models.enums.Priority;
import models.enums.Severity;

import java.util.List;

public class ChangeBugStatus implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeBugStatus(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String bugName = parameters.get(0);
        BugStatus status = ParsingHelpers.tryParseEnum(parameters.get(1), BugStatus.class, "Not a valid input.");
        Bug bug = (Bug) taskManagementSystemRepository.findTaskByName(bugName);
        BugStatus oldValue = bug.getStatus();
        bug.setStatus(status);
        return String.format("Status changed from %s to %s", oldValue, status);
    }
}
