package models;

import models.contracts.Person;
import models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person{
    private String name;
    private List<Task> tasks;

    public PersonImpl(String name) {
        setName(name);
        tasks = new ArrayList<>();
    }

    public void setName(String name) {
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
}