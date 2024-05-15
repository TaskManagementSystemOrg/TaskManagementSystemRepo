package commands.creation;

import core.contracts.TaskManagementSystemRepository;
import models.builders.BugBuilder;
import models.contracts.Bug;

import java.util.List;

public class CreateNewBugCommand extends CreateNewTaskCommand{

    public CreateNewBugCommand(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public String execute(List<String> params) {
        BugBuilder builder = new BugBuilder(repository);
        Bug bug = builder.build();
        if (bug == null) {
            return "Bug not created. Create a team and board, and person first.";
        }
        return "Bug created: " + bug.getTitle() + " with ID: " + bug.getId();
    }
}
