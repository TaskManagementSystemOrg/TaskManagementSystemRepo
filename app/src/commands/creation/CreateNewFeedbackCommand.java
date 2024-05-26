package commands.creation;

import core.contracts.TaskManagementSystemRepository;
import models.builders.FeedbackBuilder;
import models.contracts.Feedback;

public class CreateNewFeedbackCommand extends CreateNewTaskCommand {
    public CreateNewFeedbackCommand(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public String execute() {
        FeedbackBuilder builder = new FeedbackBuilder(repository);
        Feedback feedback = builder.build();
        return "Feedback created: " + feedback.getTitle() + " with ID: " + feedback.getId();
    }
}
