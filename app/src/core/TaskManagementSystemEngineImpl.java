package core;

import commands.contracts.Command;
import core.contracts.Engine;
import core.contracts.TaskManagementSystemRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagementSystemEngineImpl implements Engine {
    private static final String TERMINATION_COMMAND = "Exit";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";

    private final CommandFactoryImpl commandFactory;
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public TaskManagementSystemEngineImpl() {
        this.commandFactory = new CommandFactoryImpl();
        this.taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
    }

    public void start() {
        taskManagementSystemRepository.loadPeopleFromJson();
        taskManagementSystemRepository.loadTeamsToJSon();
        taskManagementSystemRepository.loadTasksToJson();
        taskManagementSystemRepository.loadBoardsFromJson();
        taskManagementSystemRepository.updateNextId();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type (user/admin) to log in as such: ");
        while (true) {
            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isBlank()) {
                    System.out.println(EMPTY_COMMAND_ERROR);
                    continue;
                }
                if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(inputLine);
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    private void processCommand(String inputLine) {
        String commandName = extractCommandName(inputLine);
        Command command = commandFactory.createCommandFromCommandName(commandName, taskManagementSystemRepository);
        String executionResult = command.execute();
        clearConsole();
        if(taskManagementSystemRepository.getCurrentBoard() != null)
        {
            taskManagementSystemRepository.printBoardTasks(taskManagementSystemRepository.getCurrentBoard());
        }
        System.out.println(executionResult);
    }

    private String extractCommandName(String inputLine) {
        return inputLine.split(" ")[0];
    }

       private List<String> extractCommandParameters(String inputLine) {
        String[] commandParts = inputLine.split(" ");
        List<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }

    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}