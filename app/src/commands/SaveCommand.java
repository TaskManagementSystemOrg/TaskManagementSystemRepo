package commands;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class SaveCommand implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;
    public SaveCommand(TaskManagementSystemRepository taskManagementSystemRepository)
    {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }
    @Override
    public String execute(List<String> parameters) {
        taskManagementSystemRepository.savePeopleToJson();
        return "Persons saved to JSON successfully!";
    }
}
