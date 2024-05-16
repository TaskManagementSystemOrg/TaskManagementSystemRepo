package models;

import Utils.FormattingHelpers;
import Utils.ValidationHelpers;
import models.contracts.Person;
import models.contracts.Printable;
import models.contracts.Task;

import javax.naming.Name;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_OUT_OF_BOUNDS_ERROR_MSG = "Name should be between 5 and 15 characters.";

    private String name;
    private List<Task> tasks;
    private List<String> activityHistory;

    public PersonImpl(String name) {
        setName(name);
        tasks = new ArrayList<>();
        activityHistory = new ArrayList<>();
        activityHistory.add(String.format("Person %s created on %s", name, LocalDateTime.now().format(FormattingHelpers.formatter)));
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
    public String toString() {
        return String.format("Name: %s", getName());
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        activityHistory.add(String.format("Added task %s to %s on %s", task.getTitle(), getName(), LocalDateTime.now().format(FormattingHelpers.formatter)));
    }
    @Override
    public void removeTask(Task task) {
        tasks.remove(task);
        activityHistory.add(String.format("Removed task %s from %s on %s", task.getTitle(), getName(), LocalDateTime.now().format(FormattingHelpers.formatter)));
    }
    @Override
    public String getClassName() {
        return this.getClass().getName();
    }
}
