package models;

import Utils.FormattingHelpers;
import Utils.ValidationHelpers;
import models.contracts.Board;
import models.contracts.Person;
import models.contracts.Team;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_OUT_OF_BOUNDS_ERROR_MSG = "Name should be between 5 and 15 characters.";
    private String name;
    private final List<String> members;
    private final List<String> boards;
    private final List<String> activityHistory;

    public TeamImpl(String name) {
        setName(name);
        members = new ArrayList<>();
        boards = new ArrayList<>();
        activityHistory = new ArrayList<>();
        activityHistory.add(String.format("Team %s created on %s", name, LocalDateTime.now().format(FormattingHelpers.formatter)));
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_OUT_OF_BOUNDS_ERROR_MSG);
        this.name = name;
    }

    @Override
    public List<String> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getBoards() {
        return new ArrayList<>(boards);
    }
    @Override
    public List<String> getActivity() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public void addMember(String memberToAdd) {
        members.add(memberToAdd);
        //activityHistory.add(String.format("Member %s added to %s on %s", memberToAdd, LocalDateTime.now().format(FormattingHelpers.formatter)));
    }

    @Override
    public void removeMember(String memberToRemove) {
        members.remove(memberToRemove);
        activityHistory.add(String.format("Member %s removed from %s on %s", memberToRemove, getName(), LocalDateTime.now().format(FormattingHelpers.formatter)));
    }

    @Override
    public void addBoard(String boardToAdd) {
        boards.add(boardToAdd);
        //activityHistory.add(String.format("Board %s added to %s on %s", boardToAdd, getName(), LocalDateTime.now().format(FormattingHelpers.formatter)));
    }

    @Override
    public void removeBoard(String boardToRemove) {
        boards.remove(boardToRemove);
        activityHistory.add(String.format("Board %s removed from %s on %s", boardToRemove, getName(), LocalDateTime.now().format(FormattingHelpers.formatter)));
    }

    @Override
    public String toString() {
        return String.format("Team name: %s", getName());
    }
}
