package commands.listing.complex;

import Utils.ParsingHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.contracts.Bug;
import models.contracts.Feedback;
import models.contracts.Story;
import models.contracts.Task;
import models.enums.BugStatus;
import models.enums.StoryStatus;
import models.enums.TaskType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShowAllStoriesCommand implements Command {
    TaskManagementSystemRepository repository;

    public ShowAllStoriesCommand(TaskManagementSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        Scanner scanner = new Scanner(System.in);
        String input;
        List<Story> stories = new ArrayList<>();
        for (Task task : repository.getTasks()) {
            if (task.getType().equals(TaskType.STORY)) {
                stories.add((Story) task);
            }
        }
        if (stories.isEmpty()){
            return "====================\nNo stories created yet.\n====================";
        }
//NOT_DONE,
//    IN_PROGRESS,
//    DONE
        System.out.println("Filter by status (Not_Done/In_Progress/Done) or enter an empty line: ");
        input = scanner.nextLine();

        if (input.equalsIgnoreCase("")) {
        } else if (input.equalsIgnoreCase("Not_done") || input.equalsIgnoreCase("done") || input.equalsIgnoreCase("in_progress")){
            stories = stories.stream()
                    .filter(story -> story.getStatus().equals(ParsingHelpers.tryParseEnum(input, StoryStatus.class, "Not a valid enum.")))
                    .collect(Collectors.toList());
        } else {
            System.out.println("Not a valid filter. Will not filter.");
        }

        System.out.println("Filter by assignee (Type help to see all options) or enter an empty line: ");
        String input2 = scanner.nextLine();
        boolean k = true;
        boolean unsortedByAssignee = false;

        while (k) {
            if (input2.equalsIgnoreCase("")) {
                k = false;
                unsortedByAssignee = true;
            } else if (input2.equalsIgnoreCase("help")) {
                System.out.println(repository.getPeople());
                input2 = scanner.nextLine();
            } else if (repository.getPeople().contains(repository.findPersonByName(input2))) {
                String finalInput = input2;
                stories = stories.stream()
                        .filter(story -> story.getAssignee().equals(finalInput))
                        .collect(Collectors.toList());
                k = false;
            } else {
                System.out.println("Not a valid filter. Will not filter.");
                k = false;
                unsortedByAssignee = true;
            }
        }

        //title prio size
        System.out.println("Enter sorting criteria (Title/Priority/Size) or enter an empty line: ");
        input2 = scanner.nextLine();
        int sortType = 0;
        if (input2.equalsIgnoreCase("")) {
        } else if (input2.equalsIgnoreCase("title")) {
            stories.sort(Comparator.comparing(Story::getTitle, String::compareToIgnoreCase));
            sortType = 1;
        } else if (input2.equalsIgnoreCase("priority")) {
            stories.sort(Comparator.comparing(Story::getPriority));
            sortType = 2;
        } else if (input2.equalsIgnoreCase("size")) {
            stories.sort(Comparator.comparing(Story::getSize));
            sortType = 3;
        } else {
            System.out.println("Not valid criteria. Will not sort.");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("====================\n");
        if (!unsortedByAssignee && !stories.isEmpty()) {
            stringBuilder.append(String.format("Stories by: %s\n", stories.get(0).getAssignee()));
        }
        if (stories.isEmpty()) {
            stringBuilder.append("No stories fit criteria.\n");
        } else {
            for (Story story : stories) {
                stringBuilder.append(story.toString());
                if (unsortedByAssignee) {
                    stringBuilder.append(String.format(" Assignee: %s", story.getAssignee()));
                }
                switch (sortType) {
                    case 0, 1:
                        break;
                    case 2:
                        stringBuilder.append(String.format(" Priority: %s", story.getPriority()));
                        break;
                    case 3:
                        stringBuilder.append(String.format(" Size: %s", story.getSize()));
                        break;
                }
                stringBuilder.append("\n");
            }
        }
        stringBuilder.append("====================");
        sortType = 0;
        return stringBuilder.toString();
    }
}
