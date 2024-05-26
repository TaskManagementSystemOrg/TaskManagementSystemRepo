package commands.modification.person;

import Utils.ListingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Person;
import models.contracts.Team;

import java.util.List;
import java.util.Scanner;

public class AddPersonToTeamCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    protected final TaskManagementSystemRepository repository;

    public AddPersonToTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.repository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (repository.getPeople().isEmpty() || repository.getTeams().isEmpty()) {
            return "No people or teams created yet.";
        }
        Scanner scanner = new Scanner(System.in);
        Person person = null;
        System.out.println("Type help to see all options or enter person: ");
        String input = scanner.nextLine();

        while (true) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println("====================");
                System.out.println(ListingHelpers.elementsToString(repository.getPeople()));
                System.out.println("====================");
                System.out.println("Type help to see all options or enter person: ");
                input = scanner.nextLine();
            } else if (repository.getPeople().contains(repository.findPersonByName(input))) {
                person = repository.findPersonByName(input);
                break;
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }
        }

        System.out.println("Type help to see all options or enter a team: ");
        input = scanner.nextLine();

        while (true) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println("====================");
                System.out.println(ListingHelpers.elementsToString(repository.getTeams()));
                System.out.println("====================");
                System.out.println("Type help to see all options or enter person: ");
                input = scanner.nextLine();
            } else if (repository.getTeams().contains(repository.findTeamByName(input))) {
                repository.findTeamByName(input).addMember(person.getName());
                return String.format("Added %s to %s.", person.getName(), input);
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }
        }


    }
}
