package models.contracts;

import java.util.List;

public interface Board extends Printable{
    String getName();
    List<Integer> getTasks();
    void addTask(int task);
    List<String> getActivity();
}
