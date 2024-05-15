package commands.modification.task;

import Utils.ParsingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Story;
import models.enums.StoryStatus;

import java.util.List;

public class ChangeStoryStatus implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeStoryStatus(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String storyName = parameters.get(0);
        StoryStatus status = ParsingHelpers.tryParseEnum(parameters.get(1), StoryStatus.class, "Not a valid input.");
        Story story = (Story) taskManagementSystemRepository.findTaskByName(storyName);
        StoryStatus oldValue = story.getStatus();
        story.setStatus(status);
        return String.format("Priority changed from %s to %s", oldValue, status);
    }
}
