package commands.modification;

import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class AssignTaskToAPerson implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public AssignTaskToAPerson(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String task = parameters.get(0);
        String personName = parameters.get(1);
        taskManagementSystemRepository.findPersonByName(personName).addTask(taskManagementSystemRepository.findTaskByName(task));
        return String.format("Assigned %s to %s", task, personName);
    }
}
