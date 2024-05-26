package core;

import Utils.ParsingHelpers;
import com.google.gson.Gson;
import commands.SaveCommand;
import commands.comments.AddCommentCommand;
import commands.contracts.Command;
import commands.creation.*;
import commands.enums.CommandType;
import commands.listing.*;
import commands.listing.complex.*;
import commands.modification.person.AddPersonToTeamCommand;
import commands.modification.person.AssignTaskToAPerson;
import commands.modification.person.UnassignTaskToAPerson;
import commands.modification.task.*;
import commands.navigation.EnterBoardCommand;
import commands.navigation.EnterTeamCommand;
import commands.navigation.ExitBoardCommand;
import commands.navigation.ExitTeamCommand;
import commands.navigation.usermanagement.LogInCommand;
import commands.navigation.usermanagement.LogOutCommand;
import commands.navigation.usermanagement.NotLoggedInCommand;
import core.contracts.CommandFactory;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementSystemRepository taskManagementSystemRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class, String.format(INVALID_COMMAND, commandTypeAsString));
        clearConsole();

        switch (taskManagementSystemRepository.getCurrentUser()) {
            case NOT_LOGGED_IN:
                return handleNotLoggedInCommands(commandType, taskManagementSystemRepository);

            case NORMAL:
                return handleNormalUserCommands(commandType, taskManagementSystemRepository);

            case ADMIN:
                return handleAdminUserCommands(commandType, taskManagementSystemRepository);

            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
        }
    }

    private Command handleNotLoggedInCommands(CommandType commandType, TaskManagementSystemRepository taskManagementSystemRepository) {
        switch (commandType) {
            case ADMIN:
                return new LogInCommand(taskManagementSystemRepository, 1);
            case USER:
                return new LogInCommand(taskManagementSystemRepository, 0);
            default:
                return new NotLoggedInCommand();
        }
    }

    private Command handleNormalUserCommands(CommandType commandType, TaskManagementSystemRepository taskManagementSystemRepository) {
        if (taskManagementSystemRepository.getCurrentBoard() != null) {
//            taskManagementSystemRepository.printBoardTasks(taskManagementSystemRepository.getCurrentBoard());
            return handleNormalUserWithBoardCommands(commandType, taskManagementSystemRepository);
        } else {
            return handleNormalUserWithoutBoardCommands(commandType, taskManagementSystemRepository);
        }
    }

    private Command handleNormalUserWithBoardCommands(CommandType commandType, TaskManagementSystemRepository taskManagementSystemRepository) {
        switch (commandType) {
            case SHOW_BOARD_ACTIVITY:
                return new ShowBoardActivityCommand(taskManagementSystemRepository);
            case CREATE_BUG:
                return new CreateNewBugCommand(taskManagementSystemRepository);
            case CREATE_STORY:
                return new CreateNewStoryCommand(taskManagementSystemRepository);
            case CREATE_FEEDBACK:
                return new CreateNewFeedbackCommand(taskManagementSystemRepository);
            case ADD_COMMENT:
                return new AddCommentCommand(taskManagementSystemRepository);
            case SHOW_COMMENTS:
                return new ShowCommentsCommand(taskManagementSystemRepository);
            case EXIT_BOARD:
                return new ExitBoardCommand(taskManagementSystemRepository);
            case LOG_OUT:
                return new LogOutCommand(taskManagementSystemRepository);
            default:
                throw new IllegalArgumentException();
        }
    }

    private Command handleNormalUserWithoutBoardCommands(CommandType commandType, TaskManagementSystemRepository taskManagementSystemRepository) {
        switch (commandType) {
            case SHOW_PERSON_ACTIVITY:
                return new ShowPersonsActivityCommand(taskManagementSystemRepository);
            case SHOW_TEAM_ACTIVITY:
                return new ShowTeamsActivityCommand(taskManagementSystemRepository);
            case SHOW_ALL_TEAM_MEMBERS:
                return new ShowAllTeamMembers(taskManagementSystemRepository);
            case SHOW_ALL_TEAM_BOARDS:
                return new ShowAllTeamBoardsCommand(taskManagementSystemRepository);
            case ENTER_BOARD:
                return new EnterBoardCommand(taskManagementSystemRepository);
            case ENTER_TEAM:
                return new EnterTeamCommand(taskManagementSystemRepository);
            case EXIT_TEAM:
                return new ExitTeamCommand(taskManagementSystemRepository);
            case EXIT_BOARD:
                return new ExitBoardCommand(taskManagementSystemRepository);
            case SAVE:
                return new SaveCommand(taskManagementSystemRepository);
            case LOG_OUT:
                return new LogOutCommand(taskManagementSystemRepository);
            default:
                throw new IllegalArgumentException();
        }
    }

    private Command handleAdminUserCommands(CommandType commandType, TaskManagementSystemRepository taskManagementSystemRepository) {
        switch (commandType) {
            case SHOW_BOARD_ACTIVITY:
                return new ShowBoardActivityCommand(taskManagementSystemRepository);
            case SHOW_COMMENTS:
                return new ShowCommentsCommand(taskManagementSystemRepository);
            case SHOW_ALL_PEOPLE:
                return new ShowAllPeopleCommand(taskManagementSystemRepository);
            case SHOW_ALL_TEAMS:
                return new ShowAllTeamsCommand(taskManagementSystemRepository);
            case SHOW_ALL_BUGS:
                return new ShowAllBugsCommand(taskManagementSystemRepository);
            case SHOW_ALL_FEEDBACK:
                return new ShowAllFeedbackCommand(taskManagementSystemRepository);
            case SHOW_ALL_STORIES:
                return new ShowAllStoriesCommand(taskManagementSystemRepository);
            case SHOW_ALL_TASKS:
                return new ShowAllTasksCommand(taskManagementSystemRepository);
            case SHOW_ALL_TASKS_WITH_ASSIGNEE:
                return new ShowTasksWithAssigneeCommand(taskManagementSystemRepository);
            case CREATE_PERSON:
                return new CreateNewPersonCommand(taskManagementSystemRepository);
            case SHOW_PERSON_ACTIVITY:
                return new ShowPersonsActivityCommand(taskManagementSystemRepository);
            case CREATE_TEAM:
                return new CreateNewTeamCommand(taskManagementSystemRepository);
            case SHOW_TEAM_ACTIVITY:
                return new ShowTeamsActivityCommand(taskManagementSystemRepository);
            case ADD_PERSON_TO_TEAM:
                return new AddPersonToTeamCommand(taskManagementSystemRepository);
            case SHOW_ALL_TEAM_MEMBERS:
                return new ShowAllTeamMembers(taskManagementSystemRepository);
            case CREATE_BOARD:
                return new CreateNewBoardInATeam(taskManagementSystemRepository);
            case SHOW_ALL_TEAM_BOARDS:
                return new ShowAllTeamBoardsCommand(taskManagementSystemRepository);
            case CREATE_BUG:
                return new CreateNewBugCommand(taskManagementSystemRepository);
            case CREATE_STORY:
                return new CreateNewStoryCommand(taskManagementSystemRepository);
            case CREATE_FEEDBACK:
                return new CreateNewFeedbackCommand(taskManagementSystemRepository);
            case CHANGE_BUG_PRIORITY:
                return new ChangeBugPriority(taskManagementSystemRepository);
            case CHANGE_BUG_SEVERITY:
                return new ChangeBugSeverity(taskManagementSystemRepository);
            case CHANGE_BUG_STATUS:
                return new ChangeBugStatus(taskManagementSystemRepository);
            case CHANGE_STORY_PRIORITY:
                return new ChangeStoryPriority(taskManagementSystemRepository);
            case CHANGE_STORY_SIZE:
                return new ChangeStorySize(taskManagementSystemRepository);
            case CHANGE_STORY_STATUS:
                return new ChangeStoryStatus(taskManagementSystemRepository);
            case CHANGE_FEEDBACK_RATING:
                return new ChangeFeedbackRating(taskManagementSystemRepository);
            case CHANGE_FEEDBACK_STATUS:
                return new ChangeFeedbackStatus(taskManagementSystemRepository);
            case ASSIGN_TASK:
                return new AssignTaskToAPerson(taskManagementSystemRepository);
            case UNASSIGN_TASK:
                return new UnassignTaskToAPerson(taskManagementSystemRepository);
            case ENTER_BOARD:
                return new EnterBoardCommand(taskManagementSystemRepository);
            case ENTER_TEAM:
                return new EnterTeamCommand(taskManagementSystemRepository);
            case EXIT_BOARD:
                return new ExitBoardCommand(taskManagementSystemRepository);
            case EXIT_TEAM:
                return new ExitTeamCommand(taskManagementSystemRepository);
            case ADD_COMMENT:
                return new AddCommentCommand(taskManagementSystemRepository);
            case SAVE:
                return new SaveCommand(taskManagementSystemRepository);
            case LOG_OUT:
                return new LogOutCommand(taskManagementSystemRepository);
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}