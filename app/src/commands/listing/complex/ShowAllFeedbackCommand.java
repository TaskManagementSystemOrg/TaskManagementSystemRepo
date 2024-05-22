package commands.listing.complex;

import Utils.ParsingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Bug;
import models.contracts.Feedback;
import models.contracts.Task;
import models.enums.BugStatus;
import models.enums.FeedbackStatus;
import models.enums.TaskType;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShowAllFeedbackCommand implements Command {
    TaskManagementSystemRepository repository;

    public ShowAllFeedbackCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        Scanner scanner = new Scanner(System.in);
        String input;
        List<Feedback> feedbacks = new ArrayList<>();
        for (Task task : repository.getTasks()) {
            if (task.getType().equals(TaskType.FEEDBACK)) {
                feedbacks.add((Feedback) task);
            }
        }

        System.out.println("Filter by status (New/Unscheduled/Scheduled/Done) or enter empty line: ");
        input = scanner.nextLine();

        if (input.equalsIgnoreCase("")) {
        } else if (input.equalsIgnoreCase("new") || input.equalsIgnoreCase("unscheduled")
                || input.equalsIgnoreCase("scheduled") || input.equalsIgnoreCase("done")) {
            String finalInput = input;
            feedbacks = feedbacks.stream()
                    .filter(feedback -> feedback.getStatus().equals(ParsingHelpers.tryParseEnum(finalInput, FeedbackStatus.class, "Not an enum.")))
                    .collect(Collectors.toList());
        } else {
            System.out.println("Not a valid filter. Will not filter.");
        }



        //title rating
        System.out.println("Enter sorting criteria (Title/Rating) or enter an empty line: ");
        input = scanner.nextLine();
        int sortType = 0;
        if (input.equalsIgnoreCase("")) {
        } else if (input.equalsIgnoreCase("title")) {
            feedbacks.sort(Comparator.comparing(Feedback::getTitle, String::compareToIgnoreCase));
            sortType = 1;
        } else if (input.equalsIgnoreCase("rating")) {
            feedbacks.sort(Comparator.comparing(Feedback::getRating, Integer::compareTo).reversed());
            sortType = 2;
        } else {
            System.out.println("Not valid criteria. Will not sort.");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("====================\n");
        if (feedbacks.isEmpty()) {
            stringBuilder.append("No feedback fits criteria.\n");

        } else {
            for (Feedback feedback : feedbacks) {
                stringBuilder.append(feedback.toString());
                switch (sortType) {
                    case 0, 1:
                        break;
                    case 2:
                        stringBuilder.append(String.format(" Rating: %d", feedback.getRating()));
                        break;
                }
                stringBuilder.append("\n");
            }
        }
        stringBuilder.append("====================");
        sortType = 0;
        return stringBuilder.toString();
    }
}
