package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import commands.enums.UserType;
import core.contracts.TaskManagementSystemRepository;
import models.*;
import models.contracts.*;
import models.deserializers.PersonDeserializer;
import models.deserializers.TaskDeserializer;
import models.deserializers.TeamDeserializer;
import models.enums.*;
import models.serializers.PersonSerializer;
import models.serializers.TaskSerializer;
import models.serializers.TeamSerializer;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {

    private static int nextId;

    private UserType currentUser = UserType.NOT_LOGGED_IN;
    private Board currentBoard = null;
    private Team currentTeam = null;

    private final List<Task> tasks;
    private final List<Team> teams;
    private final List<Person> people;
    private final List<Board> boards;
    private static final String PEOPLE_DATA_FILE = "people.json";
    private static final String TEAMS_DATA_FILE = "teams.json";
    private static final String TASK_DATA_FILE = "tasks.json";



    public TaskManagementSystemRepositoryImpl() {
        this.tasks = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.people = new ArrayList<>();
        this.boards = new ArrayList<>();
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
        List<Person> members = new ArrayList<>();
        for(String member : team.getMembers())
        {
            members.add(findPersonByName(member));
        }
        return members;
    }

    @Override
    public List<Board> getBoards(Team team) {
        List<Board> boards = new ArrayList<>();
        for(String board : team.getBoards())
        {
            boards.add(findBoardByName(board));
        }
        return boards;
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<String> getActivity(Person person) {
        return person.getActivity();
    }

    @Override
    public List<String> getActivity(Team team) {
        return team.getActivity();
    }

    @Override
    public List<String> getActivity(Board board) {
        return board.getActivity();
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Task> getTasks(Team team) {
        List<Task> tasks = new ArrayList<>();
        for (String boardName : team.getBoards()) {
            Board board = findBoardByName(boardName);
            tasks.addAll(getTasks(board));
        }
        return tasks;
    }

    @Override
    public List<Task> getTasks(Board board) {
        List<Task> boardTasks = new ArrayList<>();
        for (int taskId : board.getTasks()) {
            boardTasks.add(findTaskById(taskId));
        }
        return boardTasks;
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
    public Task findTaskById(int Id) {
        for (Task task : getTasks()) {
            if (task.getId() == Id) {
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
            for (Board board1 : boards) {
                if (board1.getName().equalsIgnoreCase(name)) {
                    return board1;
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
        if (people.contains(findPersonByName(name))) {
            return null;
        }
        Person person = new PersonImpl(name);
        this.people.add(person);
        return person;
    }

    @Override
    public Team createTeam(String name) {
        if (teams.contains(findTeamByName(name))) {
            return null;
        }
        Team team = new TeamImpl(name);
        this.teams.add(team);
        return team;
    }

    @Override
    public Board createBoardInTeam(String boardName, String teamName) {
        Board board = new BoardImpl(boardName);
        findTeamByName(teamName).addBoard(board.getName());
        boards.add(board);
        return board;
    }

    @Override
    public Bug createBug(String title, String description, List<String> stepsToReproduce, Priority priority, Severity severity,
                         BugStatus status, Person assignee, Board board) {
        Bug bug = new BugImpl(++nextId, title, description, stepsToReproduce, priority, severity, status, assignee.getName());
        findBoardByName(board.getName()).addTask(bug.getId());
        this.tasks.add(bug);
        return bug;
    }

    @Override
    public Story createStory(String title, String description, Priority priority, Size size, StoryStatus status, Person assignee, Board board) {
        Story story = new StoryImpl(++nextId, title, description, priority, size, status, assignee.getName());
        findBoardByName(board.getName()).addTask(story.getId());
        this.tasks.add(story);
        return story;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating, FeedbackStatus status, Board board) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating, status);
        findBoardByName(board.getName()).addTask(feedback.getId());
        this.tasks.add(feedback);
        return feedback;
    }

    @Override
    public Board getCurrentBoard() {
        return this.currentBoard;
    }

    @Override
    public void setCurrentBoard(Board board) {
        this.currentBoard = board;
    }

    @Override
    public Team getCurrentTeam() {
        return this.currentTeam;
    }

    @Override
    public void setCurrentTeam(Team team) {
        this.currentTeam = team;
    }

    @Override
    public void setCurrentUser(UserType type) {
        this.currentUser = type;
    }

    @Override
    public UserType getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void savePeopleToJson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(PersonImpl.class, new PersonSerializer())
                .create();

        try (FileWriter writer = new FileWriter(PEOPLE_DATA_FILE)) {
            gson.toJson(people, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveTeamsToJSon() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(TeamImpl.class, new TeamSerializer())
                .create();
        try (FileWriter writer = new FileWriter(TEAMS_DATA_FILE)) {
            gson.toJson(teams, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveTasksToJson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(TaskImpl.class, new TaskSerializer())
                .create();
        try (FileWriter writer = new FileWriter(TASK_DATA_FILE)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadPeopleFromJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Person.class, new PersonDeserializer());
        Gson gson = gsonBuilder.create();

        File file = new File(PEOPLE_DATA_FILE);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        try (FileReader reader = new FileReader(file)) {
            Type personListType = new TypeToken<List<Person>>() {}.getType();
            List<Person> loadedPeople = gson.fromJson(reader, personListType);
            if (loadedPeople != null) {
                this.people.addAll(loadedPeople);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadTeamsToJSon() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Team.class, new TeamDeserializer());
        Gson gson = gsonBuilder.create();

        File file = new File(TEAMS_DATA_FILE);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        try (FileReader reader = new FileReader(file)) {
            Type team = new TypeToken<List<Team>>() {}.getType();
            List<Team> loadedTeams = gson.fromJson(reader, team);
            if (loadedTeams != null) {
                this.teams.addAll(loadedTeams);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadTasksToJson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskDeserializer())
                .create();
        File file = new File(TASK_DATA_FILE);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        try (FileReader reader = new FileReader(file)) {
            Type task = new TypeToken<List<Task>>() {}.getType();
            List<Task> loadedTasks = gson.fromJson(reader, task);

            if (loadedTasks != null) {
                this.tasks.addAll(loadedTasks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
