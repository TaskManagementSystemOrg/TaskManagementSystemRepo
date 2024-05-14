package models.builders;

import core.contracts.TaskManagementSystemRepository;
import Utils.ValidationHelpers;

import java.util.Scanner;

public abstract class EntityBuilder<T> {
    protected final TaskManagementSystemRepository repository;
    protected String name;

    public EntityBuilder(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }

    protected void collectCommonAttributes() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name (5-15 characters): ");
        name = scanner.nextLine();
        while (!isValidName(name)) {
            System.out.print("Invalid name. Enter name (5-15 characters): ");
            name = scanner.nextLine();
        }
    }

    protected boolean isValidName(String name) {
        try {
            ValidationHelpers.validateStringLength(name, 5, 15, "Name should be between 5 and 15 characters.");
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public abstract T build();
}
