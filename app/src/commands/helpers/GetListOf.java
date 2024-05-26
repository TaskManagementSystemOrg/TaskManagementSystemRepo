package commands.helpers;

import core.contracts.TaskManagementSystemRepository;
import models.contracts.Bug;
import models.contracts.Feedback;
import models.contracts.Story;
import models.contracts.Task;
import models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;

public class GetListOf {

    public static List<Bug> bugs(TaskManagementSystemRepository repository) {
        List<Bug> bugs = new ArrayList<>();
        for (Task task : repository.getTasks()) {
            if (task.getType().equals(TaskType.BUG)) {
                bugs.add((Bug) task);
            }
        }
        return bugs;
    }

    public static List<Feedback> feedbacks(TaskManagementSystemRepository repository) {
        List<Feedback> feedbacks = new ArrayList<>();
        for (Task task : repository.getTasks()) {
            if (task.getType().equals(TaskType.FEEDBACK)) {
                feedbacks.add((Feedback) task);
            }
        }
        return feedbacks;
    }

    public static List<Story> stories(TaskManagementSystemRepository repository) {
        List<Story> stories = new ArrayList<>();
        for (Task task : repository.getTasks()) {
            if (task.getType().equals(TaskType.STORY)) {
                stories.add((Story) task);
            }
        }
        return stories;
    }
}

