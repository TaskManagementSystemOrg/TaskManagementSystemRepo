package models;

import models.contracts.Board;
import models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private String name;
    private final List<Task> tasks;

    public BoardImpl(String name) {
        setName(name);
        tasks = new ArrayList<>();
    }

    private void setName(String name) {
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
