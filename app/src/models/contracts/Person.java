package models.contracts;

import java.util.List;

public interface Person {
    String getName();
    List<Task> getTasks();
}