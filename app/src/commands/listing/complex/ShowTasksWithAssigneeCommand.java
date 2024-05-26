package commands.listing.complex;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Bug;
import models.contracts.Feedback;
import models.contracts.Story;
import models.contracts.Task;
import models.enums.BugStatus;
import models.enums.FeedbackStatus;
import models.enums.StoryStatus;
import models.enums.TaskType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShowTasksWithAssigneeCommand implements Command {
    TaskManagementSystemRepository repository;

    public ShowTasksWithAssigneeCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute() {
        Scanner scanner = new Scanner(System.in);
        List<Feedback> feedbacks = new ArrayList<>();
        List<Bug> bugs = new ArrayList<>();
        List<Story> stories = new ArrayList<>();
        for (Task task : repository.getTasks()) {
            if (task.getType().equals(TaskType.FEEDBACK)){
                feedbacks.add((Feedback) task);
            } else if (task.getType().equals(TaskType.BUG)) {
                bugs.add((Bug) task);
            } else if (task.getType().equals(TaskType.STORY)) {
                stories.add((Story) task);
            }
        }



        boolean k = true;
        boolean l = true;

        System.out.println("Enter assignee to filter by, type help to see all options or enter an empty line: ");
        String input = scanner.nextLine();
        while (k){
            if (input.equalsIgnoreCase("")) {
            } else if (input.equalsIgnoreCase("help")){
                System.out.println(repository.getPeople());
                input = scanner.nextLine();
            } else if (repository.getPeople().contains(repository.findPersonByName(input))) {
                String finalInput = input;
                bugs = bugs.stream()
                        .filter(bug -> bug.getAssignee().equalsIgnoreCase(finalInput))
                        .collect(Collectors.toList());
                stories = stories.stream()
                        .filter(story -> story.getAssignee().equalsIgnoreCase(finalInput))
                        .collect(Collectors.toList());
                k = false;
            } else {
                System.out.println("Invalid input. Enter assignee to filter by, type help to see all options or enter an empty line: ");
                input = scanner.nextLine();
            }
        }

        System.out.println("Type Done to see only the done tasks, type Not_Done to see tasks that are not done or enter an empty line to see all tasks: ");
        input = scanner.nextLine();
        while (l) {
            if (input.equalsIgnoreCase("done")) {
                bugs = bugs.stream()
                        .filter(bug -> bug.getStatus().equals(BugStatus.DONE))
                        .collect(Collectors.toList());
                feedbacks = feedbacks.stream()
                        .filter(feedback -> feedback.getStatus().equals(FeedbackStatus.DONE))
                        .collect(Collectors.toList());
                stories = stories.stream()
                        .filter(story -> story.getStatus().equals(StoryStatus.DONE))
                        .collect(Collectors.toList());
                l = false;
            } else if (input.equalsIgnoreCase("not_done") || input.equalsIgnoreCase("not done")) {
                bugs = bugs.stream()
                        .filter(bug -> !bug.getStatus().equals(BugStatus.DONE))
                        .collect(Collectors.toList());
                feedbacks = feedbacks.stream()
                        .filter(feedback -> !feedback.getStatus().equals(FeedbackStatus.DONE))
                        .collect(Collectors.toList());
                stories = stories.stream()
                        .filter(story -> !story.getStatus().equals(StoryStatus.DONE))
                        .collect(Collectors.toList());
                l = false;
            } else if (input.equalsIgnoreCase("")) {
                l = false;
            } else {
                System.out.println("Invalid input. Type Done to see only the done tasks, type Not_Done to see tasks that are not done or enter an empty line to see all tasks: ");
                input = scanner.nextLine();
            }
        }

        List<Task> tasks = new ArrayList<>();
        tasks.addAll(bugs);
        tasks.addAll(stories);
        tasks.addAll(feedbacks);

        System.out.println("Type yes/no to decide whether to filter by title or not: ");
        input = scanner.nextLine();

        if (input.equalsIgnoreCase("yes")) {
            tasks.sort(Comparator.comparing(Task::getTitle, String::compareToIgnoreCase));
        }


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("====================\n");

        for (Task task : tasks) {
            stringBuilder.append(task.getType());
            stringBuilder.append(" ");
            stringBuilder.append(task.toString());
            stringBuilder.append("\n");
        }

        stringBuilder.append("====================");

        return stringBuilder.toString();




    }
}
