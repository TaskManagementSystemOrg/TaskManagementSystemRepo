package core;

import core.contracts.TaskManagementSystemRepository;
import models.*;
import models.contracts.*;
import models.enums.*;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {

    private static int nextId;

    private final List<Task> tasks;
    private final List<Team> teams;
    private final List<Person> people;
    private static final String DATA_FILE = "data.json";


    public TaskManagementSystemRepositoryImpl() {
        this.tasks = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.people = new ArrayList<>();
        nextId = 0;
    }

    @Override
    public List<Person> getPeople() {
        return new ArrayList<>(people);
    }
    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }
    @Override
    public List<Person> getMembers(Team team) {
        return team.getMembers();
    }

    @Override
    public List<Board> getBoards(Team team) {
        return team.getBoards();
    }

    @Override
    public List<Board> getBoards() {
        List<Board> boards = new ArrayList<>();
        for (Team team : getTeams()) {
            boards.addAll(getBoards(team));
        }
        return boards;
    }

    @Override
    public List<String> getActivity(Person person) {
        return null;
    }

    @Override
    public List<String> getActivity(Team team) {
        return null;
    }

    @Override
    public List<String> getActivity(Board board) {
        return null;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Task> getTasks(Team team) {
        List<Task> tasks = new ArrayList<>();
        for (Board board : team.getBoards()) {
            tasks.addAll(board.getTasks());
        }
        return tasks;
    }

    @Override
    public List<Task> getTasks(Board board) {
        return new ArrayList<>(board.getTasks());
    }

    @Override
    public Task findTaskByName(String name) {
        for (Task task : getTasks()) {
            if (task.getTitle().equalsIgnoreCase(name)) {
                return task;
            }
        }
        return null;
    }

    @Override
    public Person findPersonByName(String name) {
        for (Person person : getPeople()) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Team findTeamByName(String name) {
        for (Team team : getTeams()) {
            if (team.getName().equalsIgnoreCase(name)) {
                return team;
            }
        }
        return null;
    }

    @Override
    public Board findBoardByName(String name) {
        for (Team team : getTeams()) {
            for (Board board1 : team.getBoards()) {
                if (board1.getName().equalsIgnoreCase(name)) {
                    return board1;
                }
            }
        }
        return null;
    }

    @Override
    public Comment createComment(Person author, String content) {
        return new CommentImpl(author, content);
    }

    @Override
    public Person createPerson(String name) {
        Person person = new PersonImpl(name);
        this.people.add(person);
        return person;
    }

    @Override
    public Team createTeam(String name) {
        Team team = new TeamImpl(name);
        this.teams.add(team);
        return team;
    }

    @Override
    public Board createBoardInTeam(String boardName, String teamName) {
        Board board = new BoardImpl(boardName);
        findTeamByName(teamName).addBoard(board);
        return board;
    }

    @Override
    public Bug createBug(String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity,
                         BugStatus status, Person assignee, Board board) {
        Bug bug = new BugImpl(++nextId, title, description, stepsToReproduce, priority, severity, status, assignee);
        findBoardByName(board.getName()).addTask(bug);
        return bug;
    }

    @Override
    public Story createStory(String title, String description, Priority priority, Size size, StoryStatus status, Person assignee, Board board) {
        Story story = new StoryImpl(++nextId, title, description, priority, size, status, assignee);
        findBoardByName(board.getName()).addTask(story);
        return story;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating, FeedbackStatus status, Board board) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating, status);
        findBoardByName(board.getName()).addTask(feedback);
        return feedback;
    }

}
