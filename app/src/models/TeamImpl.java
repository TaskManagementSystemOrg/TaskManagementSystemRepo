package models;

import models.contracts.Board;
import models.contracts.Person;
import models.contracts.Team;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    private String name;
    private final List<Person> members;
    private final List<Board> boards;

    public TeamImpl(String name) {
        setName(name);
        members = new ArrayList<>();
        boards = new ArrayList<>();
    }

    public void setName(String name) {
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
}
