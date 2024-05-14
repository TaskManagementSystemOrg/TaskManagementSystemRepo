import core.TaskManagementSystemEngineImpl;

import java.util.Scanner;

public class Startup {
    public static void main(String[] args) {
        TaskManagementSystemEngineImpl engine = new TaskManagementSystemEngineImpl();
        engine.start();
    }

}