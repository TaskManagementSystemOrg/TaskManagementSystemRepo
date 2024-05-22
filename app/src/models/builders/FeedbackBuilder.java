package models.builders;

import core.contracts.TaskManagementSystemRepository;
import models.contracts.Board;
import models.contracts.Feedback;
import models.contracts.Person;
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

    @Override
    protected void collectCommonAttributes() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter task title (10-100 characters): ");
        title = scanner.nextLine();
        while (!isValidTitle(title)) {
            System.out.print("Invalid title. Enter task title (10-100 characters): ");
            title = scanner.nextLine();
        }

        System.out.print("Enter task description (10-500 characters): ");
        description = scanner.nextLine();
        while (!isValidDescription(description)) {
            System.out.print("Invalid description. Enter task description (10-500 characters): ");
            description = scanner.nextLine();
        }

        if (repository.getCurrentBoard() != null) {
            board = repository.getCurrentBoard();
        } else {
            System.out.print("Type help to see all boards or enter board name: ");
            String boardName = scanner.nextLine();
            if (boardName.equalsIgnoreCase("help")) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Board board1 : repository.getBoards()) {
                    stringBuilder.append(board1);
                }
                System.out.println(stringBuilder);
                System.out.println("Enter board name: ");
                boardName = scanner.nextLine();
            }
            board = repository.findBoardByName(boardName);
            while (board == null) {
                System.out.print("Board not found. Enter board name: ");
                boardName = scanner.nextLine();
                board = repository.findBoardByName(boardName);
            }
        }
    }
}
