package commands.modification.person;

import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class UnassignTaskToAPerson implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public UnassignTaskToAPerson(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String task = parameters.get(0);
        String personName = parameters.get(1);
        taskManagementSystemRepository.findPersonByName(personName).removeTask(taskManagementSystemRepository.findTaskByName(task));
        return String.format("Unassigned %s from %s", task, personName);
    }
}