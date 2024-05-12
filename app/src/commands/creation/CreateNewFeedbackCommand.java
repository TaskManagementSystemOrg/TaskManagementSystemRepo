package commands.creation;

import core.contracts.TaskManagementSystemRepository;

public class CreateNewFeedbackCommand extends CreateNewTaskCommand{
    public CreateNewFeedbackCommand(TaskManagementSystemRepository taskManagementSystemRepository){super(taskManagementSystemRepository);}
}
