package models;

import Utils.FormattingHelpers;
import Utils.ValidationHelpers;
import models.contracts.Board;
import models.contracts.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 10;
    public static final String NAME_OUT_OF_BOUNDS_ERROR_MSG = "Name should be between 5 and 10 characters.";
    private String name;
    private final List<Task> tasks;
    private final List<String> activityHistory;

    public BoardImpl(String name) {
        setName(name);
        tasks = new ArrayList<>();
        activityHistory = new ArrayList<>();
        activityHistory.add(String.format("Board %s created on %s", name, LocalDateTime.now().format(FormattingHelpers.formatter)));
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
    public List<String> getActivity() {
        return new ArrayList<>(activityHistory);
    }
    @Override
    public void addTask(Task task) {
        tasks.add(task);
        activityHistory.add(String.format("Added task %s on %s", task.getTitle(), LocalDateTime.now().format(FormattingHelpers.formatter)));
    }

    @Override
    public String toString() {
        return String.format("Name: %s", getName());
    }
}
