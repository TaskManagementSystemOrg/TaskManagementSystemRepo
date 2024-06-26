package core.contracts;

import commands.enums.UserType;
import models.contracts.*;
import models.enums.*;

import java.util.List;

public interface TaskManagementSystemRepository {
    List<Person> getPeople();
    List<Team> getTeams();
    List<Person> getMembers(Team team);
    List<Board> getBoards(Team team);
    List<Board> getBoards();
    List<String> getActivity(Person person);
    List<String> getActivity(Team team);
    List<String> getActivity(Board board);
    List<Task> getTasks();
    List<Task> getTasks(Team team);
    List<Task> getTasks(Board board);

    Task findTaskByName(String name);
    Task findTaskById(int id);
    Person findPersonByName(String name);
    Team findTeamByName(String name);
    Board findBoardByName(String name);

    Comment createComment(Person author, String content);
    Person createPerson(String name);
    Team createTeam(String name);
    Board createBoardInTeam(String boardName, String teamName);
    Bug createBug(String title, String description, List<String> stepsToReproduce, Priority priority,
                  Severity severity, BugStatus status, Person assignee, Board board);
    Story createStory(String title, String description, Priority priority, Size size, StoryStatus status, Person assignee, Board board);
    Feedback createFeedback(String title, String description, int rating, FeedbackStatus status, Board board);

    Board getCurrentBoard();
    void setCurrentBoard(Board board);
    Team getCurrentTeam();
    void setCurrentTeam(Team team);
    void setCurrentUser(UserType type);
    UserType getCurrentUser();
    void savePeopleToJson();
    void saveTeamsToJSon();
    void saveTasksToJson();
    void saveBoardsToJson();
    void loadPeopleFromJson();
    void loadTeamsToJSon();
    void loadTasksToJson();
    void loadBoardsFromJson();
    void updateNextId();
    void printBoardTasks(Board board);
}
