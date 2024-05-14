package models.builders;

import core.contracts.TaskManagementSystemRepository;
import models.contracts.Story;
import models.enums.Priority;
import models.enums.Size;
import models.enums.StoryStatus;

import java.util.Scanner;

public class StoryBuilder extends TaskBuilder<Story> {
    private Priority priority;
    private Size size;
    private StoryStatus status;

    public StoryBuilder(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public Story build() {
        collectCommonAttributes();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
        String priorityInput = scanner.nextLine().toUpperCase();
        while (!isValidPriority(priorityInput)) {
            System.out.print("Invalid priority. Enter priority (HIGH, MEDIUM, LOW): ");
            priorityInput = scanner.nextLine().toUpperCase();
        }
        priority = Priority.valueOf(priorityInput);

        System.out.print("Enter size (LARGE, MEDIUM, SMALL): ");
        String sizeInput = scanner.nextLine().toUpperCase();
        while (!isValidSize(sizeInput)) {
            System.out.print("Invalid size. Enter size (LARGE, MEDIUM, SMALL): ");
            sizeInput = scanner.nextLine().toUpperCase();
        }
        size = Size.valueOf(sizeInput);

        System.out.print("Enter status (NOT_DONE, IN_PROGRESS, DONE): ");
        String statusInput = scanner.nextLine().toUpperCase();
        while (!isValidStoryStatus(statusInput)) {
            System.out.print("Invalid status. Enter status (NOT_DONE, IN_PROGRESS, DONE): ");
            statusInput = scanner.nextLine().toUpperCase();
        }
        status = StoryStatus.valueOf(statusInput);

        return repository.createStory(title, description, priority, size, status, assignee, board);
    }

    private boolean isValidPriority(String priority) {
        try {
            Priority.valueOf(priority);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isValidSize(String size) {
        try {
            Size.valueOf(size);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isValidStoryStatus(String status) {
        try {
            StoryStatus.valueOf(status);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
