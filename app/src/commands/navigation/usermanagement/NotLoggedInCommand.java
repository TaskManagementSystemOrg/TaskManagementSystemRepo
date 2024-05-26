package commands.navigation.usermanagement;

import commands.contracts.Command;

public class NotLoggedInCommand implements Command {

    @Override
    public String execute() {
        return "You need to log in first.";
    }
}
