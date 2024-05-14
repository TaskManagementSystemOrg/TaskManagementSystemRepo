package models;

import Utils.ValidationHelpers;
import models.contracts.Board;
import models.contracts.Person;
import models.contracts.Team;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_OUT_OF_BOUNDS_ERROR_MSG = "Name should be between 5 and 15 characters.";
    private String name;
    private final List<Person> members;
    private final List<Board> boards;
    private final List<String> activity;

    public TeamImpl(String name) {
        setName(name);
        members = new ArrayList<>();
        boards = new ArrayList<>();
        activity = new ArrayList<>();
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_OUT_OF_BOUNDS_ERROR_MSG);
        this.name = name;
    }

    @Override
    public List<Person> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }
    @Override
    public List<String> getActivity() {
        return new ArrayList<>(activity);
    }

    @Override
    public void addMember(Person memberToAdd) {
        members.add(memberToAdd);
    }

    @Override
    public void removeMember(Person memberToRemove) {
        members.remove(memberToRemove);
    }

    @Override
    public void addBoard(Board boardToAdd) {
        boards.add(boardToAdd);
    }

    @Override
    public void removeBoard(Board boardToRemove) {
        boards.remove(boardToRemove);
    }

    @Override
    public String toString() {
        return String.format("Team name: %s", getName());
    }
}
