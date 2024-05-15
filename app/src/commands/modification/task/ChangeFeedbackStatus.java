package commands.modification.task;

import Utils.ParsingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Feedback;
import models.enums.FeedbackStatus;

import java.util.List;

public class ChangeFeedbackStatus implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeFeedbackStatus(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String feedbackName = parameters.get(0);
        FeedbackStatus status = ParsingHelpers.tryParseEnum(parameters.get(1), FeedbackStatus.class, "Not a valid input.");
        Feedback feedback = (Feedback) taskManagementSystemRepository.findTaskByName(feedbackName);
        FeedbackStatus oldValue = feedback.getStatus();
        feedback.setStatus(status);
        return String.format("Status changed from %s to %s", oldValue, status);
    }
}
