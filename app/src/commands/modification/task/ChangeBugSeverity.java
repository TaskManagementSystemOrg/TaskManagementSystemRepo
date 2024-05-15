package commands.modification.task;

import Utils.ParsingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Bug;
import models.enums.Severity;

import java.util.List;

public class ChangeBugSeverity implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeBugSeverity(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String bugName = parameters.get(0);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(1), Severity.class, "Not a valid input.");
        Bug bug = (Bug) taskManagementSystemRepository.findTaskByName(bugName);
        Severity oldValue = bug.getSeverity();
        bug.setSeverity(severity);
        return String.format("Severity changed from %s to %s", oldValue, severity);
    }
}
