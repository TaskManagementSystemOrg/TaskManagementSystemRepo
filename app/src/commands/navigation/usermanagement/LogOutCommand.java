package commands.navigation.usermanagement;

import commands.contracts.Command;
import core.UserType;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class LogOutCommand implements Command {
    TaskManagementSystemRepository repository;

    public LogOutCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }


    @Override
    public String execute(List<String> parameters) {
        if (repository.getCurrentTeam() != null || repository.getCurrentBoard() != null) {
            return "Exit the current team and board in order to log out.";
        }
        repository.setCurrentUser(UserType.NOT_LOGGED_IN);
        return "Logged out. Log in to proceed with operations:";
    }
}
