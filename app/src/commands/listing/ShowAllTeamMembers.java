package commands.listing;

import Utils.ListingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Person;

import java.util.List;

public class ShowAllTeamMembers implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowAllTeamMembers(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        List<Person> people = taskManagementSystemRepository.findTeamByName(name).getMembers();
        if (people.isEmpty()) {
            return "There are no members in this team.";
        }
        return ListingHelpers.elementsToString(people);
    }
}
