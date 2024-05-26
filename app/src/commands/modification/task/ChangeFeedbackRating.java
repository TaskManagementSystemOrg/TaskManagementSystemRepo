package commands.modification.task;

import Utils.ListingHelpers;
import Utils.ParsingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Feedback;
import models.contracts.Task;
import models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeFeedbackRating implements Command {

    protected final TaskManagementSystemRepository repository;

    public ChangeFeedbackRating(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.repository = taskManagementSystemRepository;
    }

    @Override
    public String execute() {
        Scanner scanner = new Scanner(System.in);
        if (repository.getTasks().stream()
                .noneMatch(task -> task.getType().equals(TaskType.FEEDBACK))) {
            return "No feedback created yet.";
        }
        List<Feedback> feedbacks = new ArrayList<>();
        for (Task task : repository.getTasks()) {
            if (task.getType().equals(TaskType.FEEDBACK)) {
                feedbacks.add((Feedback) task);
            }
        }


        Feedback feedback = null;
        System.out.println("Type help to see all options or enter a task: ");
        String input = scanner.nextLine();

        while (true) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println("====================");
                System.out.println(ListingHelpers.elementsToString(feedbacks));
                System.out.println("====================");
                System.out.println("Type help to see all options or enter task: ");
                input = scanner.nextLine();
            } else if (feedbacks.contains(repository.findTaskByName(input))) {
                feedback = (Feedback) repository.findTaskByName(input);
                break;
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }
        }

        System.out.print("Enter new rating(0-10): ");
        input = scanner.nextLine();

        while (true) {
            if (ParsingHelpers.tryParseInteger(input, "rating") > 0 || ParsingHelpers.tryParseInteger(input, "rating") < 10) {
                feedback.setRating(ParsingHelpers.tryParseInteger(input, "rating"));
                return String.format("%s rating set to %d.", feedback.getTitle(), feedback.getRating());
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }
        }

    }
}
