package commands.creation;

import core.contracts.TaskManagementSystemRepository;
import models.builders.FeedbackBuilder;
import models.contracts.Feedback;

import java.util.List;

public class CreateNewFeedbackCommand extends CreateNewTaskCommand {
    public CreateNewFeedbackCommand(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public String execute(List<String> params) {
        FeedbackBuilder builder = new FeedbackBuilder(repository);
        Feedback feedback = builder.build();
        return "Feedback created: " + feedback.getTitle() + " with ID: " + feedback.getId();
    }
}
