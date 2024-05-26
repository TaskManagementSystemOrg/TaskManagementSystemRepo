package commands;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

public class SaveCommand implements Command {
    protected final TaskManagementSystemRepository taskManagementSystemRepository;
    public SaveCommand(TaskManagementSystemRepository taskManagementSystemRepository)
    {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }
    @Override
    public String execute() {
        taskManagementSystemRepository.savePeopleToJson();
        taskManagementSystemRepository.saveTeamsToJSon();
        taskManagementSystemRepository.saveTasksToJson();
        taskManagementSystemRepository.saveBoardsToJson();
        return "saved to JSON successfully!";
    }
}
