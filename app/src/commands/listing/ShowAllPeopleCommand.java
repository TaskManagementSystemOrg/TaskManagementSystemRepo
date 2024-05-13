package commands.listing;

import Utils.ListingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Person;

import java.util.List;

public class ShowAllPeopleCommand implements Command {
    private final List<Person> people;

    public ShowAllPeopleCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.people = taskManagementSystemRepository.getPeople();
    }

    @Override
    public String execute(List<String> parameters) {
        if (people.isEmpty()) {
            return "There are no registered users.";
        }

        return ListingHelpers.elementsToString(people);
    }
}
