package commands.listing.complex;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class ShowAllBugsCommand implements Command {
    private TaskManagementSystemRepository repository;

    public ShowAllBugsCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }
}
