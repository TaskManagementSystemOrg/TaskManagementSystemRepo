package models.contracts;

import java.util.List;

public interface Board extends Printable{
    String getName();
    List<Task> getTasks();
    void addTask(Task task);
    List<String> getActivity();
}
