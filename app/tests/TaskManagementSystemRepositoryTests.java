package core;

import commands.enums.UserType;
import core.contracts.TaskManagementSystemRepository;
import models.*;
import models.contracts.*;
import models.enums.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagementSystemRepositoryTests {

    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new TaskManagementSystemRepositoryImpl();
    }

    @Test
    public void testCreatePerson() {
        Person person = repository.createPerson("JohnDoe");
        assertNotNull(person);
        assertEquals("JohnDoe", person.getName());
        assertTrue(repository.getPeople().contains(person));
    }

    @Test
    public void testCreatePersonDuplicate() {
        repository.createPerson("JaneDoe");
        Person duplicate = repository.createPerson("JaneDoe");
        assertNull(duplicate);
    }

    @Test
    public void testCreateTeam() {
        Team team = repository.createTeam("AlphaTeam");
        assertNotNull(team);
        assertEquals("AlphaTeam", team.getName());
        assertTrue(repository.getTeams().contains(team));
    }

    @Test
    public void testCreateTeamDuplicate() {
        repository.createTeam("BetaTeam");
        Team duplicate = repository.createTeam("BetaTeam");
        assertNull(duplicate);
    }

    @Test
    public void testCreateBoardInTeam() {
        repository.createTeam("GammaTeam");
        Board board = repository.createBoardInTeam("DevBoard", "GammaTeam");
        assertNotNull(board);
        assertEquals("DevBoard", board.getName());
        assertTrue(repository.getBoards().contains(board));
        Team team = repository.findTeamByName("GammaTeam");
        assertTrue(team.getBoards().contains("DevBoard"));
    }

    @Test
    public void testCreateBug() {
        repository.createTeam("DeltaTeam");
        repository.createBoardInTeam("QABoard", "DeltaTeam");
        Person assignee = repository.createPerson("DevMember");
        Board board = repository.findBoardByName("QABoard");
        Bug bug = repository.createBug("BugTitleValid", "BugDescription", new ArrayList<>(), Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, assignee, board);
        assertNotNull(bug);
        assertEquals("BugTitleValid", bug.getTitle());
        assertTrue(repository.getTasks().contains(bug));
        assertTrue(board.getTasks().contains(bug.getId()));
    }

    @Test
    public void testCreateStory() {
        repository.createTeam("EpsilonTeam");
        repository.createBoardInTeam("DevBoard", "EpsilonTeam");
        Person assignee = repository.createPerson("DevMember");
        Board board = repository.findBoardByName("DevBoard");
        Story story = repository.createStory("StoryTitle", "StoryDescription", Priority.HIGH, Size.LARGE, StoryStatus.NOT_DONE, assignee, board);
        assertNotNull(story);
        assertEquals("StoryTitle", story.getTitle());
        assertTrue(repository.getTasks().contains(story));
        assertTrue(board.getTasks().contains(story.getId()));
    }

    @Test
    public void testCreateFeedback() {
        repository.createTeam("ZetaTeamValid");
        repository.createBoardInTeam("FeedbackB", "ZetaTeamValid");
        Board board = repository.findBoardByName("FeedbackB");
        Feedback feedback = repository.createFeedback("FeedbackTitle", "FeedbackDescription", 8, FeedbackStatus.NEW, board);
        assertNotNull(feedback);
        assertEquals("FeedbackTitle", feedback.getTitle());
        assertTrue(repository.getTasks().contains(feedback));
        assertTrue(board.getTasks().contains(feedback.getId()));
    }

    @Test
    public void testSetCurrentBoard() {
        repository.createTeam("EtaTeam");
        Board board = repository.createBoardInTeam("Board1", "EtaTeam");
        repository.setCurrentBoard(board);
        assertEquals(board, repository.getCurrentBoard());
    }

    @Test
    public void testSetCurrentTeam() {
        Team team = repository.createTeam("ThetaTeam");
        repository.setCurrentTeam(team);
        assertEquals(team, repository.getCurrentTeam());
    }

    @Test
    public void testSetCurrentUser() {
        repository.setCurrentUser(UserType.ADMIN);
        assertEquals(UserType.ADMIN, repository.getCurrentUser());
    }

    @Test
    public void testSaveAndLoadPeople() {
        repository.createPerson("JohnDoe");
        repository.savePeopleToJson();
        repository.loadPeopleFromJson();
        assertTrue(repository.getPeople().stream().anyMatch(person -> "JohnDoe".equals(person.getName())));
    }

    @Test
    public void testSaveAndLoadTeams() {
        repository.createTeam("LambdaTeam");
        repository.saveTeamsToJSon();
        repository.loadTeamsToJSon();
        assertTrue(repository.getTeams().stream().anyMatch(team -> "LambdaTeam".equals(team.getName())));
    }

    @Test
    public void testSaveAndLoadTasks() {
        repository.createTeam("MuTeam");
        repository.createBoardInTeam("TaskBoard", "MuTeam");
        Board board = repository.findBoardByName("TaskBoard");
        Person assignee = repository.createPerson("DevMember");
        repository.createBug("BugTitleValid", "BugDescription", new ArrayList<>(), Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, assignee, board);
        repository.saveTasksToJson();
        repository.loadTasksToJson();
        assertTrue(repository.getTasks().stream().anyMatch(task -> "BugTitleValid".equals(task.getTitle())));
    }

    @Test
    public void testSaveAndLoadBoards() {
        repository.createTeam("NuTeam");
        repository.createBoardInTeam("Board1", "NuTeam");
        repository.saveBoardsToJson();
        repository.loadBoardsFromJson();
        assertTrue(repository.getBoards().stream().anyMatch(board -> "Board1".equals(board.getName())));
    }

    @Test
    public void testFindPersonByName() {
        repository.createPerson("JohnDoe");
        Person person = repository.findPersonByName("JohnDoe");
        assertNotNull(person);
        assertEquals("JohnDoe", person.getName());
    }

    @Test
    public void testFindTeamByName() {
        repository.createTeam("OmicronTeam");
        Team team = repository.findTeamByName("OmicronTeam");
        assertNotNull(team);
        assertEquals("OmicronTeam", team.getName());
    }

    @Test
    public void testFindBoardByName() {
        repository.createTeam("PiTeam");
        repository.createBoardInTeam("Board1", "PiTeam");
        Board board = repository.findBoardByName("Board1");
        assertNotNull(board);
        assertEquals("Board1", board.getName());
    }

    @Test
    public void testFindTaskById() {
        repository.createTeam("RhoTeam");
        repository.createBoardInTeam("TaskBoard", "RhoTeam");
        Person assignee = repository.createPerson("DevMember");
        Board board = repository.findBoardByName("TaskBoard");
        Bug bug = repository.createBug("BugTitleValid", "BugDescription", new ArrayList<>(), Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, assignee, board);
        Task foundTask = repository.findTaskById(bug.getId());
        assertNotNull(foundTask);
        assertEquals(bug.getId(), foundTask.getId());
    }
}
