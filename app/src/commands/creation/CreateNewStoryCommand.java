package commands.creation;

import core.contracts.TaskManagementSystemRepository;

public class CreateNewStoryCommand extends CreateNewTaskCommand{
    public CreateNewStoryCommand(TaskManagementSystemRepository taskManagementSystemRepository){super(taskManagementSystemRepository);}
}
