package core.contracts;

import models.BoardImpl;
import models.contracts.*;
import models.enums.*;

import java.util.List;

public interface TaskManagementSystemRepository {
    Person getPeople();
    Team getTeams();
    Person getMembers(Team team);
    Board getBoards(Team team);
    List<String> getActivity(Person person);
    List<String> getActivity(Team team);
    List<String> getActivity(Board board);
    Task getTasks();
    Task getTasks(Team team);
    Task getTasks(Board board);

    Comment createComment(Person author, String content);
    Person createPerson(String name);
    Team createTeam(String name);
    Board createBoardInTeam(String boardName, String teamName);
    Bug createBug(String title, String description, List<String> stepsToReproduce, Priority priority,
                  Severity severity, BugStatus status, Person assignee, Board board);
    Story createStory(String title, String description, Priority priority, Size size, StoryStatus status, Person assignee, Board board);
    Feedback createFeedback(String title, String description, int rating, FeedbackStatus status, Board board);
}
