package models.builders;

import core.contracts.TaskManagementSystemRepository;
import models.contracts.Feedback;
import models.enums.FeedbackStatus;

import java.util.Scanner;

public class FeedbackBuilder extends TaskBuilder<Feedback> {
    private int rating;
    private FeedbackStatus status;

    public FeedbackBuilder(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public Feedback build() {
        collectCommonAttributes();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter rating (1-10): ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid rating. Enter rating (1-10): ");
            scanner.next();
        }
        rating = scanner.nextInt();
        while (rating < 1 || rating > 10) {
            System.out.print("Invalid rating. Enter rating (1-10): ");
            rating = scanner.nextInt();
        }
        scanner.nextLine();  // Consume newline

        System.out.print("Enter status (NEW, UNSCHEDULED, SCHEDULED, DONE): ");
        String statusInput = scanner.nextLine().toUpperCase();
        while (!isValidFeedbackStatus(statusInput)) {
            System.out.print("Invalid status. Enter status (NEW, UNSCHEDULED, SCHEDULED, DONE): ");
            statusInput = scanner.nextLine().toUpperCase();
        }
        status = FeedbackStatus.valueOf(statusInput);

        return repository.createFeedback(title, description, rating, status, board);
    }

    private boolean isValidFeedbackStatus(String status) {
        try {
            FeedbackStatus.valueOf(status);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
