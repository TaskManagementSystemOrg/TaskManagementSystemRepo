package commands.task_modification;

import Utils.ParsingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Story;
import models.enums.Priority;

import java.util.List;

public class ChangeStoryPriority implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeStoryPriority(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String storyName = parameters.get(0);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class, "Not a valid input.");
        Story story = (Story) taskManagementSystemRepository.findTaskByName(storyName);
        Priority oldValue = story.getPriority();
        story.setPriority(priority);
        return String.format("Priority changed from %s to %s", oldValue, priority);
    }
}
