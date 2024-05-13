package models;

import Utils.ValidationHelpers;
import models.contracts.Person;
import models.contracts.Printable;
import models.contracts.Task;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_OUT_OF_BOUNDS_ERROR_MSG = "Name should be between 5 and 15 characters.";

    private String name;
    private List<Task> tasks;
    private List<String> activity;

    public PersonImpl(String name) {
        setName(name);
        tasks = new ArrayList<>();
        activity = new ArrayList<>();
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
        return new ArrayList<>(activity);
    }

    @Override
    public String getAsString() {
        return String.format("Name: %s", getName());
    }
}
