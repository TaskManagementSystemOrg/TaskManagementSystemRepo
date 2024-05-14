package commands.creation;

import core.contracts.TaskManagementSystemRepository;
import models.builders.StoryBuilder;
import models.contracts.Story;

import java.util.List;

public class CreateNewStoryCommand extends CreateNewTaskCommand {
    public CreateNewStoryCommand(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public String execute(List<String> params) {
        StoryBuilder builder = new StoryBuilder(repository);
        Story story = builder.build();
        return "Story created: " + story.getTitle() + " with ID: " + story.getId();
    }
}
