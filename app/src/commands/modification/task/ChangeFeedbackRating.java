package commands.modification.task;

import Utils.ParsingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Feedback;

import java.util.List;

public class ChangeFeedbackRating implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeFeedbackRating(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String feedbackName = parameters.get(0);
        int rating = ParsingHelpers.tryParseInteger(parameters.get(1), "rating");
        Feedback feedback = (Feedback) taskManagementSystemRepository.findTaskByName(feedbackName);
        int oldValue = feedback.getRating();
        feedback.setRating(rating);
        return String.format("Rating changed from %s to %s", oldValue, rating);
    }
}
