package core;

import commands.contracts.Command;
import commands.creation.CreateNewBoardInATeam;
import commands.creation.CreateNewPersonCommand;
import commands.creation.CreateNewTeamCommand;
import commands.enums.CommandType;
import commands.listing.*;
import commands.modification.AddPersonToTeamCommand;
import commands.modification.AssignTaskToAPerson;
import commands.modification.UnassignTaskToAPerson;
import core.contracts.CommandFactory;
import core.contracts.TaskManagementSystemRepository;

public class CommandFactoryImpl implements CommandFactory {
    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementSystemRepository taskManagementSystemRepository){
        CommandType commandType = CommandType.ASSIGN_TASK;
        switch (commandType) {
            case CREATE_PERSON:
                return new CreateNewPersonCommand(taskManagementSystemRepository);
                break;
            case SHOW_ALL_PEOPLE:
                return new ShowAllPeopleCommand(taskManagementSystemRepository);
                break;
            case SHOW_PERSON_ACTIVITY:
                return new ShowPersonsActivityCommand(taskManagementSystemRepository);
                break;
            case CREATE_TEAM:
                return new CreateNewTeamCommand(taskManagementSystemRepository)
                break;
            case SHOW_ALL_TEAMS:
                return new ShowBoardActivityCommand(taskManagementSystemRepository)
                break;
            case SHOW_TEAM_ACTIVITY:
                return new ShowTeamsActivityCommand(taskManagementSystemRepository)
                break;
            case ADD_PERSON_TO_TEAM:
                return new AddPersonToTeamCommand(taskManagementSystemRepository)
                break;
            case SHOW_ALL_TEAM_MEMBERS:
                return new ShowAllTeamMembers(taskManagementSystemRepository)
                break;
            case CREATE_BOARD:
                return new CreateNewBoardInATeam(taskManagementSystemRepository);
                break;
            case SHOW_ALL_TEAM_BOARDS:
                return new ShowAllTeamBoardsCommand(taskManagementSystemRepository);
                break;
            case SHOW_BOARD_ACTIVITY:
                return new ShowBoardActivityCommand(taskManagementSystemRepository);
                break;
            case CREATE_BUG:
                break;
            case CREATE_STORY:
                break;
            case CREATE_FEEDBACK:
                break;
            case CHANGE_BUG_PRIORITY:
                break;
            case CHANGE_BUG_SEVERITY:
                break;
            case CHANGE_BUG_STATUS:
                break;
            case CHANGE_STORY_PRIORITY:
                break;
            case CHANGE_STORY_SIZE:
                break;
            case CHANGE_STORY_STATUS:
                break;
            case CHANGE_FEEDBACK_RATING:
                break;
            case CHANGE_FEEDBACK_STATUS:
                break;
            case ASSIGN_TASK:
                return new AssignTaskToAPerson(taskManagementSystemRepository);
                break;
            case UNASSIGN_TASK:
                return new UnassignTaskToAPerson(taskManagementSystemRepository);
                break;
            default:
                throw  new IllegalArgumentException();
        }
        return null;
    }

}
