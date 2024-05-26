package commands.listing;

import Utils.ListingHelpers;
import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Person;

import java.util.List;
import java.util.Scanner;

public class ShowPersonsActivityCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    protected final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowPersonsActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (taskManagementSystemRepository.getPeople().isEmpty()) {
            return "No people created yet.";
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type help to see all options or enter person: ");
        String input = scanner.nextLine();
        while (true) {
            if (input.equalsIgnoreCase("help")) {
                System.out.println("====================");
                System.out.println(ListingHelpers.elementsToString(taskManagementSystemRepository.getPeople()));
                System.out.println("====================");
                System.out.println("Type help to see all options or enter person: ");
                input = scanner.nextLine();
            } else if (taskManagementSystemRepository.getPeople().contains(taskManagementSystemRepository.findPersonByName(input))) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("====================\n");
                for (String string : taskManagementSystemRepository.findPersonByName(input).getActivity()) {
                    stringBuilder.append(string);
                    stringBuilder.append("\n");
                }
                stringBuilder.append("====================");
                return stringBuilder.toString();
            } else {
                System.out.println("Not a valid input. Try again:");
                input = scanner.nextLine();
            }

        }

    }
}
