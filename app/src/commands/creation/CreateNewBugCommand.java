package commands.creation;

import core.contracts.TaskManagementSystemRepository;

public class CreateNewBugCommand extends CreateNewTaskCommand{
    public CreateNewBugCommand(TaskManagementSystemRepository taskManagementSystemRepository){super(taskManagementSystemRepository);}
}
