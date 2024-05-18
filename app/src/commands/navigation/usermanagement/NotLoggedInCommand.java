package commands.navigation.usermanagement;

import commands.contracts.Command;

import java.util.List;

public class NotLoggedInCommand implements Command {

    @Override
    public String execute(List<String> parameters) {
        return "You need to log in first.";
    }
}
