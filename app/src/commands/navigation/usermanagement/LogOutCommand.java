package commands.navigation.usermanagement;

import commands.contracts.Command;
import commands.enums.UserType;
import core.contracts.TaskManagementSystemRepository;

public class LogOutCommand implements Command {
    TaskManagementSystemRepository repository;

    public LogOutCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }


    @Override
    public String execute() {
        if (repository.getCurrentTeam() != null || repository.getCurrentBoard() != null) {
            return "Exit the current team and board in order to log out.";
        }
        repository.setCurrentUser(UserType.NOT_LOGGED_IN);
        return "Logged out. Type (user/admin) to log in as such: ";
    }
}
