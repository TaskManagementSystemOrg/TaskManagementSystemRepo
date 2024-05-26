package commands.modification.task;

import Utils.ListingHelpers;
import Utils.ParsingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Feedback;
import models.contracts.Story;
import models.contracts.Task;
import models.enums.FeedbackStatus;
import models.enums.StoryStatus;
import models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeStoryStatus implements Command {
    protected final TaskManagementSystemRepository repository;

    public ChangeStoryStatus(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.repository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        Scanner scanner = new Scanner(System.in);
        if (repository.getTasks().stream()
                .noneMatch(task -> task.getType().equals(TaskType.STORY))) {
            return "No feedback created yet.";
        }
        List<Story> stories = new ArrayList<>();
        for (Task task : repository.getTasks()) {
            if (task.getType().equals(TaskType.STORY)) {
                stories.add((Story) task);
            }
        }


        Story story = null;
        System.out.println("Type help to see all options or enter a task: ");
        String input = scanner.nextLine();

        while (true) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println("====================");
                System.out.println(ListingHelpers.elementsToString(stories));
                System.out.println("====================");
                System.out.println("Type help to see all options or enter task: ");
                input = scanner.nextLine();
            } else if (stories.contains(repository.findTaskByName(input))) {
                story = (Story) repository.findTaskByName(input);
                break;
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }
        }

        System.out.print("Enter new status(NOT_DONE/IN_PROGRESS/DONE): ");
        input = scanner.nextLine();

        while (true) {
            if (input.equalsIgnoreCase("NOT_DONE") || input.equalsIgnoreCase("IN_PROGRESS") || input.equalsIgnoreCase("DONE")) {
                story.setStatus(ParsingHelpers.tryParseEnum(input, StoryStatus.class, "Not a valid enum."));
                return String.format("%s status set to %s.", story.getTitle(), story.getStatus());
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }
        }
    }
}
