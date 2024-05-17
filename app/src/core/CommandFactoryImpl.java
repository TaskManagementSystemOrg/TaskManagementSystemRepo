package core;

import Utils.ParsingHelpers;
import com.google.gson.Gson;
import commands.SaveCommand;
import commands.contracts.Command;
import commands.creation.*;
import commands.enums.CommandType;
import commands.listing.*;
import commands.modification.person.AddPersonToTeamCommand;
import commands.modification.person.AssignTaskToAPerson;
import commands.modification.person.UnassignTaskToAPerson;
import commands.modification.task.*;
import commands.navigation.EnterBoardCommand;
import commands.navigation.ExitBoardCommand;
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

        if (taskManagementSystemRepository.getCurrentBoard() != null) {
            switch (commandType) {
                case SHOW_BOARD_ACTIVITY:
                    return new ShowBoardActivityCommand(taskManagementSystemRepository);
                case CREATE_BUG:
                    return new CreateNewBugCommand(taskManagementSystemRepository);
                case CREATE_STORY:
                    return new CreateNewStoryCommand(taskManagementSystemRepository);
                case CREATE_FEEDBACK:
                    return new CreateNewFeedbackCommand(taskManagementSystemRepository);
                case EXIT_BOARD:
                    return new ExitBoardCommand(taskManagementSystemRepository);
                default:
                    throw new IllegalArgumentException();

            }
        } else
            switch (commandType) {
            case CREATE_PERSON:
                return new CreateNewPersonCommand(taskManagementSystemRepository);
            case SHOW_ALL_PEOPLE:
                return new ShowAllPeopleCommand(taskManagementSystemRepository);
            case SHOW_PERSON_ACTIVITY:
                return new ShowPersonsActivityCommand(taskManagementSystemRepository);
            case CREATE_TEAM:
                return new CreateNewTeamCommand(taskManagementSystemRepository);
            case SHOW_ALL_TEAMS:
                return null;
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
            case SHOW_BOARD_ACTIVITY:
                return new ShowBoardActivityCommand(taskManagementSystemRepository);
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
            case SAVE:
                return new SaveCommand(taskManagementSystemRepository);
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
