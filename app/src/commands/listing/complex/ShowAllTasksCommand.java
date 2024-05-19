package commands.listing.complex;

import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class ShowAllTasksCommand implements Command {
    TaskManagementSystemRepository repository;

    public ShowAllTasksCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }
}
