package commands.task_modification;

import Utils.ParsingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Story;
import models.enums.Priority;
import models.enums.Size;

import java.util.List;

public class ChangeStorySize implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeStorySize(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String storyName = parameters.get(0);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(1), Size.class, "Not a valid input.");
        Story story = (Story) taskManagementSystemRepository.findTaskByName(storyName);
        Size oldValue = story.getSize();
        story.setSize(size);
        return String.format("Size changed from %s to %s", oldValue, size);
    }
}
