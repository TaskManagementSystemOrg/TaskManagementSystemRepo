package models;

import Utils.ValidationHelpers;
import models.contracts.Board;
import models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 10;
    public static final String NAME_OUT_OF_BOUNDS_ERROR_MSG = "Name should be between 5 and 10 characters.";
    private String name;
    private final List<Task> tasks;

    public BoardImpl(String name) {
        setName(name);
        tasks = new ArrayList<>();
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_OUT_OF_BOUNDS_ERROR_MSG);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String getAsString() {
        return String.format("Name: %s", getName());
    }
}
