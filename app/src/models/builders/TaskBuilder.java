package models.builders;

import Utils.ValidationHelpers;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Board;
import models.contracts.Person;

import java.util.Scanner;

public abstract class TaskBuilder<T> {
    protected final TaskManagementSystemRepository repository;
    protected String title;
    protected String description;
    protected Person assignee;
    protected Board board;

    public TaskBuilder(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }

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

        System.out.print("Enter assignee name: ");
        String assigneeName = scanner.nextLine();
        assignee = repository.findPersonByName(assigneeName);
        while (assignee == null) {
            System.out.print("Assignee not found. Enter assignee name: ");
            assigneeName = scanner.nextLine();
            assignee = repository.findPersonByName(assigneeName);
        }

        System.out.print("Enter board name: ");
        String boardName = scanner.nextLine();
        board = repository.findBoardByName(boardName);
        while (board == null) {
            System.out.print("Board not found. Enter board name: ");
            boardName = scanner.nextLine();
            board = repository.findBoardByName(boardName);
        }
    }

    protected boolean isValidTitle(String title) {
        try {
            ValidationHelpers.validateStringLength(title, 10, 100, "Title length must be between 10 and 100 characters.");
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    protected boolean isValidDescription(String description) {
        try {
            ValidationHelpers.validateStringLength(description, 10, 500, "Description length must be between 10 and 500 characters.");
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public abstract T build();
}